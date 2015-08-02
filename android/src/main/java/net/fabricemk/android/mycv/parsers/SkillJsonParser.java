package net.fabricemk.android.mycv.parsers;

import android.content.Context;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.models.SkillList;
import net.fabricemk.android.mycv.models.SkillSubset;
import net.fabricemk.android.mycv.tools.JsonTools;

import java.util.Arrays;
import java.util.List;

public class SkillJsonParser {

    public static SkillList parseLocal(Context ctxt) {
        SkillList result = new SkillList();

        String localJson = JsonTools.readLocal(ctxt.getResources(), R.raw.skills);

        SkillSubset[] temp = JsonTools.constructUsingGson(SkillSubset[].class, localJson);

        List<SkillSubset> subsets = Arrays.asList(temp);

        result.buildFromList(subsets);

        return result;
    }

}
