package pl.kozak127.swdramatic.domain.resource;

import lombok.Data;
import pl.kozak127.swdramatic.domain.field.Field;
import pl.kozak127.swdramatic.domain.player.Player;

import javax.persistence.*;

@Data
@Entity
public class ResourceTransit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Player owner;

    @Column(nullable = false)
    private Resource resource;

    @Column(nullable = false)
    private Field source;

    @Column(nullable = false)
    private Field destination;
}
