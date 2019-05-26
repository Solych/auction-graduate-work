package service.Implementation;

import exceptions.ConstraintViolationException;
import model.FactSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.FactSaleRepository;
import service.FactSaleService;

@Service
public class FactSaleServiceImpl implements FactSaleService {

    private static final String ERROR_MSG_SAVE = "Cannot save a fact about sale thing";

    @Autowired
    private FactSaleRepository factSaleRepository;

    public FactSale save(FactSale factSale) throws ConstraintViolationException {
        try {
            return factSaleRepository.saveAndFlush(factSale);
        } catch (Exception ex){
            throw new ConstraintViolationException(ERROR_MSG_SAVE);
        }
    }
}
