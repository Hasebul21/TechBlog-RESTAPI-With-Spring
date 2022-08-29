package com.example.techblogapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Component
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @Column(nullable = false)
    private String password;


    @Temporal(TemporalType.DATE)
    private Date CreatedDate=new Date(System.currentTimeMillis());

    public User() {

    }

    public void setId(int id) {

        this.id = id;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public void setPassword(String password) {

        this.password = password;
    }
    public void setCreatedDate(Date createdDate) {

        CreatedDate = createdDate;
    }

    public int getId() {

        return id;
    }
    public String getEmail() {

        return email;
    }

    public String getPassword() {

        return password;
    }
    public Date getCreatedDate() {

        return CreatedDate;
    }


}
