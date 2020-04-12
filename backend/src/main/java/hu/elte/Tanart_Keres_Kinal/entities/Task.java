package hu.elte.Tanart_Keres_Kinal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.Random;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Task extends BaseEntityWithCreationInfo {
    @Column
    private String title;
    
    @Column
    private String description;
    
    @Column
    private String place;
    
    @Column
    private double price;
    
    @JoinColumn(updatable = false, nullable = false)
    @ManyToOne(targetEntity = Subject.class)
    private Subject subjectType;
    
    @JsonIgnore
    @OneToMany(targetEntity = Message.class, mappedBy = "task")
    private List<Message> message;
    
    private  double salePrice() {
        Random rand = new Random();
        int randomsale = rand.nextInt((5 - 1) + 0);
        double saleprice = price * (1- (0.1 * randomsale));
    return saleprice ;
    }
}
