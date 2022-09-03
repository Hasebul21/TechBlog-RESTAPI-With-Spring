package com.example.techblogapi.entity;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
public class User {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",flags = Pattern.Flag.CASE_INSENSITIVE, message = "Email have to be unique")
    private String email;

    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9@$!%*?&]{8,}", message = "Password have to be 8 character long" +
            " Must have atleast one digit one uppercase and one lowercase Character")
    private String password;

    @Pattern(regexp = "[A-Za-z]{3,}", message = "Invalid name. name must have length atleast 3 character UpperCase or LowerCase")
    private String name;
    @Pattern(regexp = "[0-9]{11}" ,message = "Invalid Phone. Number must have exactly 11 digit")
    private String phone;

    @Temporal(TemporalType.TIMESTAMP)
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
