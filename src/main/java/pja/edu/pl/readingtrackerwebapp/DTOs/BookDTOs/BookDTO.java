package pja.edu.pl.readingtrackerwebapp.DTOs.BookDTOs;

import jakarta.validation.constraints.Size;
import pja.edu.pl.readingtrackerwebapp.DTOs.AuthorDTOs.AuthorWithoutBooksDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.GenreDTOs.GenreWithoutBooksDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.PublisherDTOs.PublisherWithoutBooksDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class BookDTO {
    private Integer id;
    @Size(max = 100)
    private String title;
    @Size(max = 300)
    private String cover;
    private BigDecimal rating;
    private Date releaseDate;
    private PublisherWithoutBooksDTO publisher;
    private AuthorWithoutBooksDTO author;
    private Set<GenreWithoutBooksDTO> genres = new HashSet<>();
    //private List<User_BookDTO> userBooks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public PublisherWithoutBooksDTO getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherWithoutBooksDTO publisher) {
        this.publisher = publisher;
    }

    public AuthorWithoutBooksDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorWithoutBooksDTO author) {
        this.author = author;
    }

    public Set<GenreWithoutBooksDTO> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreWithoutBooksDTO> genres) {
        this.genres = genres;
    }

    /*public List<User_BookDTO> getUserBooks() {
        return userBooks;
    }

    public void setUserBooks(List<User_BookDTO> userBooks) {
        this.userBooks = userBooks;
    }*/
}
