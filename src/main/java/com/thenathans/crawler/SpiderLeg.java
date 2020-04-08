package com.thenathans.crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

public class SpiderLeg {

    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<String> childLinks = new LinkedList<String>();
    private Document htmlDocument;


    public void crawl(String nextUrl){

        try{

            Connection connection = Jsoup.connect(nextUrl).userAgent(USER_AGENT);
            this.htmlDocument = connection.get();
            System.out.println("received web page at "+nextUrl);

            Elements linksOnPage = this.htmlDocument.select("a[href]");

            System.out.println(String.format("Found %s links on page %s", linksOnPage.size(),nextUrl));

            for(Element element : linksOnPage){
                this.childLinks.add(element.absUrl("href"));
            }

        }catch(Exception ex){
            System.out.println("Something went wrong...." + ex.getMessage());
        }
    }

    public boolean searchForWord(String word){
        boolean foundWord = false;

        System.out.println("Searching for word " + word);
        String bodyText = this.htmlDocument.body().text();
        foundWord = bodyText.toLowerCase().contains(word.toLowerCase());

        return foundWord;
    }

    public List<String> getLinks() {
        return this.childLinks;
    }
}
