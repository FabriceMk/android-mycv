package net.fabricemk.android.mycv.models;

/**
 * A POJO which represents a certain skill
 */
public class Skill {

    /**
     * The skill's name
     */
    private String name;

    /**
     * A description of the skill
     */
    private String description;

    /**
     * A code for the resource to be used as the skill logo
     */
    private String icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
