package pja.edu.pl.readingtrackerwebapp.DTOs.PublisherDTOs;

import jakarta.validation.constraints.Size;

public class PublisherWithoutBooksDTO{
    private Integer id;
    @Size(max = 50)
    private String name;

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
}
