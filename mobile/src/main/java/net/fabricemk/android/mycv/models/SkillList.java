package net.fabricemk.android.mycv.models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * An object which contains a dictionary of {@link SkillSubset}
 * Serves just as a container but could do more on the {@link SkillSubset} and the {@link Skill}
 */
public class SkillList {

    /**
     * A Map of different {@link SkillSubset}
     */
    private Map<String, SkillSubset> allSkills;

    /**
     * As we want to preserve the order of insertion we
     * use a {@link LinkedHashMap} as the Map implementation
     */
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
