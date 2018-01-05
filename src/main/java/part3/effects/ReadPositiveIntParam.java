package part3.effects;

import java.util.Optional;
import java.util.Properties;

import static java.util.Optional.ofNullable;

/**
 * Created by shiqing on 18-1-3.
 */
public class ReadPositiveIntParam {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");

        System.out.println(readDurationWithOptional(props, "a"));
    }


    public static Optional<Integer> s2i(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static int readDurationWithOptional(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(ReadPositiveIntParam::s2i)
                .filter(i -> i > 0)
                .orElse(0);
    }




}
