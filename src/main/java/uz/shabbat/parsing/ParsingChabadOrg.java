package uz.shabbat.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import uz.shabbat.Shabbat;

import java.io.File;
import java.util.List;
/**
 @author Jebrak Semyon
 @version 1.0
 */

public class ParsingChabadOrg implements Parsing{
    private final String driverPath;

    public ParsingChabadOrg(String driverPath) {
        this.driverPath = driverPath;
    }

    @Override
    public Shabbat getShabat(String geoID) {
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .withLogFile(new File("logs/slenium.log"))
                .build();
        WebDriver webDriver = new ChromeDriver(service, options);
        webDriver.get("https://ru.chabad.org/calendar/candlelighting_cdo/locationid/"+geoID+"/locationtype/1");
        Document doc = Jsoup.parse(webDriver.getPageSource());
        String city = doc.selectFirst("span.js-title-city").text();
        String parashat = doc.selectFirst("span.link.a_underline_off").text().split(" ")[3];
        List<String> allDate =  List.of(doc.select("div.secular_date").text().split(" "));
        List<String> allDateJewish = List.of(doc.select("div.jewish_date.small_top_margin").text().split(" "));
        String date = String.format("%s %s%s %s\n%s%s %s", allDate.get(0), allDate.get(1), allDate.get(2), allDate.get(3),
                allDateJewish.get(0), allDateJewish.get(1), allDateJewish.get(2));
        List<String> time = List.of(doc.select("span.time.extra_large.block").text().split(" "));
        String candleLighting = time.get(0);
        String havdalah = time.get(1);
        String exodusDay = String.format("%s %s%s %s\n%s%s %s", allDate.get(4), allDate.get(5), allDate.get(6), allDate.get(7),
                allDateJewish.get(3), allDateJewish.get(4), allDateJewish.get(5));
        webDriver.quit();
        return new Shabbat(date, parashat, candleLighting, havdalah, city, exodusDay);
    }
}
