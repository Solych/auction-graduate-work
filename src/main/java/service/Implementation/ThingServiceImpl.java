package service.Implementation;

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

    @Override
    public Thing findById(Integer thingId) {
        return null;
    }

    @Override
    public List<Thing> getRandom24() {
        return null;
    }

    @Override
    public List<Thing> getByCategoryAndPage(Integer categoryId, Integer page) {
        return null;
    }

    public List<Thing> findOwnedByUser(Integer userId) {
        return thingRepository.findAllByOwnerId(userId);
    }

    public Thing save(Thing thing) {
        return thingRepository.saveAndFlush(thing);
    }
}
