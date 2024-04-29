package me.perotin.mindfulmosaics.models;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    public Tag() {
        // Default constructor needed by JPA
    }

    // Additional constructors, getters, and setters

    public Long getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public Blog getBlog() {
        return blog;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // hashCode, equals and toString methods if needed
}
