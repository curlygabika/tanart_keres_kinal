package hu.elte.Tanart_Keres_Kinal.controllers;

import hu.elte.Tanart_Keres_Kinal.entities.Message;
import hu.elte.Tanart_Keres_Kinal.entities.Task;
import hu.elte.Tanart_Keres_Kinal.repositories.MessageRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;
    
    @GetMapping("")
    public ResponseEntity<Iterable<Message>> getAll(){
        return new ResponseEntity(messageRepository.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Message> get(@PathVariable Long id){
        return new ResponseEntity(messageRepository.findById(id), HttpStatus.OK);
    }
    
    @PostMapping("")
    public ResponseEntity<Message> update(@RequestBody Message entity){
        Optional<Message> baseEntity = messageRepository.findById(entity.getId());
        
        if(baseEntity.isPresent()){
            messageRepository.save(entity);
            return new ResponseEntity(messageRepository.findById(entity.getId()), HttpStatus.OK) ;
        }
        
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("")
    public ResponseEntity<Message> create(@RequestBody Message entity){
        messageRepository.save(entity);
        return new ResponseEntity(messageRepository.findById(entity.getId()), HttpStatus.OK) ;
    }
}
