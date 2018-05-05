package pl.kozak127.swdramatic.domain.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }
}
