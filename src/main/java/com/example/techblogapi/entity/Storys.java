package com.example.techblogapi.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Storys {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Users authorid;

    @NotBlank
    @NotEmpty
    @NotNull
    private String title;

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min=0,max = 10000)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedDate=new Date(System.currentTimeMillis());


    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }


    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public Users getAuthorid() {

        return authorid;
    }

    public void setAuthorid(Users authorid) {

        this.authorid = authorid;
    }
    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }
}
