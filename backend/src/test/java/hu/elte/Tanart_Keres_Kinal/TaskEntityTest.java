package hu.elte.Tanart_Keres_Kinal;

import hu.elte.Tanart_Keres_Kinal.entities.Subject;
import hu.elte.Tanart_Keres_Kinal.entities.User;
import hu.elte.Tanart_Keres_Kinal.repositories.TaskRepository;
import hu.elte.Tanart_Keres_Kinal.repositories.UserRepository;
import hu.elte.Tanart_Keres_Kinal.entities.Task;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import static org.assertj.core.api.Assertions.assertThat;

import hu.elte.Tanart_Keres_Kinal.repositories.UserRepository;
import java.time.LocalDateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskEntityTest {
    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private TaskRepository taskRepository;
    @Autowired private UserRepository userRepository;

    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(taskRepository).isNotNull();
        assertThat(userRepository).isNotNull();
    }

    @Test
    public void findAllTasksInTaskRepository(){
        Iterable<Task> tasks = taskRepository.findAll();
        int numberOfTasks = 5;
        assertThat(tasks).hasSize(numberOfTasks);
    }

    @Test
    public void foundTaskCreatedByGivenUserNameInTaskRepository(){
        //multiple tasks are created by this user (5)
        String userFullName = "Andrea Bakocs";
        User user = userRepository.findByFullName(userFullName);
        List<Task> tasks = taskRepository.findAllByCreatedBy(user);
        assertThat(tasks).isNotNull();
        assertThat(tasks).hasSize(5);
        assertThat(tasks.get(0).getCreatedBy()).isEqualTo(user);
        
        userFullName = "Jancsi Tolnai";
        user = userRepository.findByFullName(userFullName);
        tasks = taskRepository.findAllByCreatedBy(user);
        assertThat(tasks).hasSize(0);
    }

    @Test
    public void foundTaskByTitleInTaskRepository(){
        String title = "Programming for kids";
        Optional<Task> task = taskRepository.findByTitle(title);
        assertThat(task).isNotNull();
        assertThat(task.isPresent());
        assertThat(task.get().getTitle()).isEqualTo("Programming for kids");
        assertThat(task.get().getPlace()).isEqualTo("Budapest Koer utca 1/a");

        title = "Tech course";
        task = taskRepository.findByTitle(title);
        assertThat(task).isNotNull();
        assertThat(task.isPresent());
        assertThat(task.get().getTitle()).isEqualTo("Tech course");
        assertThat(task.get().getPrice()).isEqualTo(4000.0);
       
        title = "Not a valid title";
        task = taskRepository.findByTitle(title);
        assertThat(!task.isPresent());        
        
    }


    /*@Test
    public void findUsersByGivenSubject(){
        Subject subject = new Subject(
                "art",
                null,
                null);
        subject.setId(1L);
        Task task = new Task(
                "music classes",
                "play the piano",
                "zrinyi utca 14.",
                2500.0,
                subject,
                null);
        task.setCreatedAt(LocalDateTime.now());
        User user = userRepository.findById(2L).get();
        task.setCreatedBy(user);
        taskRepository.save(task);
        List <Task> tasks = taskRepository.findAllBySubject(subject);
        assertThat(tasks).isNotNull();
        assertThat(tasks).hasSize(1);
        assertThat(tasks.get(0).getSubjectType()).isEqualTo(subject);
    }*/

    @Test
    public void whenCreatedFoundByIdInTaskRepository(){
        Subject subject = new Subject(
                "art",
                null,
                null);
        subject.setId(1L);        
        Task task = new Task(
                "biology",
                "this is biology",
                "bela u 1",
                2000.0,
                subject,
                null);
        task.setCreatedAt(LocalDateTime.now());
        User user = userRepository.findById(2L).get();
        task.setCreatedBy(user);
        taskRepository.save(task);
        assertThat(taskRepository.findById(6L)).isNotNull();
        Iterable<Task> tasks = taskRepository.findAll();
        int numberOfTasks = 6;
        assertThat(tasks).hasSize(numberOfTasks);
    }

    @Test
    public void whenUpdatedChangesFoundInTaskRepository(){
        Task task = taskRepository.findById(1L).get();
        task.setTitle("Chemistry");
        int numberOfTasks = 5;
        assertThat(taskRepository.save(task)).isNotNull();
        assertThat(taskRepository.findAll()).hasSize(numberOfTasks);

        Task newTask = taskRepository.findById(1L).get();
        assertThat(task).isEqualTo(newTask);
        assertThat(task.getTitle()).isEqualTo(newTask.getTitle());
    }
    
    @Test
    public void testSalePrice(){
        Integer  rand = 5;
        Task task = taskRepository.findByTitle("Math course").get();
        assertThat(task.getPrice()).isEqualTo(5000.0);
        assertThat(task.salePrice()).isBetween(2500.0, 5000.0);
        task = taskRepository.findByTitle("English course").get();
        assertThat(task.getPrice()).isEqualTo(1000.0);
        assertThat(task.salePrice()).isBetween(500.0, 1000.0);        
    }
}