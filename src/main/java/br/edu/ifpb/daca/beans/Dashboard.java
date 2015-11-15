package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Pedido;
import br.edu.ifpb.daca.entities.PedidoLocal;
import br.edu.ifpb.daca.service.PedidoService;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.extensions.component.gchart.model.GChartModel;
import org.primefaces.extensions.component.gchart.model.GChartModelBuilder;
import org.primefaces.extensions.component.gchart.model.GChartType;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@Named
@RequestScoped
public class Dashboard extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private LineChartModel lineChartModel;
    private TimelineModel timeLineModel;
    private List<Pedido> pedidos;
    private String selectedYear;
    private GChartType chartType = GChartType.COLUMN;
    private GChartModel chartModel = null;

    @Inject
    private PedidoService pedidoService;

    @PostConstruct
    public void init() {
        setDefaultSelectedYear();
        setPedidosList();
        createLineChart();
        createTodayBarChart();
        setTimeLine();
    }

    private void setPedidosList() {
        try {
            pedidos = pedidoService.getAll();
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }
    }

    private void createLineChart() {

        lineChartModel = new LineChartModel();

        ChartSeries apuracaoLocal = new LineChartSeries();
        apuracaoLocal.setLabel("Apuração Local");
        ChartSeries apuracaoDelivery = new LineChartSeries();
        apuracaoDelivery.setLabel("Apuração Delivery");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Pedido p : pedidos) {
            if (p instanceof PedidoLocal) {
                apuracaoLocal.set(sdf.format(p.getDataHora()), p.getValorTotal());
            } else {
                apuracaoDelivery.set(sdf.format(p.getDataHora()), p.getValorTotal());
            }
        }

        lineChartModel.addSeries(apuracaoLocal);
        lineChartModel.addSeries(apuracaoDelivery);

        lineChartModel.setTitle("Apuração Anual");
        lineChartModel.setZoom(true);
        lineChartModel.setLegendPosition("");

        DateAxis dateAxis = new DateAxis("Data");
        dateAxis.setTickAngle(-50);
        dateAxis.setMin(selectedYear + "-01-01");
        dateAxis.setMax(selectedYear + "-12-31");
        dateAxis.setTickFormat("%d/%#m/%y");

        Axis yAxis = lineChartModel.getAxis(AxisType.Y);
        yAxis.setTickFormat("%.2f");
        yAxis.setLabel("Valor R$");

        lineChartModel.getAxes().put(AxisType.X, dateAxis);
    }

    private void setTimeLine() {
        timeLineModel = new TimelineModel();
        String prexf = "";
        for (Pedido p : pedidos) {
            if(p instanceof PedidoLocal) {
                prexf = "PL: ";
            }else {
                prexf = "PD: ";
            }
            timeLineModel.add(new TimelineEvent(
                    prexf + p.getId().toString(),
                    p.getDataHora())
            );
        }
    }

    private void createTodayBarChart() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        double vendasLocal = 0;
        double vendaseDelivery = 0;

        for (Pedido p : pedidos) {
            if (sdf.format(p.getDataHora()).equals(sdf.format(new Date()))) {
                if (p instanceof PedidoLocal) {
                    vendasLocal += p.getValorTotal();
                } else {
                    vendaseDelivery += p.getValorTotal();
                }
            }
        }

        chartModel = new GChartModelBuilder().setChartType(getChartType())
                .addColumns("Pedido", "R$").addRow("Pedido Local", vendasLocal)
                .addRow("Pedido Delivery", vendaseDelivery).build();
    }

    public LineChartModel getLineChartModel() {
        return lineChartModel;
    }

    private void setDefaultSelectedYear() {
        Calendar cal = Calendar.getInstance();
        selectedYear = "" + cal.get(Calendar.YEAR);
    }

    public String getSelectedYear() {
        return selectedYear;
    }

    public void setSelectedYear(String selectedYear) {
        this.selectedYear = selectedYear;
    }

    public GChartModel getChartModel() {
        return chartModel;
    }

    public GChartType getChartType() {
        return chartType;
    }

    public void setChartType(GChartType chartType) {
        this.chartType = chartType;
    }

    public TimelineModel getTimeLineModel() {
        return timeLineModel;
    }
}
