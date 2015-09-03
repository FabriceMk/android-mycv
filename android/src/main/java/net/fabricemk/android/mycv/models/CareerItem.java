package net.fabricemk.android.mycv.models;

/**
 * POJO which represents a job position in a company
 */
public class CareerItem {

    /**
     * The name of the company
     */
    String company;

    /**
     * The position in the company (e.g: Developer, CTO...)
     */
    String position;

    /**
     * A string representation of the start date of this position, doesn't follow a specific format
     */
    String startDate;

    /**
     * A string representation of the end date of this position, doesn't follow a specific format
     */
    String endDate;

    /**
     * A small description of the position
     */
    String description;

    /**
     * A more detailed description of the position
     */
    String details;

    /**
     * A code for the resource to be used as the company logo
     */
    String icon;

    /**
     * A code for the resource to be used as the header image
     */
    String header;

    /**
     * The URL of the company website
     */
    String website;



    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
