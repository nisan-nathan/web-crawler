package com.thenathans.crawler;

/**
 * Using code snippets as provided in http://www.netinstructions.com/how-to-make-a-simple-web-crawler-in-java/
 *
 */

import java.util.*;

public class Spider {
    private static final int MAXIMUM_NUMBER_OF_PAGES_TO_VISIT = 10;
    private Set<String> pagesVisited = new HashSet<String>();
    private List<String> pagesToVisit = new LinkedList<String>();

    public void search(String url, String searchWord) {

        while(this.pagesVisited.size() < MAXIMUM_NUMBER_OF_PAGES_TO_VISIT){
            String currentUrl;
            SpiderLeg spiderLeg = new SpiderLeg();

            if(this.pagesToVisit.isEmpty()){
                currentUrl = url;
                this.pagesVisited.add(url);
            }
            else {
                currentUrl = this.getNextUrl();
            }

            spiderLeg.crawl(currentUrl);
            boolean success = spiderLeg.searchForWord(searchWord);
            if(success){
                System.out.println(String.format("Successfully found %s at url %s", searchWord, currentUrl));
                break;
            }

            this.pagesToVisit.addAll(spiderLeg.getLinks());
        }

        System.out.println(String.format("Completed search...Visited %s website(s)",this.pagesToVisit.size()));
    }

    private String getNextUrl() {
        String nextUrl;

        do {
            nextUrl = pagesToVisit.remove(0);
        } while (pagesVisited.contains(nextUrl));

        if(nextUrl!=null){
            pagesVisited.add(nextUrl);
        }

        return nextUrl;
    }

}
