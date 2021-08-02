package sk.richardbobik.animestarterpack;

public class Show {
    private int id;
    private String name;
    private String picture;
    private String shortDescription;
    private String longDescription;
    private String tag;
    private String viewedStatus;
    boolean isExpanded;

    public Show(int id, String name, String picture, String shortDescription, String longDescription, String tag, String viewedStatus) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.tag = tag;
        this.viewedStatus = viewedStatus;
        isExpanded = false;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getViewedStatus() {
        return viewedStatus;
    }

    public void setViewedStatus(String viewedStatus) {
        this.viewedStatus = viewedStatus;
    }

    @Override
    public String toString() {
        return "Show{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", tag='" + tag + '\'' +
                ", viewedStatus='" + viewedStatus + '\'' +
                ", isExpanded=" + isExpanded +
                '}';
    }
}
