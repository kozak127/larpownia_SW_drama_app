package pl.kozak127.swdramatic.domain.field;

import lombok.Data;
import pl.kozak127.swdramatic.domain.player.Player;
import pl.kozak127.swdramatic.domain.resource.Resource;

import javax.persistence.*;

@Data
@Entity
public class FieldOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FieldOrderStatus status = FieldOrderStatus.WAITING;

    @ManyToOne
    private Resource resource;

    @ManyToOne
    private Field source;

    @ManyToOne
    private Field destination;

    @ManyToOne
    private Player manager;
}
