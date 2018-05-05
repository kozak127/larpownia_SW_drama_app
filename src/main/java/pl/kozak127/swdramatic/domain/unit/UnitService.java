package pl.kozak127.swdramatic.domain.unit;

import pl.kozak127.swdramatic.domain.player.Player;

import java.util.Collection;
import java.util.Optional;

public interface UnitService {
    Collection<Unit> findAll();

    Collection<Unit> findVisibleEnemy(Player player);

    Optional<Unit> findById(Long unitId);

    Unit save(Unit unit);
}
