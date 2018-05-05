package pl.kozak127.swdramatic.domain.faction;

import com.google.common.collect.ImmutableList;
import lombok.Data;
import pl.kozak127.swdramatic.domain.player.Player;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Faction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    @Column
    private List<Player> players = ImmutableList.of();
}
