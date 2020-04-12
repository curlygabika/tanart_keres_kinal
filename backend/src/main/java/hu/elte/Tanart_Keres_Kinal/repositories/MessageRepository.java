package hu.elte.Tanart_Keres_Kinal.repositories;

import hu.elte.Tanart_Keres_Kinal.entities.Message;
import hu.elte.Tanart_Keres_Kinal.entities.Task;
import hu.elte.Tanart_Keres_Kinal.entities.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long>{
    
    @Query("SELECT m FROM Message m WHERE m.createdBy = ?1 order by m.text")
    List<Message> findAllByCreatedBy(User user);
    
    @Query("SELECT m FROM Message m WHERE m.text = ?1")
    Optional<Message> findByText(String text);
    
    @Query("SELECT m FROM Message m WHERE m.addressedTo = ?1 order by m.text")
    List<Message> findAllByAddressedTo(User user);
    
    @Query("SELECT m FROM Message m WHERE m.task = ?1 order by m.text")
    List<Message> findAllByTask(Task task);
}
