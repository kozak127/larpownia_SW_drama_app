package pl.kozak127.swdramatic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.kozak127.swdramatic.domain.faction.Faction;
import pl.kozak127.swdramatic.domain.faction.FactionService;
import pl.kozak127.swdramatic.domain.field.Field;
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

    @RequestMapping(method = RequestMethod.GET)
    Collection<Field> getAll(@PathVariable String playerId) {
        Player player = getPlayer(playerId);
        if (player.isAdmin()) {
            return fieldService.findAll();
        }

        Faction faction = player.getFaction();
        return factionService.getFields(faction);
    }
}
