package pl.kozak127.swdramatic.controller;

import pl.kozak127.swdramatic.domain.player.Player;
import pl.kozak127.swdramatic.domain.player.PlayerService;

class AbstractController {

    private final PlayerService playerService;

    AbstractController(PlayerService playerService) {
        this.playerService = playerService;
    }

    Player getPlayer(String playerId) {
        System.out.println(playerId);
        Long userIdLong = Long.parseLong(playerId);
        return playerService
                .findById(userIdLong)
                .orElseThrow(IllegalArgumentException::new);
    }
}
