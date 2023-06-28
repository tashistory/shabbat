package uz.shabbat;

import uz.shabbat.parsing.Parsing;
import uz.shabbat.parsing.ParsingChabadOrg;
import uz.shabbat.parsing.ParsingHebcal;

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
        System.out.println(getMassage(parsing.getShabats(new ParsingChabadOrg(), geoIDsChabadOrg)));
    }
}