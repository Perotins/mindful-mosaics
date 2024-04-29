package me.perotin.mindfulmosaics.models;

public class Report {
    private int usersRegistered;
    private int blogsCreated;
    private int blogsLiked;

    public Report(int usersRegistered, int blogsCreated, int blogsLiked) {
        this.usersRegistered = usersRegistered;
        this.blogsCreated = blogsCreated;
        this.blogsLiked = blogsLiked;
    }

    public int getUsersRegistered() {
        return usersRegistered;
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
