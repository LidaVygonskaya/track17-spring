package track.container;

import track.container.beans.Car;
import track.container.config.Bean;
import track.container.config.ConfigReader;
import track.container.config.InvalidConfigurationException;

import java.io.File;
import java.util.List;

/**
 *
 */
public class Main {

    public static void main(String[] args) throws InvalidConfigurationException {

        /*

        ПРИМЕР ИСПОЛЬЗОВАНИЯ

         */

        ConfigReader reader = new JsonConfigReader();
        List<Bean> beans = reader.parseBeans(new File("config.json"));
        Container container = new Container(beans);
        Car car = null;
        try {
            car = (Car) container.getByClass("track.container.beans.Car");
            car = (Car) container.getById("carBean");
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }
}
