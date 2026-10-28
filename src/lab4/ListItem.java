package lab4;

import java.io.PrintStream;

public class ListItem {
    String content;

    public ListItem(String newContent) {
        this.content = newContent;
    }

    void writeHTML(PrintStream out) {
        out.printf("<li>%s</li>\n", this.content);
    }
}
