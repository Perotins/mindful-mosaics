package me.perotin.mindfulmosaics.models;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password; // Store encrypted passwords only

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    // Standard getters and setters
}
