package part3.effects;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by shiqing on 18-1-3.
 */
public class FactoryMain {


    public static void main(String[] args) {
        Product loan = ProductFactory.createProduct("loan");
        Supplier<Product> stock = Stock::new;
        Product supplierStock = stock.get();
        Product bond = ProductFactory.createProductLambdas("bond");
        System.out.println(loan);
        System.out.println(supplierStock);
        System.out.println(bond);
    }


    private static final Map<String, Supplier<Product>> map = new HashMap<>(17);

    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }

    protected static class ProductFactory {

        public static Product createProduct(String name) {
            switch (name) {
                case "loan":
                    return new Loan();
                case "stock":
                    return new Stock();
                case "bond":
                    return new Bond();
                default:
                    throw new RuntimeException("No such product " + name);
            }
        }

        public static Product createProductLambdas(String name) {
            Supplier<Product> productSupplier = map.get(name);
            if (Objects.nonNull(productSupplier)) {
                return productSupplier.get();
            }
            throw new RuntimeException("No such product " + name);
        }


    }


    interface Product {
    }

    static class Loan implements Product {
    }

    static class Stock implements Product {
    }

    static class Bond implements Product {
    }


}
