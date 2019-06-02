package service;

import exceptions.ThingNotFoundException;
import model.Thing;

import java.util.List;

public interface ThingService {
    Thing findById(Integer thingId) throws ThingNotFoundException;
    List<Thing> getRandom24();
    List<Thing> getByCategoryAndPage(Integer categoryId, Integer page);
    List<Thing> findOwnedByUser(Integer userId);
    Thing save(Thing thing);
    List<Thing> findThingByName(String name) throws ThingNotFoundException;
    Integer getRandomIdOfThing();
}
