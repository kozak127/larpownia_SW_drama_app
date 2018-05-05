package pl.kozak127.swdramatic.controller;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.kozak127.swdramatic.domain.faction.Faction;
import pl.kozak127.swdramatic.domain.faction.FactionService;
import pl.kozak127.swdramatic.domain.player.Player;
import pl.kozak127.swdramatic.domain.player.PlayerService;
import pl.kozak127.swdramatic.domain.unit.Unit;
import pl.kozak127.swdramatic.domain.unit.UnitService;

import java.util.Collection;

@RestController
@RequestMapping("/{playerId}/unit")
public class UnitController extends AbstractController {

    private final FactionService factionService;
    private final UnitService unitService;

    @Autowired
    UnitController(PlayerService playerService, FactionService factionService, UnitService unitService) {
        super(playerService);
        this.factionService = factionService;
        this.unitService = unitService;
    }

    @RequestMapping(path = "/allied", method = RequestMethod.GET)
    Collection<Unit> getAllAllied(@PathVariable String playerId) {
        Player player = getPlayer(playerId);
        if (player.isAdmin()) {
            return unitService.findAll();
        }

        Faction faction = player.getFaction();
        return factionService.getUnits(faction);
    }

    @RequestMapping(path = "/enemy", method = RequestMethod.GET)
    Collection<Unit> getAllVisibleEnemy(@PathVariable String playerId) {
        Player player = getPlayer(playerId);
        if (player.isAdmin()) {
            return ImmutableList.of();
        }
        return unitService.findVisibleEnemy(player);
    }

    @RequestMapping(path = "/mutiny/{unitId}/{mutiny}", method = RequestMethod.PUT)
    Unit mutiny(@PathVariable String playerId, @PathVariable String unitId, @PathVariable String mutiny) {

        Player player = getPlayer(playerId);
        if (!player.isAdmin()) {
            throw new IllegalStateException();
        }

        Long unitIdLong = Long.decode(unitId);
        Unit unit = unitService.findById(unitIdLong).orElseThrow(IllegalArgumentException::new);
        unit.setMutiny(Boolean.getBoolean(mutiny));
        return unitService.save(unit);
    }
}
