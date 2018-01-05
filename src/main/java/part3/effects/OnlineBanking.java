package part3.effects;

/**
 * Created by shiqing on 18-1-3.
 */
public abstract class OnlineBanking {

    static class Customer {}

    static class Database {
        static Customer getCustomerWithId(int id) {
            return new Customer();
        }
    }


    public void processCustomer(int id) {
        Customer customer = Database.getCustomerWithId(id);
        makeCustomerHappy(customer);
    }

    abstract void makeCustomerHappy(Customer customer);


}
