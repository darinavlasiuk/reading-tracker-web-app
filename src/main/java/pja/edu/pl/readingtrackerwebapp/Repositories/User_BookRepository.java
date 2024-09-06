package pja.edu.pl.readingtrackerwebapp.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;
import pja.edu.pl.readingtrackerwebapp.Entities.Book;
import pja.edu.pl.readingtrackerwebapp.Entities.User_Book;

import java.util.List;

@Repository
public interface User_BookRepository extends CrudRepository<User_Book, Integer> {
    @Query(value = "SELECT COUNT(*) " +
            "FROM User_Book ub " +
            "WHERE ub.User_ID = ?1 AND is_Read = 1 AND YEAR(date_Read) = YEAR(CURRENT_DATE);", nativeQuery = true)
    int howManyBooksReadThisYear(Integer id);
}
