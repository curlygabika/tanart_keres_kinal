package hu.elte.Tanart_Keres_Kinal.controllers;

import hu.elte.Tanart_Keres_Kinal.entities.Message;
import hu.elte.Tanart_Keres_Kinal.entities.Status;
import hu.elte.Tanart_Keres_Kinal.entities.Task;
import hu.elte.Tanart_Keres_Kinal.entities.User;
import hu.elte.Tanart_Keres_Kinal.repositories.MessageRepository;
import hu.elte.Tanart_Keres_Kinal.repositories.TaskRepository;
import hu.elte.Tanart_Keres_Kinal.repositories.UserRepository;
import hu.elte.Tanart_Keres_Kinal.security.AuthenticatedUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired 
    private TaskRepository taskRepository;
    
    @Autowired 
    private AuthenticatedUser authenticatedUser;
    
    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id){
        return new ResponseEntity(userRepository.findById(id), HttpStatus.OK);
    }
    
    @GetMapping("/fullName/{fullName}")
    public ResponseEntity<User> findByFullName(@PathVariable String fullName){
        User user = userRepository.findByFullName(fullName);
        return new ResponseEntity(user, HttpStatus.OK);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<Iterable<User>> getUsersByStatus(@PathVariable Status status){
        List<User> users = userRepository.getUsersByStatus(status);
        return new ResponseEntity(users, HttpStatus.OK);
    }
    
    
    @GetMapping("/{id}/task")
    public ResponseEntity<Iterable<Task>> getAllTask(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        
        if (user.isPresent()) {
            return new ResponseEntity(taskRepository.findAllByCreatedBy(user.get()), HttpStatus.OK);
        }
        
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{id}/message")
    public ResponseEntity<Iterable<Message>> getAllMessage(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        
        if (user.isPresent()) {
            return new ResponseEntity(messageRepository.findAllByCreatedBy(user.get()), HttpStatus.OK);
        }
        
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("")
    public ResponseEntity<Iterable<User>> getAll(){
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);
    }
    
    @PostMapping("")
    public ResponseEntity<User> update(@RequestBody User entity){
        Optional<User> baseEntity = userRepository.findById(entity.getId());
        
        if(baseEntity.isPresent()){
            userRepository.save(entity);
            return new ResponseEntity(userRepository.findById(entity.getId()), HttpStatus.OK) ;
        }
        
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("")
    public ResponseEntity<User> create(@RequestBody User entity){
        entity = userRepository.save(entity);
        return new ResponseEntity(entity, HttpStatus.OK) ;      
    }
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Optional<User> oUser = userRepository.findByUserName(user.getUserName());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.STUDENT);
        user.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok(userRepository.save(user));
    }
    
    @PostMapping("login")
    public ResponseEntity login(@RequestBody User user) {
        return ResponseEntity.ok(authenticatedUser.getUser());
    }
    
    @GetMapping("logoff")
    public ResponseEntity logoff() {
        authenticatedUser.setUser(null);
        return ResponseEntity.ok(0);
    }
}
