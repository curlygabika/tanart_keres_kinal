package hu.elte.Tanart_Keres_Kinal;

import hu.elte.Tanart_Keres_Kinal.entities.Subject;
import hu.elte.Tanart_Keres_Kinal.entities.User;
import hu.elte.Tanart_Keres_Kinal.repositories.TaskRepository;
import hu.elte.Tanart_Keres_Kinal.repositories.UserRepository;
import hu.elte.Tanart_Keres_Kinal.entities.Task;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import static org.assertj.core.api.Assertions.assertThat;

import hu.elte.Tanart_Keres_Kinal.repositories.UserRepository;
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
    public void findAllUsersInUserRepository(){
        Iterable<Task> tasks = taskRepository.findAll();
        int numberOfUsers = 5;
        assertThat(tasks).hasSize(numberOfUsers);
    }

    @Test
    public void foundTaskCreatedByGivenUserNameInTaskRepository(){
        //multiple tasks are created by this user (5)
        String userFullName = "Andrea Bakocs";
        User user = userRepository.findByFullName(userFullName);
        assertThat(user).isNotNull();
        assertThat(user.getFullName() == "Andrea Bakocs");

        List<Task> tasks = taskRepository.findAllByCreatedBy(user);
        assertThat(tasks).isNotNull();
        assertThat(tasks).hasSize(5);
        assertThat(tasks.get(0).getCreatedBy() == user);

    }

    @Test
    public void foundZeroTaskCreatedByGivenUserNameInTaskRepository(){
        //no tasks has been created by this user
        String userFullName = "Jancsi Tolnai";
        User user = userRepository.findByFullName(userFullName);
        assertThat(user).isNotNull();
        assertThat(user.getFullName() == "Jancsi Tolnai");

        List<Task> tasks = taskRepository.findAllByCreatedBy(user);
        assertThat(tasks).hasSize(0);
    }

    @Test
    public void foundTaskByTitleInTaskRepository(){
        String title = "Programming for kids";
        Optional<Task> task = taskRepository.findByTitle(title);
        assertThat(task).isNotNull();
        assertThat(task.isPresent());
        assertThat(task.get().getTitle() == "Programming for kids");
        assertThat(task.get().getPlace() == "Budapest Koer utca 1/a");

        title = "Tech course";
        task = taskRepository.findByTitle(title);
        assertThat(task).isNotNull();
        assertThat(task.isPresent());
        assertThat(task.get().getTitle() == "Tech course");
        assertThat(task.get().getPrice() == 4000.0);
    }

    @Test
    public void foundZeroTaskByTitleInTaskRepository(){
        String title = "Not a valid title";
        Optional<Task> task = taskRepository.findByTitle(title);
        assertThat(task).isNotNull();
        assertThat(!task.isPresent());
    }

   /* @Test
    public void findUsersByGivenSubject(){

    }*/

    /*@Test
    public void whenCreatedFoundByIdInTaskRepository(){
        Task task = new Task(
                "biology",
                "this is biology",
                "bela u 1",
                2000.0,
                null,
                null,
                null);
        taskRepository.save(task);
        assertThat(taskRepository.findById(task.getId())).isNotNull();
        Iterable<Task> tasks = taskRepository.findAll();
        int numberOfTasks = 6;
        assertThat(tasks).hasSize(numberOfTasks);
    }*/

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
}
