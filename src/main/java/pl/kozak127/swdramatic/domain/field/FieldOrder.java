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
    @Column(nullable = false)
    private FieldOrderStatus status = FieldOrderStatus.WAITING;

    @ManyToOne
    @Column(nullable = false)
    private Resource resource;

    @ManyToOne
    @Column(nullable = false)
    private Field source;

    @ManyToOne
    @Column(nullable = false)
    private Field destination;

    @ManyToMany
    @Column(nullable = false)
    private Player manager;
}
