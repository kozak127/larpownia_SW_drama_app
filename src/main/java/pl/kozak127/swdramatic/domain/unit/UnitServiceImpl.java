package pl.kozak127.swdramatic.domain.unit;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;

    UnitServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public Collection<Unit> findAll() {
        return Lists.newArrayList(unitRepository.findAll());
    }
}
