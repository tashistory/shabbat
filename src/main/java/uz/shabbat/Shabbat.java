package uz.shabbat;
/**
 @author Jebrak Semyon
 @version 1.0
 */
public class Shabbat {
    private final String date;
    private final String parashat;
    private final String candleLighting;
    private final String havdalah;
    private final String city;
    private final String exodusDay;
    public Shabbat(String date, String parashat, String candleLighting, String havdalah, String city, String exodusDay) {
        this.date = date;
        this.parashat = parashat;
        this.candleLighting = candleLighting;
        this.havdalah = havdalah;
        this.city = city;
        this.exodusDay = exodusDay;
    }

    public String getDate() {
        return date;
    }

    public String getParashat() {
        return parashat;
    }

    public String getCandleLighting() {
        return candleLighting;
    }

    public String getHavdalah() {
        return havdalah;
    }

    public String getCity() {
        return city;
    }

    public String getExodusDay() {
        return exodusDay;
    }

    @Override
    public String toString() {
        return "Shabbat{" +
                "date='" + date + '\'' +
                ", parashat='" + parashat + '\'' +
                ", candleLighting='" + candleLighting + '\'' +
                ", havdalah='" + havdalah + '\'' +
                ", city='" + city + '\'' +
                ", exodusDay='" + exodusDay + '\'' +
                '}';
    }
}
