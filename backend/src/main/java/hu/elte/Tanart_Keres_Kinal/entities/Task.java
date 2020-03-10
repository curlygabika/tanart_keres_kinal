package hu.elte.Tanart_Keres_Kinal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Task extends BaseEntityWithCreationInfo {
    @Column
    private String title;
    
    @Column
    private String description;
    
    @Column
    private String place;
    
    @Column
    private String price;
    
    @JoinColumn(updatable = false, nullable = false)
    @ManyToOne(targetEntity = Subject.class)
    private Subject subjectType;
    
    @JsonIgnore
    @OneToMany(targetEntity = Message.class, mappedBy = "task")
    private List<Message> message;
}
