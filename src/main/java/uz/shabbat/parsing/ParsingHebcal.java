package uz.shabbat.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import uz.shabbat.Shabbat;

import java.io.IOException;
/**
 @author Jebrak Semyon
 @version 1.0
 */
public class ParsingHebcal implements Parsing{
    @Override
    public Shabbat getShabat(String geoID, String driverPath) throws IOException {
        String url = "https://www.hebcal.com/shabbat?geonameid=" + geoID + "&b=18&M=off&m=20&lg=s";
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201")
                .referrer("http://www.google.com")
                .timeout(10 * 1000)
                .get();

        Element ListNews = doc.selectFirst("ul.hebcal-results.list-unstyled");
        Element dateElement = doc.selectFirst("li#20230630-candle-lighting.candles");
        String date = doc.selectFirst("li#20230630-candle-lighting.candles").selectFirst("span").text();
        String candleLighting = dateElement.selectFirst("strong").text();
        String parashat = ListNews.selectFirst("li.parashat").selectFirst("a").text();
        String havdalah = ListNews.selectFirst("li.havdalah").selectFirst("strong").text();
        String city = doc.selectFirst("div.col-md-8").selectFirst("h2").text().split(" ")[3];

        return new Shabbat(date, parashat, candleLighting, havdalah, city, "");
    }
}
