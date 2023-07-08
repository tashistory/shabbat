package uz.shabbat.parsing;

import uz.shabbat.Shabbat;

import java.io.IOException;
/**
 @author Jebrak Semyon
 @version 1.0
 */
public interface Parsing {
   Shabbat getShabat(String geoID) throws IOException;
}
