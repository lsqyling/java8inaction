package part3.effects;

import model.Quote;
import model.Shop;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by shiqing on 18-1-5.
 */
public class BestPriceFinder {

    private final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy"));

    private final Executor executor = Executors.newFixedThreadPool(shops.size(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    public List<String> findPriceSequential(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    public List<String> findPriceParallel(String product) {
        return shops.stream().parallel()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    public List<String> findPriceFuture(String product) {
        List<CompletableFuture<String>> priceFutures = findPriceStream(product).collect(Collectors.toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    private Stream<CompletableFuture<String>> findPriceStream(String product) {

        Stream<CompletableFuture<String>> futureStream = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
        return futureStream;
    }

    public void printPriceStream(String product) {
        long start = System.nanoTime();
        CompletableFuture[] futures = findPriceStream(product).map(f -> f.thenAccept(s -> System.out.printf("%s done in %d msecs.\n", s, (System.nanoTime() - start) / 1_000_000)))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
    }


}
