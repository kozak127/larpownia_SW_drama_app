package pl.kozak127.swdramatic.domain.unit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.kozak127.swdramatic.domain.field.Field;
import pl.kozak127.swdramatic.domain.player.Player;

import javax.persistence.*;

@Data
@Entity
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private UnitType type;

    @ManyToOne
    private Field field;

    @JsonIgnore
    @ManyToOne
    private Player manager;

    private Integer successfulBlockadeChance = 0;

    private Integer hitPoints = 100;

    private Integer attack = 10;

    private Integer speed = 1;

    private Boolean radar = false;

    private Boolean mutiny = false;
}
