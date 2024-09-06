package pja.edu.pl.readingtrackerwebapp.Services;

import org.springframework.stereotype.Service;
import pja.edu.pl.readingtrackerwebapp.DTOs.AuthorDTOs.AuthorDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.AuthorDTOs.AuthorWithoutBooksDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.AuthorDTOs.NewAuthorDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.BookDTOs.BookDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.GenreDTOs.GenreWithoutBooksDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.BookDTOs.NewBookDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.PublisherDTOs.NewPublisherDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.PublisherDTOs.PublisherDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.PublisherDTOs.PublisherWithoutBooksDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.UserDTOs.NewUserDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.UserDTOs.UserDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.UserDTOs.UserWithoutBooksDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.User_BookDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.User_BookWithoutUserDTO;
import pja.edu.pl.readingtrackerwebapp.Entities.*;

import java.math.BigDecimal;

@Service
public class Mapper {
    // Book
    public BookDTO map(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setCover(book.getCover());
        dto.setAuthor(mapToAuthorWithoutBooks(book.getAuthor()));
        dto.setRating(book.getRating());
        dto.setPublisher(mapToPublisherWithoutBooks(book.getPublisher()));
        dto.setReleaseDate(book.getReleaseDate());
        dto.setTitle(book.getTitle());
        for(Genre genre: book.getGenres())
            dto.getGenres().add(map(genre));
        return dto;
    }
    public NewBookDTO mapToNewBook(Book book) {
        NewBookDTO dto = new NewBookDTO();
        dto.setCover(book.getCover());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setPublisherId(book.getPublisher().getId());
        dto.setReleaseDate(book.getReleaseDate());
        dto.setTitle(book.getTitle());
        for(Genre genre: book.getGenres())
            dto.getGenreIds().add(genre.getId());
        return dto;
    }

    private GenreWithoutBooksDTO map(Genre genre) {
        GenreWithoutBooksDTO dto = new GenreWithoutBooksDTO();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        return dto;
    }
    public Book map(NewBookDTO dto) {
        Book book = new Book();
        book.setCover(dto.getCover());
        book.setRating(BigDecimal.valueOf(0));
        book.setReleaseDate(dto.getReleaseDate());
        book.setTitle(dto.getTitle());
        return book;
    }
    public Book map(BookDTO dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setCover(dto.getCover());
        book.setRating(BigDecimal.valueOf(0));
        book.setReleaseDate(dto.getReleaseDate());
        book.setTitle(dto.getTitle());
        return book;
    }

    // User
    public UserDTO map(MyUser user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPassword(user.getPassword());
        dto.setGoal(user.getGoal());
        dto.setIsAdmin(user.getIsAdmin());
        for(User_Book userBook : user.getUserBooks())
            dto.getUserBooks().add(map(userBook));
        return dto;
    }
    public NewUserDTO mapToNewUser(MyUser user) {
        NewUserDTO dto = new NewUserDTO();
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPassword(user.getPassword());
        dto.setUsername(user.getUsername());
        dto.setGoal(user.getGoal());
        dto.setIsAdmin(user.getIsAdmin());
        return dto;
    }
    public User_BookWithoutUserDTO map(User_Book userBook){
        User_BookWithoutUserDTO dto = new User_BookWithoutUserDTO();
        dto.setBook(map(userBook.getBook()));
        dto.setUserid(userBook.getUser().getId());
        dto.setRating(userBook.getRating());
        dto.setDateRead(userBook.getDateRead());
        dto.setIsRead(userBook.getIsRead());
        dto.setIsWantToRead(userBook.getIsWantToRead());
        return dto;
    }
    public MyUser map(NewUserDTO dto) {
        MyUser user = new MyUser();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(user.getEmail());
        user.setGoal(dto.getGoal());
        user.setIsAdmin(user.getIsAdmin());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        return user;
    }

    // Author
    public AuthorDTO map(Author author){
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setFirstName(author.getFirstName());
        dto.setLastName(author.getLastName());
        dto.setDateOfBirth(author.getDateOfBirth());
        for(Book book: author.getBooks())
            dto.getBooks().add(map(book));
        return dto;
    }
    public AuthorWithoutBooksDTO mapToAuthorWithoutBooks(Author author){
        AuthorWithoutBooksDTO dto = new AuthorWithoutBooksDTO();
        dto.setId(author.getId());
        dto.setFirstName(author.getFirstName());
        dto.setLastName(author.getLastName());
        dto.setDateOfBirth(author.getDateOfBirth());
        return dto;
    }
    public NewAuthorDTO mapToNewAuthor(Author author) {
        NewAuthorDTO dto = new NewAuthorDTO();
        dto.setFirstName(author.getFirstName());
        dto.setLastName(author.getLastName());
        dto.setDateOfBirth(author.getDateOfBirth());
        return dto;
    }
    public Author map(NewAuthorDTO dto) {
        Author author = new Author();
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        author.setDateOfBirth(dto.getDateOfBirth());
        return author;
    }

    // Publisher
    public PublisherDTO map(Publisher publisher){
        PublisherDTO dto = new PublisherDTO();
        dto.setId(publisher.getId());
        dto.setName(publisher.getName());
        for(Book book : publisher.getBooks())
            dto.getBooks().add(map(book));
        return dto;
    }
    public PublisherWithoutBooksDTO mapToPublisherWithoutBooks(Publisher publisher){
        PublisherWithoutBooksDTO dto = new PublisherWithoutBooksDTO();
        dto.setId(publisher.getId());
        dto.setName(publisher.getName());
        return dto;
    }

    public NewPublisherDTO mapToNewPublisher(Publisher publisher) {
        NewPublisherDTO dto = new NewPublisherDTO();
        dto.setName(publisher.getName());
        return dto;
    }

    public Publisher map(NewPublisherDTO dto) {
        Publisher publisher = new Publisher();
        publisher.setName(dto.getName());
        return publisher;
    }
}
