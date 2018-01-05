package part3.effects;

import java.util.concurrent.Future;

/**
 * Created by shiqing on 18-1-4.
 */
public class AsyncShopClient {

    public static void main(String[] args) {
        ShopAsync bestShop = new ShopAsync("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = bestShop.getPrice("myPhone");
        long invocation = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Invocation returned after %d msecs\n", invocation);

        try {
            System.out.println("Price is " + futurePrice.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long retrivalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Price returned after %d msecs.\n", retrivalTime);


    }






}
