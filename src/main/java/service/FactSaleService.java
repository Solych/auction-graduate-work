package service;

import exceptions.ConstraintViolationException;
import model.FactSale;

public interface FactSaleService {
    FactSale save(FactSale factSale) throws ConstraintViolationException;
}
