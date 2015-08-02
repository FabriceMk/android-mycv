package net.fabricemk.android.mycv.models;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SkillList {

    private Map<String, SkillSubset> allSkills;

    public void buildFromList(List<SkillSubset> list) {
        allSkills = new LinkedHashMap<>();
        for (SkillSubset sub : list) {
            allSkills.put(sub.getSubsetName(), sub);
        }
    }

    public Map<String, SkillSubset> getAllSkills() {
        return allSkills;
    }

    public void setAllSkills(Map<String, SkillSubset> allSkills) {
        this.allSkills = allSkills;
    }
}
