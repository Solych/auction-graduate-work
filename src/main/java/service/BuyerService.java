package service;

import exceptions.ConstraintViolationException;
import exceptions.UserNotFoundException;
import model.Buyer;
import model.dto.Admission;

public interface BuyerService {
    String checkAdmission(Admission admission) throws UserNotFoundException;
    Buyer register(Buyer buyer) throws ConstraintViolationException;
}
