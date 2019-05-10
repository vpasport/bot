package Ivi;

import Bot.Bot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Film {
    private Document document;
    String name;

    public Film(){
        connect();
    }

    public Film( String name ){
        this.name = name;
        System.out.println(this.name);
        connect();
    }

    private void connect() {
        try {
            document = Jsoup.connect("https://www.surgebook.com/Lina_Kims/book/lazurit").get();
            System.out.println(document);
            Elements elements = document.getElementsByAttributeValue("tabindex", "-1");
            System.out.println(elements.text());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getTitle(){
        Elements elements = document.getElementsByClass("title-block");
        return  elements.text();
    }

    public String getRating(){
        Elements elements = document.getElementsByAttributeValue("itemprop", "ratingValue");
        return elements.text();
    }

    public String getDescription(){
        Elements element = document.getElementsByClass("description");
        return element.text();
    }

    public String getTags(){
        Elements elements = document.getElementsByAttributeValue("itemprop", "genre");
        return elements.text();
    }

    public String getActors(){
        Elements elements = document.getElementsByAttributeValue("itemprop", "actor");
        return elements.text();
    }

    public String getImg(){
        Elements elements = document.getElementsByAttributeValue("itemprop", "image");
        return  "http:" + elements.attr("src");
    }
}
