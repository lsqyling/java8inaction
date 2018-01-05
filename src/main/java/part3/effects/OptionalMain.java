package part3.effects;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Created by shiqing on 18-1-3.
 */
public class OptionalMain {

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }



}
