package pja.edu.pl.readingtrackerwebapp.DTOs.UserDTOs;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import pja.edu.pl.readingtrackerwebapp.DTOs.PersonDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.User_BookWithoutUserDTO;

import java.util.ArrayList;
import java.util.List;


public class UserDTO extends PersonDTO {
    @Size(max = 50)
    private String username;
    @Size(min = 8, max = 50)
    private String password;
    @Email
    @Size(max = 50)
    private String email;
    private Short isAdmin;
    @Nullable
    private Integer goal;
    private List<User_BookWithoutUserDTO> userBooks = new ArrayList<>();

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

    public List<User_BookWithoutUserDTO> getUserBooks() {
        return userBooks;
    }

    public void setUserBooks(List<User_BookWithoutUserDTO> userBooks) {
        this.userBooks = userBooks;
    }
}
