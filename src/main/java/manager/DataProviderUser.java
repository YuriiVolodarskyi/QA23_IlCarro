package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {

    @DataProvider
    public Iterator<Object[]> example() {
        List<Object[]> list = new ArrayList<>();
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginSuccess() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"alimych65@gmail.com", "Yv030665!"});
        list.add(new Object[]{"alimych65@gmail.com", "Yv030665!"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/data.csv"));
        String line = reader.readLine();
        while (line != null) {
            String [] all = line.split(",");
            list.add(new Object[]{new User().withEmail(all[0]).withPassword(all[1])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginModelSuccess() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"alimych65@gmail.com", "Yv030665!"});
      //list.add(new Object[]{"sonya@gmail.com", "Aa123456&"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginWrongEmail() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"ch65gmail.com", "Yv030665!"});
        //list.add(new Object[]{"ych65@gmailcom", "Aa123456&"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginWrongPassword() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"mych65@gmail.com", "Yv030665"});
        list.add(new Object[]{"ych65@gmailcom", "a123456&"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginUnregisteredUser() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"mych@65gmail.com", "Yv030665$"});
        list.add(new Object[]{"ych65@gmailcom", "Aa123456&"});
        return list.iterator();
    }
}
