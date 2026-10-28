package lab4;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();
    PrintStream out;

    public Document(String newTitle, PrintStream newOut, String photoUrl) {
        this.title = newTitle;
        this.out = newOut;
        this.photo = new Photo(photoUrl);
    }

    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl){
        photo.url = photoUrl;

        return this;
    }

    Section addSection(String sectionTitle){
        // utwórz sekcję o danym tytule i dodaj do sections

        Section newSection = new Section(sectionTitle);

        sections.add(newSection);

        return newSection;
    }
    Document addSection(Section newSection){

        sections.add(newSection);

        return this;
    }


    void writeHTML(PrintStream out){

        out.print("<!DOCTYPE html>\n");

        out.print("<html>\n");

        out.print("<head>\n");
        out.printf("<title>%s</title>", this.title);
        out.print("</head>\n");

        out.print("<body>\n");

        out.printf("<h1>%s</h1>", this.title);

        photo.writeHTML(out);

        for (Section section : this.sections) {
            section.writeHTML(out);
        }

        out.print("</body>\n");

        out.print("</html>\n");
    }

    void WriteToFile(String filename) {
        try (PrintStream out = new PrintStream(filename)) {
            writeHTML(out);
            System.out.println("HTML saved to file successfully!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}