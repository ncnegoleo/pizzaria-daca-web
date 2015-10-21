package br.edu.ifpb.daca.validation;

public class AttributeValidator {

    public static boolean stringRegexValidator(String attr, String regex) {
        if (isNullValidator(attr)) {
            return false;
        }
        Validator<String> validator = s -> s.trim().matches(regex);
        return validator.vaidate(attr);
    }

    public static boolean isNullValidator(Object... objects) {
        for (Object o : objects) {
            return o == null;
        }
        
        return false;
    }
}
