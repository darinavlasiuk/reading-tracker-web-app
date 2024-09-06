package pja.edu.pl.readingtrackerwebapp.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pja.edu.pl.readingtrackerwebapp.Entities.Author;
import pja.edu.pl.readingtrackerwebapp.Entities.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
