package pja.edu.pl.readingtrackerwebapp.DTOs.PublisherDTOs;

import jakarta.validation.constraints.Size;

public class NewPublisherDTO {
    @Size(max = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
