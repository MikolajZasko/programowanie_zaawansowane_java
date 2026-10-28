package lab4;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphTest {

    @Test
    void setContent() {
    }

    @Test
    public void writeHTML() throws Exception {
        String paragraphContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer massa justo, posuere vitae neque a, placerat sodales augue. Aenean interdum consequat euismod. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam molestie efficitur purus ut vestibulum. Curabitur volutpat dignissim nisi in tempor. Nullam nec condimentum arcu, ut luctus est. Curabitur maximus, odio vitae pellentesque interdum, augue mi sollicitudin leo, ullamcorper bibendum urna odio sed ante. Integer elementum commodo justo a vestibulum. Ut nisl eros, porttitor et aliquet nec, feugiat eu quam. Vivamus ac eros dignissim, pellentesque ligula quis, eleifend lacus. Cras eu fermentum nisi. Nullam lobortis ante eget elit fringilla, in pretium est ornare.";
        // Utwórz strumień zapisujący w pamięci
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        // Utwórz obiekt i zapisz do strumienia
        new Paragraph(paragraphContent).writeHTML(ps);
        String result = null;
        // Pobierz jako String
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(result);

        // Sprawdź, czy result zawiera wybrane elementy
        assertTrue(result.contains("<p>"));
        assertTrue(result.contains("</p>"));
        assertTrue(result.contains("Lorem ipsum"));
    }
}