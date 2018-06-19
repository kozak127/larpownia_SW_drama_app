package pl.kozak127.swdramatic.domain.unit;

import org.springframework.data.repository.CrudRepository;
import pl.kozak127.swdramatic.domain.player.Player;

import java.util.Collection;

public interface UnitOrderRepository extends CrudRepository<UnitOrder, Long> {
    Collection<UnitOrder> findAllByManager(Player manager);
}
