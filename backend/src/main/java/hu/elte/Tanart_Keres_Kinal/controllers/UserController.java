package hu.elte.Tanart_Keres_Kinal.controllers;

import hu.elte.Tanart_Keres_Kinal.entities.Message;
import hu.elte.Tanart_Keres_Kinal.entities.Status;
import hu.elte.Tanart_Keres_Kinal.entities.Task;
import hu.elte.Tanart_Keres_Kinal.entities.User;
import hu.elte.Tanart_Keres_Kinal.repositories.MessageRepository;
import hu.elte.Tanart_Keres_Kinal.repositories.TaskRepository;
import hu.elte.Tanart_Keres_Kinal.repositories.UserRepository;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id){
        return new ResponseEntity(userRepository.findById(id), HttpStatus.OK);
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
        userRepository.save(entity);
        return new ResponseEntity(userRepository.findById(entity.getId()), HttpStatus.OK) ;
        
    }
    
}
