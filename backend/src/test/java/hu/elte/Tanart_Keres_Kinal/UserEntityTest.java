package hu.elte.Tanart_Keres_Kinal;

import hu.elte.Tanart_Keres_Kinal.repositories.UserRepository;
import hu.elte.Tanart_Keres_Kinal.entities.User;
import hu.elte.Tanart_Keres_Kinal.entities.Status;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserEntityTest {
    
    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private UserRepository userRepository;
    
    @Test
    public void injectedComponentsAreNotNull(){
      assertThat(dataSource).isNotNull();
      assertThat(jdbcTemplate).isNotNull();
      assertThat(entityManager).isNotNull();
      assertThat(userRepository).isNotNull();
    }
    
    @Test
    public void findAllUsersInUserRepository(){
        Iterable<User> users = userRepository.findAll();
        int numberOfUsers = 3;
        assertThat(users).hasSize(numberOfUsers);
    }
    
    @Test
    public void foundUserByGivenUserNameInUserRepository(){
        String userName = "andrea112";
        Optional<User> user = userRepository.findByUserName(userName);
        assertThat(user).isNotNull();
        assertThat(user.isPresent());
        assertThat(user.get().getUserName()).isEqualTo("andrea112");
        
        userName = "jancsika";
        user = userRepository.findByUserName(userName);
        assertThat(user).isNotNull();
        assertThat(user.isPresent());
        assertThat(user.get().getUserName()).isEqualTo("jancsika");
    }
    
    @Test
    public void foundUserByGivenFullNameInUserRepository(){
        String fullName = "Andrea Bakocs";
        User user = userRepository.findByFullName(fullName);
        assertThat(user).isNotNull();
        assertThat(user.getFullName()).isEqualTo("Andrea Bakocs");
        
        fullName = "Jancsi Tolnai";
        user = userRepository.findByFullName(fullName);
        assertThat(user).isNotNull();
        assertThat(user.getFullName()).isEqualTo("Jancsi Tolnai");
    }
    
    @Test
    public void findUsersByGivenStatus(){
        Status status = Status.TEACHER;
        List<User> users = userRepository.getUsersByStatus(status);
        assertThat(users).isNotNull();
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getStatus()).isEqualTo(Status.TEACHER);
        status = Status.STUDENT;
        users = userRepository.getUsersByStatus(status);
        assertThat(users).isNotNull();
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getStatus()).isEqualTo(Status.STUDENT);
    }
    
    @Test
    public void whenCreatedFoundByIdInUserRepository(){
        User user = new User(
                "paradicsom",
                "budapest56",
                Status.STUDENT,
                "Ruszin Adrien",
                null,
                null,
                LocalDateTime.now(),
                null,
                null);
        userRepository.save(user);
        assertThat(userRepository.findById(user.getId())).isNotNull();
        Iterable<User> users = userRepository.findAll();
        int numberOfUsers = 4;
        assertThat(users).hasSize(numberOfUsers);
    }
    @Test 
    public void whenUpdatedChangesFoundInUserRepository(){
        User user = userRepository.findById(1L).get();
        user.setFullName("King");
        user.setPhoneNumber("+36701238965");
        int numberOfUsers = 3;
        assertThat(userRepository.save(user)).isNotNull();
        assertThat(userRepository.findAll()).hasSize(numberOfUsers);
        
        User newuser = userRepository.findById(1L).get();
        assertThat(user).isEqualTo(newuser);
        assertThat(user.getFullName()).isEqualTo(newuser.getFullName());        
    }
    
    
}