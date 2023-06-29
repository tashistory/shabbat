package uz.shabbat;

import uz.shabbat.config.Config;
import uz.shabbat.parsing.*;
import uz.shabbat.telegram.SendMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
/**
 @author Jebrak Semyon
 @version 1.2
 */
public class Main {

    public List<Shabbat> getShabats(Parsing parsing, String driverPath, List<String> geoIDs) throws IOException {
        List<Shabbat> shabbats = new ArrayList<>();
        for (String geoID : geoIDs) {
            shabbats.add(parsing.getShabat(geoID, driverPath));
        }
        return shabbats;
    }

    public String getCapter(ParsingChapter parsing) throws IOException {
        return String.format("\uD83D\uDCDC Краткое описание недельной главы:\n%s", parsing.getChapter());
    }

    public static String getMassage(List<Shabbat> shabats) throws IOException {

        StringBuilder result = new StringBuilder();

        result.append(shabats.get(0).getDate()).append("\n\n");
        result.append("\uD83D\uDD6F \uD83D\uDD6F Время зажигания шаббатних свечей:\n");
        for (Shabbat shabat : shabats) {
            result.append(String.format("\t○ %s\t %s\n", shabat.getCity(), shabat.getCandleLighting()));
        }
        result.append("\n").append(shabats.get(0).getExodusDay()).append("\n");
        result.append("\n\uD83D\uDD4D Исход шаббата:\n");
        for (Shabbat shabat : shabats) {
            result.append(String.format("\t○ %s\t %s\n", shabat.getCity(), shabat.getHavdalah()));
        }
        result.append("\n\uD83D\uDCD6 Недельная глава:\n").append(shabats.get(0).getParashat());
        result.append("\n\n✡ Всем Шаббат шалом!");

        return result.toString();
    }

    public static String readConfig() {
        StringBuilder rsult = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader("config.xml"))) {
            in.lines().forEach(rsult::append);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsult.toString();
    }

    public Config getConfig() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Config.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(readConfig())) {
            return (Config) unmarshaller.unmarshal(reader);
        }
    }


    public static void main(String[] args) throws IOException, JAXBException {
        Main parsing = new Main();
        Config config = parsing.getConfig();
        List<String> geoIDs = List.of(config.getGeoID());
        String tgToken = config.getTgToken();
        String chatIds = config.getChatId();
        SendMessage send = SendMessage.getInstance();
        String namewebsite = config.getNameWebsite();
        String driverPath = config.getDriverPath();
        if ("hebcal.com".equals(namewebsite)){
            String txt = getMassage(parsing.getShabats(new ParsingHebcal(), "", geoIDs));
            for (String chatId : chatIds.split(",")) {
                send.send(tgToken, chatId, txt);
            }
        } else if ("chabad.org".equals(namewebsite)) {
            String txt = getMassage(parsing.getShabats(new ParsingChabadOrg(), driverPath, geoIDs));
            for (String chatId : chatIds.split(",")) {
                send.send(tgToken, chatId, txt);
            }
            txt = parsing.getCapter(new ParsingChapterChabadOrg());
            for (String chatId : chatIds.split(",")) {
                send.send(tgToken, chatId, txt);
            }
        } else {
            throw new UnsupportedOperationException("Wrong site. Use the config file!");
        }
    }
}