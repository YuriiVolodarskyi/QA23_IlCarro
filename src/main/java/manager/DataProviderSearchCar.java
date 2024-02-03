package manager;

import models.Car;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderSearchCar {

    @DataProvider
    public Iterator<Object[]> example() {
        List<Object[]> list = new ArrayList<>();
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> searchAnyPeriodSuccess() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Rehovot, Israel", "2/8/2024", "2/11/2024"});
        list.add(new Object[]{"Tel-Aviv, Israel", "6/2/2024", "12/22/2024"});
        list.add(new Object[]{"Tel-Aviv, Israel", "9/3/2024", "1/13/2025"});
        list.add(new Object[]{"Rehovot, Israel", "1/4/2025", "1/14/2025"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> searchAnyPeriodSuccessFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/SearchCar.csv"));
        String line = reader.readLine();
        while (line != null) {
            String [] all = line.split(",");
            list.add(new Object[]{all[0], all[1], all[2]});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> searchWrongPeriod() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Rehovot, Israel", "1/1/2024", "2/11/2024"});
      /*  list.add(new Object[]{"Tel-Aviv, Israel", "6/2/2024", "12/22/2024"});
        list.add(new Object[]{"Tel-Aviv, Israel", "9/3/2024", "1/13/2025"});
        list.add(new Object[]{"Rehovot, Israel", "1/4/2025", "1/14/2025"});
       */
        return list.iterator();
    }
}
