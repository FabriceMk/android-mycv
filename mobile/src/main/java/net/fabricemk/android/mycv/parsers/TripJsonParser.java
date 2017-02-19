package net.fabricemk.android.mycv.parsers;

import android.content.Context;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.models.TripItem;
import net.fabricemk.android.mycv.tools.JsonTools;

import java.util.Arrays;
import java.util.List;

/**
 * A class dedicated to the parsing of the JSON data files related to Trips
 */
public class TripJsonParser {

    public static List<TripItem> parseLocal(Context ctxt) {
        String localJson = JsonTools.readLocal(ctxt.getResources(), R.raw.trips);
        return parse(localJson);
    }

    public static List<TripItem> parse(String json) {
        TripItem[] temp = JsonTools.constructUsingGson(TripItem[].class, json);
        return Arrays.asList(temp);
    }
}
