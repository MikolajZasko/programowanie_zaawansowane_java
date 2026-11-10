package lab6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {

    static List<String> file_names = List.of(
            "accelerator.csv",
            "elec.csv",
            "missing-values.csv",
            "no-header.csv",
            "with-header.csv"
    );

    static List<String> get_file_names() {
        return file_names;
    }

    static String data_dir_path = "data/lab5/";

    @ParameterizedTest
    @MethodSource("get_file_names")
    void doubleRead(String file_name) throws IOException {
        CSVReader reader = new CSVReader(data_dir_path + file_name,";",true);

        // print the headers
        for (String label: reader.columnLabels) {
            System.out.printf(Locale.US,"%s ",label);
        }
        System.out.print("\n");

        while(reader.next()){

            // print the whole row
            for (String label: reader.columnLabels) {
                double val = reader.getDouble(label);

                System.out.printf(Locale.US,"%f ",val);
            }
            System.out.print("\n");
        }
    }


    @Test
    void testByHand1() throws IOException {
        String file_path = data_dir_path + file_names.getLast();

        CSVReader reader = new CSVReader(file_path,";",true);

        // print the headers
        for (String label: reader.columnLabels) {
            System.out.printf(Locale.US,"%s ",label);
        }
        System.out.print("\n");

        while(reader.next()) {
            System.out.printf(Locale.US,"%s: %s \n",reader.columnLabels.get(0),reader.getInt(0));
            System.out.printf(Locale.US,"%s: %s \n",reader.columnLabels.get(1),reader.get(1));
            System.out.printf(Locale.US,"%s: %s \n",reader.columnLabels.get(2),reader.get(2));
            System.out.printf(Locale.US,"%s: %s \n",reader.columnLabels.get(3),reader.get(3));
            System.out.printf(Locale.US,"%s: %s \n",reader.columnLabels.get(4),reader.getInt(4));
            System.out.printf(Locale.US,"%s: %s \n",reader.columnLabels.get(5),reader.getInt(5));
        }

    }

    @Test
    void testByHand2() throws IOException {
        String file_path = data_dir_path + file_names.getLast();

        CSVReader reader = new CSVReader(file_path,";",true);

        // print the headers
        for (String label: reader.columnLabels) {
            System.out.printf(Locale.US,"%s ",label);
        }
        System.out.print("\n");

        ArrayList<String> missing_vals = new ArrayList<>(List.of());

        int row_index = 0;

        while(reader.next()) {
            // Index 0
            try {
                System.out.printf(Locale.US, "%s: %s \n", reader.columnLabels.get(0), reader.getInt(0));
            } catch (Exception e) {
                System.out.print(" \n");
                String message = "at " + row_index + " index in col " + 0 + " val was not found";
                missing_vals.add(message);
            }

// Index 1
            try {
                System.out.printf(Locale.US, "%s: %s \n", reader.columnLabels.get(1), reader.get(1));
            } catch (Exception e) {
                System.out.print(" \n");
                String message = "at " + row_index + " index in col " + 1 + " val was not found";
                missing_vals.add(message);
            }

// Index 2
            try {
                System.out.printf(Locale.US, "%s: %s \n", reader.columnLabels.get(2), reader.get(2));
            } catch (Exception e) {
                System.out.print(" \n");
                String message = "at " + row_index + " index in col " + 2 + " val was not found";
                missing_vals.add(message);
            }

// Index 3
            try {
                System.out.printf(Locale.US, "%s: %s \n", reader.columnLabels.get(3), reader.get(3));
            } catch (Exception e) {
                System.out.print(" \n");
                String message = "at " + row_index + " index in col " + 3 + " val was not found";
                missing_vals.add(message);
            }

// Index 4
            try {
                System.out.printf(Locale.US, "%s: %s \n", reader.columnLabels.get(4), reader.getInt(4));
            } catch (Exception e) {
                System.out.print(" \n");
                String message = "at " + row_index + " index in col " + 4 + " val was not found";
                missing_vals.add(message);
            }

// Index 5
            try {
                System.out.printf(Locale.US, "%s: %s \n", reader.columnLabels.get(5), reader.getInt(5));
            } catch (Exception e) {
                System.out.print(" \n");
                String message = "at " + row_index + " index in col " + 5 + " val was not found";
                missing_vals.add(message);
            }
            row_index++;
        }

    }

    @Test
    void next() {
    }
}