package uz.shabbat.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
/**
 @author Jebrak Semyon
 @version 1.0
 */

public class ParsingChapterChabadOrg implements ParsingChapter {
    String driverPath;

    public ParsingChapterChabadOrg(String driverPath) {
        this.driverPath = driverPath;
    }

    @Override
    public String getChapter() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .withLogFile(new File("logs/slenium.log"))
                .build();
        WebDriver webDriver = new ChromeDriver(service, options);
        webDriver.get("https://ru.chabad.org/parshah/default.htm");
        Document doc = Jsoup.parse(webDriver.getPageSource());
        String link = "https://ru.chabad.org"+doc.selectFirst("div.nested_item.child_title.small_vertical_margin").selectFirst("a").attr("href");
        webDriver.get(link);
        doc = Jsoup.parse(webDriver.getPageSource());
        String content = doc.selectFirst("div.co_body.article-body.cf").toString()
                .replace("<div class=\"co_body article-body cf\"> ", "")
                .replace("<p>", "")
                .replace("</p>", "\n")
                .replace("</div>", "");
        webDriver.quit();
        return content;
    }
}
