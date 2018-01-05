package part3.effects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by shiqing on 18-1-3.
 */
public class ObserverMain {


    public static void main(String[] args) {
        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new LeMonde());
        f.notifyObserver("The queen said her favourite book is Java 8 in Action!");


        Feed feedLam = new Feed();
        feedLam.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! " + tweet);
            }
        });

        feedLam.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("Yet another news in London... " + tweet);
            }
        });

        feedLam.registerObserver(tweet -> {
            if (Objects.nonNull(tweet) && tweet.contains("wine")) {
                System.out.println("Today cheese, wine and news! " + tweet);
            }
        });

        feedLam.notifyObserver("Money money money, give me money!");

    }


    interface Observer {
        void inform(String message);
    }

    interface Subject {
        void registerObserver(Observer o);

        void notifyObserver(String tweet);
    }


    private static class NYTimes implements Observer {
        @Override
        public void inform(String message) {
            if (Objects.nonNull(message) && message.contains("money")) {
                System.out.println("Breaking news in NY!" + message);
            }
        }
    }

    private static class Guardian implements Observer {
        @Override
        public void inform(String message) {
            if (Objects.nonNull(message) && message.contains("queen")) {
                System.out.println("Yet another news in London... " + message);
            }
        }
    }

    private static class LeMonde implements Observer {
        @Override
        public void inform(String message) {
            if (Objects.nonNull(message) && message.contains("wine")) {
                System.out.println("Today cheese, wine and news! " + message);
            }
        }
    }

    private static class Feed implements Subject {
        private final List<Observer> observers = new ArrayList<>();

        @Override
        public void registerObserver(Observer o) {
            observers.add(o);
        }

        @Override
        public void notifyObserver(String tweet) {
            observers.forEach(o -> o.inform(tweet));
        }
    }


}
