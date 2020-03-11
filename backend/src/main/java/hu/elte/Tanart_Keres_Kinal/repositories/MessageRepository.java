package hu.elte.Tanart_Keres_Kinal.repositories;

import hu.elte.Tanart_Keres_Kinal.entities.Message;
import hu.elte.Tanart_Keres_Kinal.entities.Task;
import hu.elte.Tanart_Keres_Kinal.entities.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long>{
    List<Message> findAllByCreatedBy(User user);
}
