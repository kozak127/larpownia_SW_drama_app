package pl.kozak127.swdramatic.domain.unit;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnitType type;

    @ManyToOne
    @Column()
    private Field field;

    @OneToMany
    @Column(nullable = false)
    private Player manager;

    @Column(nullable = false)
    private Integer successfulBlockadeChance = 0;

    @Column(nullable = false)
    private Integer hitPoints = 100;

    @Column(nullable = false)
    private Integer attack = 10;

    @Column(nullable = false)
    private Integer speed = 1;
}
