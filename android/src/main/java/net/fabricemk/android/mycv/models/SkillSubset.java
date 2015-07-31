package net.fabricemk.android.mycv.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SkillSubset {

    @SerializedName("type")
    String subsetName;

    List<Skill> skills;
}
