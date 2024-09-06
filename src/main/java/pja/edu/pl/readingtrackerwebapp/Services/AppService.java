package pja.edu.pl.readingtrackerwebapp.Services;


import org.springframework.stereotype.Service;
import pja.edu.pl.readingtrackerwebapp.DTOs.AuthorDTOs.AuthorDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.AuthorDTOs.NewAuthorDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.BookDTOs.BookDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.BookDTOs.NewBookDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.PublisherDTOs.NewPublisherDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.PublisherDTOs.PublisherDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.UserDTOs.NewUserDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.UserDTOs.UserDTO;
import pja.edu.pl.readingtrackerwebapp.Entities.*;
import pja.edu.pl.readingtrackerwebapp.Repositories.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final GenreRepository genreRepository;
    private final UserRepository userRepository;
    private final User_BookRepository userBookRepository;
    private final Mapper mapper;
    public AppService(BookRepository bookRepository, AuthorRepository authorRepository,
                      PublisherRepository publisherRepository, GenreRepository genreRepository,
                      UserRepository userRepository, User_BookRepository userBookRepository, Mapper mapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.genreRepository = genreRepository;
        this.userRepository = userRepository;
        this.userBookRepository = userBookRepository;
        this.mapper = mapper;
    }

    // Book Service
    public Optional<BookDTO> getBookById(Integer id) {
        return bookRepository.findById(id).map(mapper::map);
    }
    public Optional<NewBookDTO> getNewBookById(Integer id) {
        return bookRepository.findById(id).map(mapper::mapToNewBook);
    }
    public Optional<BookDTO> getBookByTitle(String title) {
        return bookRepository.findByTitle(title).map(mapper::map);
    }
    public BookDTO saveBook(NewBookDTO book) {
        Book toSave = mapNewBookToBook(book);

        return mapper.map(bookRepository.save(toSave));
    }
    public void saveBookById(Integer id, NewBookDTO book){
        Book toSave = mapNewBookToBook(book);
        toSave.setId(id);

        bookRepository.save(toSave);
    }
    public Book mapNewBookToBook(NewBookDTO book) {
        Book toSave = mapper.map(book);
        Optional<Author> author = authorRepository.findById(book.getAuthorId());
        if(author.isEmpty())
            return null;

        Optional<Publisher> publisher = publisherRepository.findById(book.getPublisherId());
        if(publisher.isEmpty())
            return null;

        Set<Genre> genres = new HashSet<>();
        for(Genre genre: genreRepository.findAllById(book.getGenreIds()))
            genres.add(genre);

        toSave.setAuthor(author.get());
        toSave.setPublisher(publisher.get());
        toSave.setGenres(genres);

        return toSave;
    }
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
    public Map<String, List<BookDTO>> getTopBooksInEachGenre() {
        List<Genre> mostPopularGenres = genreRepository.getTop5PopularGenres();
        Map<String, List<BookDTO>> topBooks = new HashMap<>();

        for(Genre genre: mostPopularGenres){
            topBooks.put(genre.getName(), bookRepository.findTop5BooksInGenre(genre.getName()).stream()
                    .map(mapper::map).collect(Collectors.toList()));
        }
        return topBooks;
    }
    public List<BookDTO> getReadBooksById(Integer id){
        return bookRepository.getReadBooksById(id).stream().map(mapper::map).collect(Collectors.toList());
    }


    // User Service
    public Optional<UserDTO> getUserById(Integer id) {
        return userRepository.findById(id).map(mapper::map);
    }
    public Optional<NewUserDTO> getNewUserById(Integer id) {
        return userRepository.findById(id).map(mapper::mapToNewUser);
    }
    public UserDTO saveUser(NewUserDTO user) {
        MyUser toSave = mapper.map(user);

        return mapper.map(userRepository.save(toSave));
    }
    public void saveUserById(Integer id, NewUserDTO user){
        MyUser toSave = mapper.map(user);
        toSave.setId(id);

        userRepository.save(toSave);
    }
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
    public Optional<UserDTO> existsUserWithUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).map(mapper::map);
    }

    // Author Service
    public Optional<AuthorDTO> getAuthorById(Integer id) {
        return authorRepository.findById(id).map(mapper::map);
    }
    public Optional<NewAuthorDTO> getNewAuthorById(Integer id) {
        return authorRepository.findById(id).map(mapper::mapToNewAuthor);
    }
    public Optional<AuthorDTO> getAuthorByName(String firstname, String lastname) {
        return authorRepository.findByFirstNameAndLastName(firstname, lastname).map(mapper::map);
    }
    public AuthorDTO saveAuthor(NewAuthorDTO author) {
        Author toSave = mapper.map(author);

        return mapper.map(authorRepository.save(toSave));
    }
    public void saveAuthorById(Integer id, NewAuthorDTO author){
        Author toSave = mapper.map(author);
        toSave.setId(id);

        authorRepository.save(toSave);
    }
    public void deleteAuthor(Integer id) {
        authorRepository.deleteById(id);
    }

    // Publisher Service
    public Optional<PublisherDTO> getPublisherById(Integer id) {
        return publisherRepository.findById(id).map(mapper::map);
    }
    public Optional<NewPublisherDTO> getNewPublisherById(Integer id) {
        return publisherRepository.findById(id).map(mapper::mapToNewPublisher);
    }
    public PublisherDTO savePublisher(NewPublisherDTO publisher) {
        Publisher toSave = mapper.map(publisher);

        return mapper.map(publisherRepository.save(toSave));
    }
    public void savePublisherById(Integer id, NewPublisherDTO publisher){
        Publisher toSave = mapper.map(publisher);
        toSave.setId(id);

        publisherRepository.save(toSave);
    }
    public void deletePublisher(Integer id) {
        publisherRepository.deleteById(id);
    }

    // User_Book Service
    public int howManyBooksReadThisYear(Integer id){
        return userBookRepository.howManyBooksReadThisYear(id);
    }

}
