package pja.edu.pl.readingtrackerwebapp.DTOs.AuthorDTOs;

import jakarta.validation.constraints.Past;
import pja.edu.pl.readingtrackerwebapp.DTOs.BookDTOs.BookDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.PersonDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthorDTO extends PersonDTO {
    @Past
    private Date dateOfBirth;
    private List<BookDTO> books = new ArrayList<>();

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
