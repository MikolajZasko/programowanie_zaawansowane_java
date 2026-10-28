package lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    List<ListItem> listElements = new ArrayList<>();

    public UnorderedList() {};

    UnorderedList addListItem (String newContent) {
        listElements.add(new ListItem(newContent));

        return this;
    }

    void writeHTML(PrintStream out) {
        out.print("<ul>\n");

        for (ListItem item : this.listElements) {
            item.writeHTML(out);
        }

        out.print("</ul>\n");
    }
}
