package part3.effects;

/**
 * Created by shiqing on 18-1-3.
 */
public class Ambiguous {

    interface A {
        default void hello() {
            System.out.println("Hello from A");
        }
    }

    interface B {
        default void hello() {
            System.out.println("Hello from B");
        }
    }

    static class C implements A, B {
        @Override
        public void hello() {
            A.super.hello();
        }
    }


    public static void main(String[] args) {
        new C().hello();
    }









}
