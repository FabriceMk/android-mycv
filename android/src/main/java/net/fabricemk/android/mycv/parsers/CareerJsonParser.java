package net.fabricemk.android.mycv.parsers;

import android.content.Context;

import com.google.gson.Gson;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.models.CareerItem;
import net.fabricemk.android.mycv.models.EducationItem;
import net.fabricemk.android.mycv.tools.JsonTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CareerJsonParser {

    public static List<CareerItem> parseLocal(Context ctxt) {
        List<CareerItem> results;

        String localJson = JsonTools.readLocal(ctxt.getResources(), R.raw.career_timeline);

        CareerItem[] temp = JsonTools.constructUsingGson(CareerItem[].class, localJson);

        results = Arrays.asList(temp);

        return results;
    }

}
