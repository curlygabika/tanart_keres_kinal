package hu.elte.Tanart_Keres_Kinal.controllers;

import hu.elte.Tanart_Keres_Kinal.entities.Task;
import hu.elte.Tanart_Keres_Kinal.repositories.TaskRepository;
import hu.elte.Tanart_Keres_Kinal.repositories.UserRepository;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("task")
public class TaskController{
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private UserRepository userRepository;
    
 
    @GetMapping("")
    public ResponseEntity<Iterable<Task>> getAll(){
        return new ResponseEntity(taskRepository.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Task> get(@PathVariable Long id){
        return new ResponseEntity(taskRepository.findById(id), HttpStatus.OK);
    }
    
    @GetMapping("createdBy/{id}")
    public ResponseEntity<Task> getByCreatedUser(@PathVariable Long id){
        return new ResponseEntity(
                taskRepository.findAllByCreatedBy(
                        userRepository.findById(id).get()
                ), 
                HttpStatus.OK);
    }
    
    @PostMapping("")
    public ResponseEntity<Task> update(@RequestBody Task entity){
        Optional<Task> baseEntity = taskRepository.findById(entity.getId());
        
        if(baseEntity.isPresent()){
            taskRepository.save(entity);
            return new ResponseEntity(taskRepository.findById(entity.getId()), HttpStatus.OK) ;
        }
        
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("")
    public ResponseEntity<Task> create(@RequestBody Task entity){
        taskRepository.save(entity);
        return new ResponseEntity(taskRepository.findById(entity.getId()), HttpStatus.OK) ;
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        
        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

