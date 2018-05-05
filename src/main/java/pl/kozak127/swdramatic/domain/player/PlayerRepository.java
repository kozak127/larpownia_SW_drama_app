package pl.kozak127.swdramatic.domain.player;

import org.springframework.data.repository.CrudRepository;
import pl.kozak127.swdramatic.domain.faction.Faction;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    List<Player> findAllByFaction(Faction faction);
}
