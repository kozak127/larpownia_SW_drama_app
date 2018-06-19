package pl.kozak127.swdramatic.domain.unit;

import lombok.Data;
import pl.kozak127.swdramatic.domain.field.Field;
import pl.kozak127.swdramatic.domain.player.Player;

import javax.persistence.*;

@Data
@Entity
public class UnitOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UnitOrderStatus status = UnitOrderStatus.WAITING;

    @Enumerated(EnumType.STRING)
    private UnitOrderType type = UnitOrderType.PEACE;

    @ManyToOne
    private Unit unit;

    @ManyToOne
    private Field source;

    @ManyToOne
    private Field destination;

    @ManyToOne
    private Player manager;
}
