package pja.edu.pl.readingtrackerwebapp.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pja.edu.pl.readingtrackerwebapp.Entities.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    @Query(value = "SELECT TOP 5 b.* " +
            "FROM Book b " +
            "JOIN Book_Genres bg ON b.ID = bg.Books_ID " +
            "JOIN Genre g ON bg.Genres_ID = g.ID " +
            "LEFT JOIN User_Book ub ON b.ID = ub.Book_ID " +
            "WHERE g.Name = ?1 " +
            "GROUP BY b.ID " +
            "ORDER BY b.Rating DESC, Count(ub.is_read) DESC", nativeQuery = true)
    List<Book> findTop5BooksInGenre(String genre);

    Optional<Book> findByTitle(String title);
    /*@Query(value = "SELECT b.* " +
            "FROM Book b " +
            "JOIN Author a ON b.Author_ID = a.ID " +
            "WHERE LOWER(b.Title) = LOWER( ?1 ) OR LOWER(a.FIRST_NAME) LIKE LOWER('%?1%') OR LOWER(a.LAST_NAME) LIKE LOWER('%?1%') " +
            "GROUP BY b.ID, b.Title, b.Cover, b.Rating, b.Release_Date, b.Publisher_ID, b.Author_ID " +
            "ORDER BY b.Rating DESC", nativeQuery = true)
    List<Book> findByTitleOrAuthor(String title); */
    @Query(value = "SELECT b.* " +
            "FROM Book b " +
            "JOIN User_Book ub ON ub.Book_ID = b.Id " +
            "WHERE ub.User_ID = ?1 AND is_Read = 1 AND YEAR(ub.Date_Read) = YEAR(CURDATE());", nativeQuery = true)
    List<Book> getBooksReadThisYearById(Integer id);
}
