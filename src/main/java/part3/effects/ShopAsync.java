package part3.effects;

import util.Util;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by shiqing on 18-1-3.
 */
public class ShopAsync {

    private final String name;
    private final Random random;

    public ShopAsync(String name) {
        this.name = name;
        this.random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public Future<Double> getPrice(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        Util.delay();
        if (true) throw new RuntimeException("Product not available");
        return Util.format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }


}
