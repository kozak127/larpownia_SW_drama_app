package pl.kozak127.swdramatic.domain.field;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kozak127.swdramatic.domain.player.Player;

import java.util.Collection;
import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;
    private final FieldOrderRepository fieldOrderRepository;

    @Autowired
    FieldServiceImpl(FieldRepository fieldRepository, FieldOrderRepository fieldOrderRepository) {
        this.fieldRepository = fieldRepository;
        this.fieldOrderRepository = fieldOrderRepository;
    }

    @Override
    public List<Field> findAll() {
        return Lists.newArrayList(fieldRepository.findAll());
    }

    @Override
    public FieldOrder saveOrder(FieldOrder fieldOrder) {
        return fieldOrderRepository.save(fieldOrder);
    }

    @Override
    public Collection<FieldOrder> getAllOrders() {
        return Lists.newArrayList(fieldOrderRepository.findAll());
    }

    @Override
    public Collection<FieldOrder> getOrdersForPlayer(Player player) {
        return fieldOrderRepository.findAllByManager(player);
    }
}
