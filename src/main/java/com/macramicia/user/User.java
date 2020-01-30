package com.macramicia.user;

import com.macramicia.courses.Course;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    private String firstName;
    private String lastName;

    @Id
    @NotNull
    private String username;

    private String password;

    @Email
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany/*(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE},
            fetch = FetchType.LAZY)*/
    @JoinTable(
            name = "users_course",
            joinColumns = @JoinColumn(name = "users_username", referencedColumnName = "username"),
            inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id")})
    private Set<Course> courses;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        if(this.courses == null) courses = new HashSet<>();

        this.courses = courses;
    }

    public boolean addCourse(Course c) {
        System.out.println("Adding Course with id: " + c.getId());
        return this.courses.add(c);
    }

    public boolean removeCourse(Course c) {
        return this.courses.remove(c);
    }

}
