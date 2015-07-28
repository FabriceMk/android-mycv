package net.fabricemk.android.mycv.parsers;

import android.content.Context;

import com.google.gson.Gson;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.models.EducationItem;
import net.fabricemk.android.mycv.tools.JsonTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EducationJsonParser {

    public static List<EducationItem> parseLocal(Context ctxt) {
        List<EducationItem> results;

        String localJson = JsonTools.readLocal(ctxt.getResources(), R.raw.education_timeline);

        EducationItem[] temp = JsonTools.constructUsingGson(EducationItem[].class, localJson);

        results = Arrays.asList(temp);

        return results;
    }

}
