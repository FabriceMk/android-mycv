package net.fabricemk.android.mycv.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a group of skills of the same nature (e.g: web development, languages...)
 */
public class SkillSubset {

    /**
     * The name of the subset
     */
    @SerializedName("type")
    private String subsetName;

    /**
     * The list of all the skills of the subset
     */
    private List<Skill> skills;

    /**
     * An int which can serve as an index order to compare SkillSubsts between them
     */
    private int order;

    /**
     * Adds a skill to the subset. Doesn't check for duplicates.
     * @param skill the skill to be added
     */
    public void addSkill(Skill skill) {
        if (skills == null) {
            skills = new ArrayList<>();
        }
        skills.add(skill);
    }

    public String getSubsetName() {
        return subsetName;
    }

    public void setSubsetName(String subsetName) {
        this.subsetName = subsetName;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
