package lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    String title;
    List<Paragraph> paragraphs = new ArrayList<>();

    public Section(String newTitle) {
        setTitle(newTitle);
    }

    Section setTitle(String newTitle){
        this.title = newTitle;

        return this;
    }

    Section addParagraph(String paragraphText){
        paragraphs.add(new Paragraph(paragraphText));

        return this;
    }

    Section addParagraph(Paragraph p){
        paragraphs.add(p);

        return this;
    }

    void writeHTML(PrintStream out){
        out.print("<div>\n");

        for (Paragraph paragraph : this.paragraphs) {
            paragraph.writeHTML(out);
        }

        out.print("</div>\n");
    }
}
