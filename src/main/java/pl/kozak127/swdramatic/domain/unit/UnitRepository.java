package pl.kozak127.swdramatic.domain.unit;

import org.springframework.data.repository.CrudRepository;
import pl.kozak127.swdramatic.domain.player.Player;

import java.util.List;

public interface UnitRepository extends CrudRepository<Unit, Long> {
    List<Unit> findAllByManager(Player manager);
}
