//package com.lambdaschool.starthere.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.lambdaschool.starthere.logging.Loggable;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Loggable
//@Entity
//@Table(name = "wrote",
//        uniqueConstraints = {@UniqueConstraint(columnNames = {"bookid", "authorid"})})
//public class Wrote extends Auditable implements Serializable
//{
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "bookid")
//    @JsonIgnoreProperties("wrote")
//    private User user;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "authorid")
//    @JsonIgnoreProperties("wrote")
//    private Role role;
//
//    public Wrote()
//    {
//    }
//
//    public Wrote(User user, Role role)
//    {
//        this.user = user;
//        this.role = role;
//    }
//
//    public User getUser()
//    {
//        return user;
//    }
//
//    public void setUser(User user)
//    {
//        this.user = user;
//    }
//
//    public Role getRole()
//    {
//        return role;
//    }
//
//    public void setRole(Role role)
//    {
//        this.role = role;
//    }
//}
