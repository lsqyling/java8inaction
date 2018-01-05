package part3.effects;

/**
 * Created by shiqing on 18-1-3.
 */
public class StrategyMain {

    public static void main(String[] args) {
//        old school
        Validator validator = new Validator(new IsNumeric());
        System.out.println(validator.validate("aaaaa"));
        Validator v2 = new Validator(new IsAllLowerCase());
        System.out.println(v2.validate("bbbbbbb"));
//        with lambdas
        Validator v3 = new Validator(s -> s.matches("[a-z]+"));
        System.out.println(v3.validate("aaaaaaa"));
        Validator v4 = new Validator(s -> s.matches("\\d+"));
        System.out.println(v4.validate("bbbbbbb"));

    }

    interface ValidationStrategy {
        boolean execute(String s);
    }

    static class IsAllLowerCase implements ValidationStrategy {
        @Override
        public boolean execute(String s) {
            return s.matches("[a-z]+");
        }
    }

    static class IsNumeric implements ValidationStrategy {
        @Override
        public boolean execute(String s) {
            return s.matches("\\d+");
        }
    }

    static class Validator {
        private final ValidationStrategy strategy;

        public Validator(ValidationStrategy strategy) {
            this.strategy = strategy;
        }

        public boolean validate(String s) {
            return strategy.execute(s);
        }

    }




}
