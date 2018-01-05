package part3.effects;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Created by shiqing on 18-1-3.
 */
public class ChainOfResponsibilityMain {


    public static void main(String[] args) {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);

        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result);
        UnaryOperator<String> headerProcessing = text -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing = text -> text.replaceAll("labda", "lambdas");
        Function<String, String> pipeLine = headerProcessing.andThen(spellCheckerProcessing);
        String lineResult = pipeLine.apply("Aren't labdas really sexy?!!");
        System.out.println(lineResult);
    }




    protected static abstract class ProcessingObject<T> {
        protected ProcessingObject<T> successor;

        public ProcessingObject() {
        }

        public void setSuccessor(ProcessingObject<T> successor) {
            this.successor = successor;
        }

        public ProcessingObject(ProcessingObject<T> successor) {
            this.successor = successor;
        }

        protected abstract T handleWork(T input);

        public T handle(T input) {
            T t = handleWork(input);
            if (Objects.nonNull(successor)) {
                return successor.handle(t);
            }
            return t;
        }

    }

    private static class HeaderTextProcessing extends ProcessingObject<String> {
        public HeaderTextProcessing(ProcessingObject<String> successor) {
            super(successor);
        }

        public HeaderTextProcessing() {}

        @Override
        protected String handleWork(String input) {
            return "From Raoul, Mario and Alan: " + input;
        }
    }


    private static class SpellCheckerProcessing extends ProcessingObject<String> {

        public SpellCheckerProcessing() {}

        public SpellCheckerProcessing(ProcessingObject<String> successor) {
            super(successor);
        }

        @Override
        protected String handleWork(String input) {
            return input.replaceAll("labda", "lambdas");
        }
    }









}
