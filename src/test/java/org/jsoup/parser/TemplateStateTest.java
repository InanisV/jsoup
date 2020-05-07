package org.jsoup.parser;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;

public class TemplateStateTest {
    @Test
    public void test1() throws IOException {
        String temp =
                "<template id=\"lorem-ipsum\">\n" +
                        "  <tr>\n" +
                        "    <td>Lorem</td>\n" +
                        "    <td>Ipsum</td>\n" +
                        "  </tr>\n" +
                        "</template>";
        Document doc = Jsoup.parse(temp);
        System.out.println(doc.outerHtml());
        doc.outputSettings().prettyPrint(false);
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        System.out.println(doc.outerHtml());
       assertEquals("<html><head></head><body><template id=\"lorem-ipsum\">\n" +
               "  </template></body><tbody><tr>\n" +
               "    <td>Lorem</td>\n" +
               "    <td>Ipsum</td>\n" +
               "  </tr>\n" +
               "</tbody></html>", doc.outerHtml());
    }

    @Test
    public void test2() throws IOException {
        String temp =
                "<template id=\"lorem-ipsum\">\n" +
                        "  <p>\n" +
                        "</template>";

        Document doc = Jsoup.parse(temp);
        doc.outputSettings().prettyPrint(false);
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        System.out.print(doc.outerHtml());

    }
}
