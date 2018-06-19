package pl.kozak127.swdramatic.domain.field;

import pl.kozak127.swdramatic.domain.player.Player;

import java.util.Collection;

public interface FieldService {
    Collection<Field> findAll();

    FieldOrder saveOrder(FieldOrder fieldOrder);

    Collection<FieldOrder> getAllOrders();

    Collection<FieldOrder> getOrdersForPlayer(Player player);
}
