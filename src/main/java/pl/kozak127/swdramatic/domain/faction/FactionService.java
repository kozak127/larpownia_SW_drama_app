package pl.kozak127.swdramatic.domain.faction;

import pl.kozak127.swdramatic.domain.field.Field;
import pl.kozak127.swdramatic.domain.unit.Unit;

import java.util.List;

public interface FactionService {
    List<Field> getFields(Faction faction);

    List<Unit> getUnits(Faction faction);
}
