package pl.kozak127.swdramatic.domain.player;

import java.util.Optional;

public interface PlayerService {
    Optional<Player> findById(Long id);
}
