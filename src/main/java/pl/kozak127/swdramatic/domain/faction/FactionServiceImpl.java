package pl.kozak127.swdramatic.domain.faction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kozak127.swdramatic.domain.field.Field;
import pl.kozak127.swdramatic.domain.field.FieldRepository;
import pl.kozak127.swdramatic.domain.unit.Unit;
import pl.kozak127.swdramatic.domain.unit.UnitRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FactionServiceImpl implements FactionService {

    private final FieldRepository fieldRepository;

    private final UnitRepository unitRepository;

    @Autowired
    public FactionServiceImpl(FieldRepository fieldRepository, UnitRepository unitRepository) {
        this.fieldRepository = fieldRepository;
        this.unitRepository = unitRepository;
    }

    @Override
    public List<Field> getFields(Faction faction) {
        return fieldRepository.findAllByFaction(faction);
    }

    @Override
    public List<Unit> getUnits(Faction faction) {
        return faction.getPlayers()
                .stream()
                .map(unitRepository::findAllByManager)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
