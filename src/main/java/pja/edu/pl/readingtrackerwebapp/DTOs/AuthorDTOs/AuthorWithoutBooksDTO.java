package pja.edu.pl.readingtrackerwebapp.DTOs.AuthorDTOs;

import jakarta.validation.constraints.Past;
import pja.edu.pl.readingtrackerwebapp.DTOs.PersonDTO;

import java.util.Date;

public class AuthorWithoutBooksDTO extends PersonDTO {
    @Past
    private Date dateOfBirth;

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
