package lab6;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {

    @Test
    void initialTest() {
        CSVReader reader = new CSVReader("titanic-part.csv",",",true);
        while(reader.next()){
            int id = reader.getInt("PassengerId");
            String name = reader.get("Name");
            double fare = reader.getDouble("Fare");

            System.out.printf(Locale.US,"%d %s %d",id, name, fare);
        }
    }


    @Test
    void parseHeader() {
    }

    @Test
    void next() {
    }
}