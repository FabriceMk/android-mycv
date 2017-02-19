package net.fabricemk.android.mycv.models;

/**
 * A POJO which represents a formation or a diploma obtained in a specific school
 */
public class EducationItem {

    /**
     * The school name
     */
    private String school;

    /**
     * The diploma's name or the formation name
     */
    private String diploma;

    /**
     * The string representation of the formation start date , doesn't follow any specific format
     */
    private String startDate;

    /**
     * The string representation of the formation end date , doesn't follow any specific format
     */
    private String endDate;

    /**
     * A description of the formation, diploma
     */
    private String description;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
