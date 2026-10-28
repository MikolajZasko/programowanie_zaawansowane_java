package lab4;

import java.io.PrintStream;

public class Main {
    public static void main() {

        PrintStream out = System.out;

        Document cv = new Document("sas",out,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSKRyzzog1DNrV7hpE6A8AZFb7yoyO3YMixsY9D_DZ9p3AfY2RKp4H9u8CL&s=10");

        cv.setPhoto("https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Calico_tabby_cat_-_Savannah.jpg/1200px-Calico_tabby_cat_-_Savannah.jpg");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph(
                        new ParagraphWithList("Kursy")
                                .setContent("Kursy")
                                .addListItem("Języka Angielskiego")
                                .addListItem("Języka Hiszpańskiego")
                                .addListItem("Szydełkowania")
                );
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList("Znane technologie")
                                .setContent("Znane technologie")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                );

        cv.WriteToFile("test.html");
        cv.writeHTML(out);
    }
}
