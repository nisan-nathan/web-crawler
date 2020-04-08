import com.thenathans.crawler.SpiderHotDealEntity;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CustomTest {
    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";

    public static void main(String[] args){
        {
            String hottestDeal;
            SpiderHotDealEntity spiderHotDealEntity = new SpiderHotDealEntity();
            try{

                Connection connection = Jsoup.connect("https://www.hotukdeals.com/search?q=xbox").userAgent(USER_AGENT);
                Document htmlDocument = connection.get();

                Elements articlesOnPage = htmlDocument.select("article");

                for(Element article : articlesOnPage){
                    System.out.println(article.text());
                    if(!article.text().toLowerCase().contains("expired")){
                        int heat = Integer.parseInt(article.text().substring(0, article.text().indexOf("°")));

                        if(heat > spiderHotDealEntity.getHotTemp()){
                            spiderHotDealEntity.setHotTemp(heat);
                            spiderHotDealEntity.setHottestDeal(article.text());
                        }
                    }

                }

                System.out.println(String.format("Hottest deal is %s and the details are %s",spiderHotDealEntity.getHotTemp(),spiderHotDealEntity.getHottestDeal()));

            }catch(Exception ex){
                System.out.println("Something went wrong...." + ex.getMessage());
            }
        }
    }
}
