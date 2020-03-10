package hu.elte.Tanart_Keres_Kinal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User extends BaseEntity {
    
    @Column(nullable = false)
    private String userName;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Column
    private String fullName;
    
    @Column
    private String mail;
    
    @Column
    private String phoneNumber;
    
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
    
    @JsonIgnore
    @OneToMany(targetEntity = Task.class, mappedBy = "createdBy")
    private List<Task> createdTask;
    
    //@JsonIgnore
    //@OneToMany(targetEntity = Task.class, mappedBy = "updatedBy")
    //private List<Task> updatedTask;
    
    @JsonIgnore
    @OneToMany(targetEntity = Message.class, mappedBy = "createdBy")
    private List<Message> createdMessage;
}
