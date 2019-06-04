package service.Implementation;

import exceptions.ConstraintViolationException;
import model.Buyer;
import model.FactOverride;
import model.FactSale;
import model.Thing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import repository.BuyerRepository;
import repository.FactOverrideRepository;
import repository.FactSaleRepository;
import repository.ThingRepository;
import service.FactSaleService;

import java.util.Date;
import java.util.List;

@Service
public class FactSaleServiceImpl implements FactSaleService {

    private static final String ERROR_MSG_SAVE = "Cannot save a fact about sale thing";
    private static final String THING_WITHOUT_OVERRIDES = "Аукцион не состоялся";

    @Autowired
    private FactSaleRepository factSaleRepository;

    @Autowired
    private ThingRepository thingRepository;

    @Autowired
    private FactOverrideRepository factOverrideRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    public FactSale save(FactSale factSale) throws ConstraintViolationException {
        try {
            return factSaleRepository.saveAndFlush(factSale);
        } catch (Exception ex){
            throw new ConstraintViolationException(ERROR_MSG_SAVE);
        }
    }

    @Scheduled(fixedDelay = 60000)
    public void scheduleFixedDelayTask() {
        List<Thing> things = thingRepository.getExpired(new Date());
        things.forEach(thing -> {
            FactOverride lastOverride = factOverrideRepository.findLastPrice(thing.getThingId());
            if(lastOverride == null) {
                thing.setMessage(THING_WITHOUT_OVERRIDES);
                thingRepository.saveAndFlush(thing);
            } else {
               Buyer buyer = buyerRepository.findByBuyerId(lastOverride.getBuyer().getBuyerId());
               thing.setMessage("Данный лот выиграль пользователь с именем " + buyer.getNickName() + " со ставкой в " +
                       lastOverride.getPrice() + " рублей. Пожалуйста, свяжитесь с ним по почте " + buyer.getMail());
               thingRepository.saveAndFlush(thing);
               factSaleRepository.saveAndFlush(new FactSale(lastOverride.getBuyer(), lastOverride.getThing(),
                       lastOverride.getOverrideTime(), lastOverride.getPrice()));
            }
        });
    }
}
