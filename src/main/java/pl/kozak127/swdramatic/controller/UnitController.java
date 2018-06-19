package pl.kozak127.swdramatic.controller;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kozak127.swdramatic.domain.faction.Faction;
import pl.kozak127.swdramatic.domain.faction.FactionService;
import pl.kozak127.swdramatic.domain.player.Player;
import pl.kozak127.swdramatic.domain.player.PlayerService;
import pl.kozak127.swdramatic.domain.unit.Unit;
import pl.kozak127.swdramatic.domain.unit.UnitOrder;
import pl.kozak127.swdramatic.domain.unit.UnitService;

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
    ResponseEntity<?> getAllAllied(@PathVariable String playerId) {
        Player player = getPlayer(playerId);
        if (player.isAdmin()) {
            return new ResponseEntity<>(unitService.findAll(), HttpStatus.OK);
        }

        Faction faction = player.getFaction();
        return new ResponseEntity<>(factionService.getUnits(faction), HttpStatus.OK);
    }

    @RequestMapping(path = "/enemy", method = RequestMethod.GET)
    ResponseEntity<?> getAllVisibleEnemy(@PathVariable String playerId) {
        Player player = getPlayer(playerId);
        if (player.isAdmin()) {
            return new ResponseEntity<>(ImmutableList.of(), HttpStatus.OK);
        }
        return new ResponseEntity<>(unitService.findVisibleEnemy(player), HttpStatus.OK);
    }

    @RequestMapping(path = "/order", method = RequestMethod.POST)
    ResponseEntity<?> newOrder(@RequestBody UnitOrder unitOrder, @PathVariable String playerId) {
        Unit unit = unitOrder.getUnit();
        Player player = getPlayer(playerId);

        if (!unit.getManager().equals(player)) {
            return new ResponseEntity<>(new CustomErrorType("Unable to create order. Player " + playerId + " is not manager"), HttpStatus.FORBIDDEN);
        }
        unitService.saveOrder(unitOrder);
        return new ResponseEntity<>(unitOrder, HttpStatus.OK);
    }

    @RequestMapping(path = "/mutiny/{unitId}/{mutiny}", method = RequestMethod.PUT)
    ResponseEntity<?> mutiny(@PathVariable String playerId, @PathVariable String unitId, @PathVariable String mutiny) {

        Player player = getPlayer(playerId);
        if (!player.isAdmin()) {
            throw new IllegalStateException();
        }

        Long unitIdLong = Long.decode(unitId);
        Unit unit = unitService.findById(unitIdLong).orElseThrow(IllegalArgumentException::new);
        unit.setMutiny(Boolean.parseBoolean(mutiny));
        return new ResponseEntity<>(unitService.save(unit), HttpStatus.OK);
    }
}
