package org.jsoup.nodes;


import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.parser.XmlTreeBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SpecialCaseXMLTest {
    @Test
    public void testSpecialXmlParse() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "    <head>\n" +
                "        <title>normal &lt;3 <script>alert()</script> <b>bold</b>!</title>\n" +
                "    </head>\n" +
                "</html>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Parser parser = new Parser(tb);
        Document doc = parser.parseInput(xml, "");
        assertEquals("normal <3 !",
                TextUtil.stripNewlines(doc.title()));

    }

    @Test
    public void testNormalCaseXmlParse() {
        String url = "https://www.bilibili.com/";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.113 Safari/537.36";
        String text;
        try {
            text = Jsoup.connect(url).userAgent(userAgent).get().toString();
            XmlTreeBuilder tb = new XmlTreeBuilder();
            Parser parser = new Parser(tb);
            Document doc = parser.parseInput(text, "");
            assertEquals("哔哩哔哩 (゜-゜)つロ 干杯~-bilibili", TextUtil.stripNewlines(doc.title()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testNoTitleCaseXmlParse(){
        String url = "https://www.bilibili.com/";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.113 Safari/537.36";
        String text;
        try {
            text = Jsoup.connect(url).userAgent(userAgent).get().text();
            XmlTreeBuilder tb = new XmlTreeBuilder();
            Parser parser = new Parser(tb);
            Document doc = parser.parseInput(text, "");
            assertEquals("", TextUtil.stripNewlines(doc.title()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
