package pja.edu.pl.readingtrackerwebapp.DTOs.PublisherDTOs;

import jakarta.validation.constraints.Size;
import pja.edu.pl.readingtrackerwebapp.DTOs.BookDTOs.BookDTO;

import java.util.ArrayList;
import java.util.List;

public class PublisherDTO {
    private Integer id;
    @Size(max = 50)
    private String name;
    private List<BookDTO> books = new ArrayList<>();

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

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
