package hu.elte.Tanart_Keres_Kinal.repositories;

import hu.elte.Tanart_Keres_Kinal.entities.Subject;
import hu.elte.Tanart_Keres_Kinal.entities.Task;
import hu.elte.Tanart_Keres_Kinal.entities.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    
    @Query("SELECT t FROM Task t WHERE t.createdBy = ?1 order by t.title")
    List<Task> findAllByCreatedBy(User user);
    
    @Query("SELECT t FROM Task t WHERE t.title = ?1")
    Optional<Task> findByTitle(String title);
   
    @Query("SELECT t FROM Task t WHERE t.subjectType = ?1 order by t.title")
    List<Task> findAllBySubject(Subject subject);    
            
}
