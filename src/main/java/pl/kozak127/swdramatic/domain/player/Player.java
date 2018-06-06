package pl.kozak127.swdramatic.domain.player;

import lombok.Data;
import pl.kozak127.swdramatic.domain.faction.Faction;

import javax.persistence.*;

@Data
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Faction faction;

    private boolean admin = false;
}
