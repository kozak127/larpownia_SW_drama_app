package pl.kozak127.swdramatic.domain.unit;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import pl.kozak127.swdramatic.domain.field.Field;
import pl.kozak127.swdramatic.domain.player.Player;
import pl.kozak127.swdramatic.domain.player.PlayerRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;

    private final PlayerRepository playerRepository;
    private UnitOrderRepository unitOrderRepository;

    UnitServiceImpl(UnitRepository unitRepository, PlayerRepository playerRepository) {
        this.unitRepository = unitRepository;
        this.playerRepository = playerRepository;
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

        List<Player> factionPlayers = playerRepository.findAllByFaction(player.getFaction());

        Collection<Unit> visibleEnemyUnits = unitRepository.findAllByFieldInAndManagerNotIn(visibleFields, factionPlayers);

        for (Unit playerUnit : playerUnits) {
            if (playerUnit.getRadar()) {
                visibleEnemyUnits.addAll(unitRepository.findAllByTypeAndManagerNotIn(UnitType.SPACESHIP, factionPlayers));
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

    @Override
    public UnitOrder saveOrder(UnitOrder unitOrder) {
        return unitOrderRepository.save(unitOrder);
    }

    @Override
    public Collection<UnitOrder> getAllOrders() {
        return Lists.newArrayList(unitOrderRepository.findAll());
    }

    @Override
    public Collection<UnitOrder> getOrdersForPlayer(Player player) {
        return unitOrderRepository.findAllByManager(player);
    }
}
