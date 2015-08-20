package net.fabricemk.android.mycv.parsers;

import android.content.Context;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.models.TripItem;
import net.fabricemk.android.mycv.tools.JsonTools;

import java.util.Arrays;
import java.util.List;

public class TripJsonParser {

    public static List<TripItem> parseLocal(Context ctxt) {
        String localJson = JsonTools.readLocal(ctxt.getResources(), R.raw.trips);

        TripItem[] temp = JsonTools.constructUsingGson(TripItem[].class, localJson);

        List<TripItem> trips = Arrays.asList(temp);

        return trips;
    }
}
