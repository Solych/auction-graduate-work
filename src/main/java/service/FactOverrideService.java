package service;

import exceptions.ConstraintViolationException;
import model.FactOverride;
import model.Thing;
import model.dto.FactOverrideDto;

import java.util.List;

public interface FactOverrideService {
    FactOverride save(FactOverride factOverride) throws ConstraintViolationException;

    List<FactOverrideDto> getDataOfThing(Integer thingId);

    List<Thing> getOverridesByUser(Integer userId);
}
