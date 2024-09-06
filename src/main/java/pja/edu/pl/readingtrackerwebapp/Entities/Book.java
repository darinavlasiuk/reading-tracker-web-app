package pja.edu.pl.readingtrackerwebapp.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 100)
    private String title;
    @Size(max = 300)
    private String cover;
    @Column(precision = 2, scale = 1)
    private BigDecimal rating;
    private Date releaseDate;
    @ManyToOne
    private Publisher publisher;
    @ManyToOne
    private Author author;
    @ManyToMany
    private Set<Genre> genres;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<User_Book> userBooks = new ArrayList<>();

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

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public List<User_Book> getUserBooks() {
        return userBooks;
    }

    public void setUserBooks(List<User_Book> userBooks) {
        this.userBooks = userBooks;
    }
}
