package lab4;

import java.io.PrintStream;

public class ParagraphWithList extends Paragraph {

    UnorderedList items = new UnorderedList();

    public ParagraphWithList(String newTitle) {
        super(newTitle);
    }

    ParagraphWithList addListItem(String text) {
        items.addListItem(text);

        return this;
    }

    ParagraphWithList setContent(String newContent) {
        this.content = newContent;

        return this;
    };

    void writeHTML(PrintStream out) {
        super.writeHTML(out);
        items.writeHTML(out);
    }
}
