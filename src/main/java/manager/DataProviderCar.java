package manager;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderCar {

    @DataProvider
    public Iterator<Object[]> example() {
        List<Object[]> list = new ArrayList<>();
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> searchAnyPeriodSuccess() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Rehovot, Israel", "2/1/2024", "2/11/2024"});
        list.add(new Object[]{"Tel-Aviv, Israel", "6/2/2024", "12/22/2024"});
        list.add(new Object[]{"Tel-Aviv, Israel", "9/3/2024", "1/13/2025"});
        list.add(new Object[]{"Rehovot, Israel", "1/4/2025", "1/14/2025"});
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
