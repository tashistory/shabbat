package uz.shabbat;

import uz.shabbat.parsing.Parsing;
import uz.shabbat.parsing.ParsingChabadOrg;
import uz.shabbat.parsing.ParsingChapter;
import uz.shabbat.parsing.ParsingChapterChabadOrg;
import uz.shabbat.parsing.telegram.SendMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public  List<Shabbat> getShabats(Parsing parsing, List<String> geoIDs) throws IOException {
        List<Shabbat> shabbats = new ArrayList<>();
        for (String geoID : geoIDs) {
            shabbats.add(parsing.getShabat(geoID));
        }
        return shabbats;
    }

    public  String getCapter(ParsingChapter parsing) throws IOException {
        return String.format("Краткое описание недельной главы:\n%s", parsing.getChapter());
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

    public static void main(String[] args) throws IOException {
        List<String> geoIDsHebcal = List.of("1512569", "1217662", "1216265", "1514019");
        List<String> geoIDsChabadOrg = List.of("681", "883", "882");

        Main parsing = new Main();
        SendMessage send = SendMessage.getInstance();
        String tgToken = "6125429176:AAF5RCb68lhKPXUeEBOyefGMIrI9BFN3pZY";
        int  chatId = 722152527;
        String txt = getMassage(parsing.getShabats(new ParsingChabadOrg(), geoIDsChabadOrg));
        send.send(tgToken, chatId, txt);
        txt = parsing.getCapter(new ParsingChapterChabadOrg());
        send.send(tgToken, chatId, txt);
        // System.out.println(getMassage(parsing.getShabats(new ParsingChabadOrg(), geoIDsChabadOrg)));
        //System.out.println();
        //System.out.println(parsing.getCapter(new ParsingChapterChabadOrg()));
    }
}