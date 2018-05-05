package pl.kozak127.swdramatic.domain.unit;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import pl.kozak127.swdramatic.domain.field.Field;
import pl.kozak127.swdramatic.domain.player.Player;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;

    UnitServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public Collection<Unit> findAll() {
        return Lists.newArrayList(unitRepository.findAll());
    }

    @Override
    public Collection<Unit> findVisibleEnemy(Player player) {
        Collection<Unit> playerUnits = unitRepository.findAllByManager(player);

        Set<Field> visibleFields = playerUnits
                .stream()
                .map(Unit::getField)
                .collect(Collectors.toSet());

        Collection<Unit> visibleEnemyUnits = unitRepository.findAllByFieldIn(visibleFields);

        for (Unit playerUnit : playerUnits) {
            if (playerUnit.getRadar()) {
                visibleEnemyUnits.addAll(unitRepository.findAllByType(UnitType.SPACESHIP));
                break;
            }
        }

        return visibleEnemyUnits;
    }

    @Override
    public Optional<Unit> findById(Long unitId) {
        return unitRepository.findById(unitId);
    }

    @Override
    public Unit save(Unit unit) {
        return unitRepository.save(unit);
    }
}
