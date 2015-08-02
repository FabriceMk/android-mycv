package net.fabricemk.android.mycv.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SkillSubset {

    @SerializedName("type")
    private String subsetName;

    private List<Skill> skills;

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
}
