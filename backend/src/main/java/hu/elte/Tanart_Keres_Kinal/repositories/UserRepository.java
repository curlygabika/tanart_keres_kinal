package hu.elte.Tanart_Keres_Kinal.repositories;

import hu.elte.Tanart_Keres_Kinal.entities.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
