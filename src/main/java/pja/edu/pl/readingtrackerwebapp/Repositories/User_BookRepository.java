package pja.edu.pl.readingtrackerwebapp.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;
import pja.edu.pl.readingtrackerwebapp.Entities.Book;
import pja.edu.pl.readingtrackerwebapp.Entities.User_Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface User_BookRepository extends CrudRepository<User_Book, Integer> {
    Optional<User_Book> findByUserIdAndBookId(Integer userId, Integer bookId);

    @Query(value = "UPDATE User_Book ub " +
            "SET Is_Want_To_Read = 1, Is_Read = 0, date_Read = NULL " +
            "WHERE ub.User_ID = ?1 AND ub.Book_ID = ?2;", nativeQuery = true)
    void updateBookAsWantToRead(Integer userId, Integer bookId);

    @Query(value = "UPDATE User_Book ub " +
            "SET Is_Read = 1, Is_Want_To_Read = 0, date_Read = GETDATE() " +
            "WHERE ub.User_ID = ?1 AND ub.Book_ID = ?2;", nativeQuery = true)
    void updateBookAsRead(Integer userId, Integer bookId);

    @Modifying
    @Query(value = "INSERT INTO User_Book (User_ID, Book_ID, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) " +
            "VALUES ( ?1 , ?2 , 0, 1, NULL , NULL);", nativeQuery = true)
    void markBookAsWantToRead(Integer userId, Integer bookId);

    @Modifying
    @Query(value = "INSERT INTO User_Book (User_ID, Book_ID, iS_READ, iS_WANT_TO_READ, Rating, dATE_READ) " +
            "VALUES ( ?1 , ?2 , 1, 0, NULL , GETDATE() );", nativeQuery = true)
    void markBookAsRead(Integer userId, Integer bookId);
}
