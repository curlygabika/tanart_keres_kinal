package hu.elte.Tanart_Keres_Kinal.repositories;

import hu.elte.Tanart_Keres_Kinal.entities.Status;
import hu.elte.Tanart_Keres_Kinal.entities.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    Optional<User> findByUserName(String userName);
    
    @Query("SELECT u FROM User u WHERE u.fullName = ?1")
    User findByFullName(String fullName);
    
    @Query("SELECT u FROM User u WHERE u.status = ?1 order by u.userName")
    List<User> getUsersByStatus(Status status);
}
