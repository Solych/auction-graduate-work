package service;

import model.Thing;

import java.util.List;

public interface ThingService {
    Thing findById(Integer thingId);
    List<Thing> getRandom24();
    List<Thing> getByCategoryAndPage(Integer categoryId, Integer page);
    List<Thing> findOwnedByUser(Integer userId);
    Thing save(Thing thing);
    List<Thing> findThingByName(String name);
}
