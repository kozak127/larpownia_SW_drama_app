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

    @ManyToOne
    private Player owner;

    @OneToOne
    private Resource resource;

    @ManyToOne
    private Field source;

    @ManyToOne
    private Field destination;
}
