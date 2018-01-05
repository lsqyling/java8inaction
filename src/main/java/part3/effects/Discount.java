package part3.effects;

import model.Quote;
import model.Code;

import static util.Util.delay;
import static util.Util.format;

/**
 * Created by shiqing on 18-1-5.
 */
public class Discount {



    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }
    public static double apply(double price, Code code) {
        delay();
        return format(price * (100 - code.percentage) / 100);
    }



}
