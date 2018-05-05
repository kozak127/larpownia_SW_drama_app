package pl.kozak127.swdramatic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kozak127.swdramatic.domain.faction.Faction;
import pl.kozak127.swdramatic.domain.faction.FactionService;
import pl.kozak127.swdramatic.domain.field.Field;
import pl.kozak127.swdramatic.domain.field.FieldOrder;
import pl.kozak127.swdramatic.domain.field.FieldService;
import pl.kozak127.swdramatic.domain.player.Player;
import pl.kozak127.swdramatic.domain.player.PlayerService;

import java.util.Collection;

@RestController
@RequestMapping("/{playerId}/field")
public class FieldController extends AbstractController {

    private final FactionService factionService;
    private final FieldService fieldService;

    @Autowired
    FieldController(PlayerService playerService, FactionService factionService, FieldService fieldService) {
        super(playerService);
        this.factionService = factionService;
        this.fieldService = fieldService;
    }

    @RequestMapping(path = "/order", method = RequestMethod.POST)
    ResponseEntity<?> newOrder(@RequestBody FieldOrder fieldOrder, @PathVariable String playerId) {
        Field source = fieldOrder.getSource();
        Player player = getPlayer(playerId);

        if (!source.getManagers().contains(player)) {
            return new ResponseEntity<>(new CustomErrorType("Unable to create order. Player " + playerId + " is not manager"), HttpStatus.FORBIDDEN);
        }
        fieldService.saveOrder(fieldOrder);
        return new ResponseEntity<>(fieldOrder, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Collection<Field>> getAll(@PathVariable String playerId) {
        Player player = getPlayer(playerId);
        if (player.isAdmin()) {
            return new ResponseEntity<>(fieldService.findAll(), HttpStatus.OK);
        }

        Faction faction = player.getFaction();
        return new ResponseEntity<>(factionService.getFields(faction), HttpStatus.OK);
    }
}
