package lab6;

import java.io.*;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String,Integer> columnLabelsToInt = new HashMap<>();

    String[] current;

    /**
     *
     * @param filename - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     */

    public CSVReader(String filename,String delimiter,boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader)parseHeader();
    }

    public CSVReader(String filename,String delimiter) throws IOException {

        // assume we have a header and - ???
        this(filename, delimiter, true);
    }

    public CSVReader(String filename) throws IOException {

        // assume we have a header and the delimiter is "," - ???
        this(filename, ",", true);
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader = (BufferedReader) reader;
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader)parseHeader();
    }

    void parseHeader() throws IOException {
        // wczytaj wiersz
        String line = reader.readLine();
        if (line == null) {
            return;
        }

        // podziel na pola
        String[] header = line.split(delimiter);

        // this is more efficient (i think - BUT works only for lists/arrays, not maps)
        // Collections.addAll(columnLabels, header);

        for (int i = 0; i < header.length; i++) {
            // dodaj nazwy kolumn do columnLabels i numery do columnLabelsToInt
            //
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i], i);
        }
    }

    boolean next() throws IOException {
        // czyta następny wiersz, dzieli na elementy i przypisuje do current
        //
        // wczytaj wiersz
        String line = reader.readLine();
        if (line == null) {
            return false;
        }

        current = line.split(delimiter);

        return true;
    }
}
