package pl.kozak127.swdramatic.domain.field;

import org.springframework.data.repository.CrudRepository;
import pl.kozak127.swdramatic.domain.faction.Faction;

import java.util.List;

public interface FieldRepository extends CrudRepository<Field, Long> {
    List<Field> findAllByFaction(Faction faction);
}
