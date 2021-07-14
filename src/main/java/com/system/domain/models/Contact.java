package com.system.domain.models;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="contact")
public class Contact {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @ApiModelProperty(notes = "Nome do contato", name = "contact_name", required = true, value = "teste name")
    private String name;
    private String phone;
    private String email;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Contact() {
    }

    public Contact(String name, String phone, String email, User user) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.user = user;
    }


    public String getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
