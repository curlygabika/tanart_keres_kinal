package hu.elte.Tanart_Keres_Kinal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subject")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Subject extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    
    @JoinColumn(updatable = false, nullable = false)
    @ManyToOne(targetEntity = Difficulty.class)
    private Difficulty level;
    
    @JsonIgnore
    @OneToMany(targetEntity = Task.class, mappedBy = "subjectType")
    private List<Task> task;
}
