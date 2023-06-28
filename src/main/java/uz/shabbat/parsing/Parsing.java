package uz.shabbat.parsing;

import uz.shabbat.Shabbat;

import java.io.IOException;

public interface Parsing {
   Shabbat getShabat(String geoID) throws IOException;
}
