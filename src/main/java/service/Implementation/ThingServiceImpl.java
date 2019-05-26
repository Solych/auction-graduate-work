package service.Implementation;

import exceptions.ThingNotFoundException;
import model.Thing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ThingRepository;
import service.ThingService;

import java.util.Date;
import java.util.List;

@Service
public class ThingServiceImpl implements ThingService {

    @Autowired
    private ThingRepository thingRepository;

    public static final String THING_NOT_FOUND = "Thing not found";

    public Thing findById(Integer thingId) throws ThingNotFoundException {
        Thing thing = thingRepository.findByThingId(thingId);
        if(thing == null) {
            throw new ThingNotFoundException(THING_NOT_FOUND);
        }
        return thing;
    }

    public List<Thing> getRandom24() {
        return thingRepository.findRandom24(new Date());
    }

    public List<Thing> getByCategoryAndPage(Integer categoryId, Integer page) {
        return thingRepository.findByCategoryPageable(new Date(), categoryId, page);
    }

    public List<Thing> findOwnedByUser(Integer userId) {
        return thingRepository.findAllByOwnerId(userId);
    }

    public Thing save(Thing thing) {
        return thingRepository.saveAndFlush(thing);
    }

    public List<Thing> findThingByName(String name) throws ThingNotFoundException {
        List<Thing> things = thingRepository.getThingsByNameLike(name, new Date());
        if(things.size() == 0) {
            throw new ThingNotFoundException(THING_NOT_FOUND);
        }
        return things;
    }
}
