package pl.kozak127.swdramatic.domain.field;

import com.google.common.collect.ImmutableList;
import lombok.Data;
import pl.kozak127.swdramatic.domain.faction.Faction;
import pl.kozak127.swdramatic.domain.player.Player;
import pl.kozak127.swdramatic.domain.resource.Resource;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @Column
    private List<Player> managers = ImmutableList.of();

    @ManyToOne
    @Column
    private Faction faction;

    @OneToMany
    @Column
    private List<Resource> resources = ImmutableList.of();
}
