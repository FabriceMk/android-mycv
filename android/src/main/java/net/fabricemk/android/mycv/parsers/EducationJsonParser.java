package net.fabricemk.android.mycv.parsers;

import android.content.Context;

import com.google.gson.Gson;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.models.EducationItem;
import net.fabricemk.android.mycv.tools.JsonTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class dedicated to the parsing of the JSON data files related to Education
 */
public class EducationJsonParser {

    public static List<EducationItem> parseLocal(Context ctxt) {
        String localJson = JsonTools.readLocal(ctxt.getResources(), R.raw.education_timeline);

        return parse(localJson);
    }

    public static List<EducationItem> parse(String json) {
        EducationItem[] temp = JsonTools.constructUsingGson(EducationItem[].class, json);
        List<EducationItem> results = Arrays.asList(temp);
        return results;
    }

}
