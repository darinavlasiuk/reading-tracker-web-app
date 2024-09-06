package pja.edu.pl.readingtrackerwebapp.Entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class MyUser extends Person{
    @Size(max = 50)
    @Column(unique = true)
    private String username;
    @Size(min = 8, max = 50)
    private String password;
    @Email
    @Column(unique = true)
    @Size(max = 50)
    private String email;
    private Short isAdmin;
    @Nullable
    private Integer goal;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<User_Book> userBooks = new ArrayList<>();

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

    public Short getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Short isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public List<User_Book> getUserBooks() {
        return userBooks;
    }

    public void setUserBooks(List<User_Book> userBooks) {
        this.userBooks = userBooks;
    }
}
