package pl.kozak127.swdramatic.domain.field;

import org.springframework.data.repository.CrudRepository;
import pl.kozak127.swdramatic.domain.player.Player;

import java.util.Collection;

public interface FieldOrderRepository extends CrudRepository<FieldOrder, Long> {
    Collection<FieldOrder> findAllByManager(Player manager);
}
