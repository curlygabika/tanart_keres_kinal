package hu.elte.Tanart_Keres_Kinal.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntityWithCreationInfo extends BaseEntity {
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
    
    @JoinColumn(updatable = false, nullable = false)
    @ManyToOne(targetEntity = User.class)
    private User createdBy;
}
