package hu.elte.Tanart_Keres_Kinal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "difficulty")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Difficulty extends BaseEntity {
    //enum Level {begginer, intermediate, expert}
    @Column(nullable = false, unique = true)
    private String level;
}
