package part3.effects;

import java.util.function.Consumer;

/**
 * Created by shiqing on 18-1-3.
 */
public class OnlineBankingLambdas {

    static class Customer {}

    static class Database {
        static Customer getCustomerWithId(int id) {
            return new Customer();
        }
    }


    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }


    public static void main(String[] args) {
        new OnlineBankingLambdas().processCustomer(1337, customer -> System.out.println("hello!"));
    }



}
