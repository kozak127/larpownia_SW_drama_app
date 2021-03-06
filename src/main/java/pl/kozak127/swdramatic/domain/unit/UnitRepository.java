package pl.kozak127.swdramatic.domain.unit;

import org.springframework.data.repository.CrudRepository;
import pl.kozak127.swdramatic.domain.field.Field;
import pl.kozak127.swdramatic.domain.player.Player;

import java.util.Collection;

public interface UnitRepository extends CrudRepository<Unit, Long> {
    Collection<Unit> findAllByManager(Player manager);

    Collection<Unit> findAllByFieldIn(Collection<Field> fields);

    Collection<Unit> findAllByFieldInAndManagerNotIn(Collection<Field> fields, Collection<Player> managers);

    Collection<Unit> findAllByType(UnitType unitType);

    Collection<Unit> findAllByTypeAndManagerNotIn(UnitType unitType, Collection<Player> managers);
}
