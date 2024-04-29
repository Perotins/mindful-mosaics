package me.perotin.mindfulmosaics.models;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(indexes = {
        @Index(columnList = "date_created", name = "idx_blog_date_created")
})
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Assuming you have a User entity

    private String title;
    @Column(columnDefinition="LONGTEXT")
    private String content;

    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;


    @Column(name = "word_count")
    private Integer wordCount;

    private int likes;



    // Getters and setters for the likes field
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    // Increment likes method
    public void incrementLikes() {
        this.likes++;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @PrePersist
    protected void onCreate() {
        dateCreated = new Date();
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    protected void onLoadOrUpdate() {
        wordCount = content != null ? content.split("\\s+").length : 0;
    }
}
