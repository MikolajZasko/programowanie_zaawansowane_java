package lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Paragraph {

    String content;

    public Paragraph (String newContent) {
        setContent(newContent);
    }

    Paragraph setContent(String newContent) {
        this.content = newContent;

        return this;
    };

    void writeHTML(PrintStream out) {
        out.print("<p>\n");
        out.printf("%s", this.content);
        out.print("</p>\n");
    };
}
