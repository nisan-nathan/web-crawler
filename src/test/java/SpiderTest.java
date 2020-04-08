import com.thenathans.crawler.Spider;

public class SpiderTest {

    public static void main(String[] args){
        Spider spider = new Spider();
        spider.search("https://www.hotukdeals.com/search?q=xbox", "xbox");
    }
}
