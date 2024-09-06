package pja.edu.pl.readingtrackerwebapp.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;
import pja.edu.pl.readingtrackerwebapp.Entities.Genre;

import java.util.List;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {
    @Query(value = "SELECT TOP 5 g.* " +
            "FROM Genre g " +
            "JOIN Book_Genres bg ON g.ID = bg.Genres_ID " +
            "JOIN Book b ON bg.Books_ID = b.ID " +
            "JOIN User_Book ub ON b.ID = ub.Book_ID " +
            "WHERE ub.is_Read = 1 " +
            "GROUP BY g.ID, g.Name " +
            "ORDER BY Count(ub.is_read) DESC", nativeQuery = true)
    List<Genre> getTop5PopularGenres();
}
