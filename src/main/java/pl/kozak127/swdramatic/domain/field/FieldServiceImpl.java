package pl.kozak127.swdramatic.domain.field;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
