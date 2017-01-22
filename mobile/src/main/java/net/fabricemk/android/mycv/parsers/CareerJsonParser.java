package net.fabricemk.android.mycv.parsers;

import android.content.Context;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.models.CareerItem;
import net.fabricemk.android.mycv.tools.JsonTools;

import java.util.Arrays;
import java.util.List;

/**
 * A class dedicated to the parsing of the JSON data files related to Career
 */
public class CareerJsonParser {

    public static List<CareerItem> parseLocal(Context ctxt) {
        String localJson = JsonTools.readLocal(ctxt.getResources(), R.raw.career_timeline);
        return parse(localJson);
    }

    public static List<CareerItem> parse(String json) {
        CareerItem[] temp = JsonTools.constructUsingGson(CareerItem[].class, json);
        return Arrays.asList(temp);
    }

}
