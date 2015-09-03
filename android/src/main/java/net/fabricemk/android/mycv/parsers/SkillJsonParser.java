package net.fabricemk.android.mycv.parsers;

import android.content.Context;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.models.SkillList;
import net.fabricemk.android.mycv.models.SkillSubset;
import net.fabricemk.android.mycv.tools.JsonTools;

import java.util.Arrays;
import java.util.List;

/**
 * A class dedicated to the parsing of the JSON data files related to Skills
 */
public class SkillJsonParser {

    public static SkillList parseLocal(Context ctxt) {
        String localJson = JsonTools.readLocal(ctxt.getResources(), R.raw.skills);

        return parse(localJson);
    }

    public static SkillList parse(String json) {
        SkillSubset[] temp = JsonTools.constructUsingGson(SkillSubset[].class, json);

        List<SkillSubset> subsets = Arrays.asList(temp);

        SkillList result = new SkillList();
        result.buildFromList(subsets);

        return result;
    }

}
