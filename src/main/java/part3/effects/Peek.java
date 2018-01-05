package part3.effects;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by shiqing on 18-1-3.
 */
public class Peek {

    public static void main(String[] args) {
        List<Integer> results = Stream.of(3, 4, 5, 6)
                .peek(x -> System.out.printf("taking from stream: %d\n", x))
                .map(x -> x + 17)
                .peek(x -> System.out.printf("after map: %d\n", x))
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.printf("after filter: %d\n", x))
                .limit(4)
                .peek(x -> System.out.printf("after limit: %d\n", x))
                .collect(Collectors.toList());
        System.out.println(results);
    }




}
