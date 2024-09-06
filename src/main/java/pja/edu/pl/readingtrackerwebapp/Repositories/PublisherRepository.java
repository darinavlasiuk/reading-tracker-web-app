package pja.edu.pl.readingtrackerwebapp.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pja.edu.pl.readingtrackerwebapp.Entities.Publisher;

@Repository
public interface PublisherRepository extends CrudRepository<Publisher, Integer> {
}
