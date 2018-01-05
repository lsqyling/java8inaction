package part3.effects;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by shiqing on 18-1-5.
 */
public class BestPriceFinderMain {


    private static BestPriceFinder bestPriceFinder = new BestPriceFinder();

    public static void main(String[] args) {
        execute("sequential", () -> bestPriceFinder.findPriceSequential("myPhone27S"));
        execute("parallel", () -> bestPriceFinder.findPriceParallel("myPhone27S"));
        execute("composed CompletableFuture", () -> bestPriceFinder.findPriceFuture("myPhone27S"));
        bestPriceFinder.printPriceStream("myPhone27S");
    }

    private static void execute(String msg, Supplier<List<String>> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }



}
