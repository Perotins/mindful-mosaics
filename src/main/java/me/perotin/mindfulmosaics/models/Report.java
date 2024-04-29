package me.perotin.mindfulmosaics.models;

public class Report {
    private int usersRegistered;
    private int blogsCreated;
    private int blogsLiked;

    private int blogsDeleted;
    private double averageBlogLength;

    private int tagsCreated;
    private String mostUsedTag;

    public Report(int usersRegistered, int blogsCreated, int blogsLiked, int blogsDeleted, double averageLength, int tagsCreated, String mostUsed) {
        this.usersRegistered = usersRegistered;
        this.blogsCreated = blogsCreated;
        this.blogsLiked = blogsLiked;
        this.blogsDeleted = blogsDeleted;
        this.blogsCreated = blogsDeleted;
        this.averageBlogLength = averageLength;
        this.tagsCreated = tagsCreated;
        this.mostUsedTag = mostUsed;
    }

    public double getAverageBlogLength() {
        return averageBlogLength;
    }

    public void setAverageBlogLength(double averageBlogLength) {
        this.averageBlogLength = averageBlogLength;
    }

    public int getTagsCreated() {
        return tagsCreated;
    }

    public void setTagsCreated(int tagsCreated) {
        this.tagsCreated = tagsCreated;
    }

    public String getMostUsedTag() {
        return mostUsedTag;
    }



    public void setMostUsedTag(String mostUsedTag) {
        this.mostUsedTag = mostUsedTag;
    }

    public int getUsersRegistered() {
        return usersRegistered;
    }

    public int getBlogsDeleted() {
        return blogsDeleted;
    }

    public void setBlogsDeleted(int blogsDeleted) {
        this.blogsDeleted = blogsDeleted;
    }

    public int getBlogsCreated() {
        return blogsCreated;
    }

    public int getBlogsLiked() {
        return blogsLiked;
    }

    public void setUsersRegistered(int usersRegistered) {
        this.usersRegistered = usersRegistered;
    }

    public void setBlogsCreated(int blogsCreated) {
        this.blogsCreated = blogsCreated;
    }

    public void setBlogsLiked(int blogsLiked) {
        this.blogsLiked = blogsLiked;
    }
}
