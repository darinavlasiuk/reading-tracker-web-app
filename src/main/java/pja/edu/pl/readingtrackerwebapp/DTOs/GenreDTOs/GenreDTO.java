package pja.edu.pl.readingtrackerwebapp.DTOs.GenreDTOs;

import jakarta.validation.constraints.Size;
import pja.edu.pl.readingtrackerwebapp.DTOs.BookDTOs.BookDTO;

import java.util.HashSet;
import java.util.Set;

public class GenreDTO {
    private Integer id;
    @Size(max = 50)
    private String name;
    private Set<BookDTO> books = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDTO> books) {
        this.books = books;
    }
}
