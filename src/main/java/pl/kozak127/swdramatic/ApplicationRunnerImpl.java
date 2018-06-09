package pl.kozak127.swdramatic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.kozak127.swdramatic.domain.faction.Faction;
import pl.kozak127.swdramatic.domain.faction.FactionRepository;
import pl.kozak127.swdramatic.domain.field.Field;
import pl.kozak127.swdramatic.domain.field.FieldRepository;
import pl.kozak127.swdramatic.domain.player.Player;
import pl.kozak127.swdramatic.domain.player.PlayerRepository;
import pl.kozak127.swdramatic.domain.resource.Resource;
import pl.kozak127.swdramatic.domain.resource.ResourceRepository;
import pl.kozak127.swdramatic.domain.resource.ResourceType;
import pl.kozak127.swdramatic.domain.unit.Unit;
import pl.kozak127.swdramatic.domain.unit.UnitRepository;
import pl.kozak127.swdramatic.domain.unit.UnitType;

import java.util.Arrays;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private FactionRepository factionRepository;

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Faction miesotarianie = new Faction();
        factionRepository.save(miesotarianie);

        Faction weganie = new Faction();
        factionRepository.save(weganie);

        System.out.println("DUPA DUPA DUPA");
        Player player1 = new Player();
        player1.setFaction(miesotarianie);
        playerRepository.save(player1);
        System.out.println("PLAYER 1 ID " + player1.getId());

        Player player2 = new Player();
        player2.setFaction(weganie);
        playerRepository.save(player2);
        System.out.println("PLAYER 2 ID " + player2.getId());

        Resource resource1 = new Resource();
        resource1.setType(ResourceType.FOOD);
        resourceRepository.save(resource1);

        Resource resource2 = new Resource();
        resource2.setType(ResourceType.FUEL);
        resourceRepository.save(resource2);

        Field field1 = new Field();
        field1.setName("zadupie");
        field1.setFaction(miesotarianie);
        field1.setManagers(Arrays.asList(player1));
        field1.setResources(Arrays.asList(resource1));
        fieldRepository.save(field1);

        Field field2 = new Field();
        field2.setName("bialystok");
        field2.setFaction(weganie);
        field2.setManagers(Arrays.asList(player2));
        field2.setResources(Arrays.asList(resource2));
        fieldRepository.save(field2);

        Unit unit11 = new Unit();
        unit11.setName("unit11");
        unit11.setManager(player1);
        unit11.setType(UnitType.INFANTRY);
        unit11.setMutiny(false);
        unit11.setAttack(10);
        unit11.setRadar(false);
        unit11.setSpeed(3);
        unit11.setField(field1);
        unitRepository.save(unit11);

        Unit unit12 = new Unit();
        unit12.setName("unit12");
        unit12.setManager(player1);
        unit12.setType(UnitType.SPACESHIP);
        unit12.setMutiny(false);
        unit12.setAttack(10);
        unit12.setRadar(true);
        unit12.setSpeed(3);
        unit12.setField(field1);
        unitRepository.save(unit12);

        Unit unit21 = new Unit();
        unit21.setName("unit21");
        unit21.setManager(player2);
        unit21.setType(UnitType.SPACESHIP);
        unit21.setMutiny(false);
        unit21.setAttack(10);
        unit21.setRadar(true);
        unit21.setSpeed(3);
        unit21.setField(field2);
        unitRepository.save(unit21);
    }
}