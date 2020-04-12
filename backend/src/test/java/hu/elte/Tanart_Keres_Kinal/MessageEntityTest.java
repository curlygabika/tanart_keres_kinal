package hu.elte.Tanart_Keres_Kinal;

import hu.elte.Tanart_Keres_Kinal.entities.Task;
import hu.elte.Tanart_Keres_Kinal.repositories.MessageRepository;
import hu.elte.Tanart_Keres_Kinal.repositories.TaskRepository;
import hu.elte.Tanart_Keres_Kinal.repositories.UserRepository;
import hu.elte.Tanart_Keres_Kinal.entities.Message;
import hu.elte.Tanart_Keres_Kinal.entities.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MessageEntityTest {
    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private MessageRepository messageRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private TaskRepository taskRepository;

    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(messageRepository).isNotNull();
        assertThat(taskRepository).isNotNull();
    }

    @Test
    public void findAllMessagesInMessageRepository(){
        Iterable<Message> messages = messageRepository.findAll();
        int numberOfMessages = 2;
        assertThat(messages).hasSize(numberOfMessages);
    }

    @Test
    public void foundAllMessagesCreatedByUserInMessageRepository(){

        String user1 = "andrea112";
        Optional<User> user = userRepository.findByUserName(user1);
        assertThat(user).isNotNull();
        assertThat(user).isPresent();

        List<Message> message = messageRepository.findAllByCreatedBy(user.get());
        assertThat(message).isNotNull();
        assertThat(message).hasSize(1);
        assertThat(message.get(0).getText()).isEqualTo("Hello");

        user1 = "jancsika";
        user = userRepository.findByUserName(user1);
        assertThat(user).isNotNull();
        assertThat(user).isPresent();

        message = messageRepository.findAllByCreatedBy(user.get());
        assertThat(message).isNotNull();
        assertThat(message).hasSize(0);

    }


    @Test
    public void foundMessageByTextInMessageRepository(){

        String text = "Start message";
        Optional<Message> message = messageRepository.findByText(text);
        assertThat(message).isNotNull();
        assertThat(message.isPresent());
        assertThat(message.get().getText()).isEqualTo("Start message");

    }

    @Test
    public void foundAllMessagesByAddressedToInMessageRepository(){

        String user1 = "andrea112";
        Optional<User> user = userRepository.findByUserName(user1);
        assertThat(user).isNotNull();
        assertThat(user).isPresent();
        List<Message> message = messageRepository.findAllByAddressedTo(user.get());

        assertThat(message).isNotNull();
        assertThat(message).hasSize(1);
        assertThat(message.get(0).getText()).isEqualTo("Start message");

    }


    @Test
    public void foundAllMessagesByTaskInMessageRepository(){

        String title = "English course";
        Optional<Task> task =  taskRepository.findByTitle(title);
        assertThat(task).isNotNull();
        assertThat(task).isPresent();
        List<Message> messages = messageRepository.findAllByTask(task.get());

        assertThat(messages).isNotNull();
        assertThat(messages).hasSize(1);
        assertThat(messages.get(0).getText()).isEqualTo("Hello");

    }

}
