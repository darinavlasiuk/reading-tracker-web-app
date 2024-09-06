package pja.edu.pl.readingtrackerwebapp.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pja.edu.pl.readingtrackerwebapp.Entities.MyUser;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<MyUser, Integer> {
    Optional<MyUser> findByUsernameAndPassword(String username, String password);
}
