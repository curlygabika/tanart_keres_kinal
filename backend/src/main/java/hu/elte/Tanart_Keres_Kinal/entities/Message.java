package hu.elte.Tanart_Keres_Kinal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "message")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Message extends BaseEntityWithCreationInfo {
    @Column(nullable = false)
    private String text;
    
    @JoinColumn(updatable = false, nullable = true)
    @ManyToOne(targetEntity = User.class)
    private User addressedTo;
    
    //@JsonIgnore
    @JoinColumn(updatable = false, nullable = true)
    @ManyToOne(targetEntity = Task.class)
    private Task task;
}
