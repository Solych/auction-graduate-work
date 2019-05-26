package service.Implementation;

import exceptions.ConstraintViolationException;
import exceptions.UserNotFoundException;
import model.Buyer;
import model.dto.Admission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.BuyerRepository;
import service.BuyerService;
import token.Token;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;
    public static final Integer NUMBER_OF_COMPLAINTS = 0;

    public static final String USER_NOT_FOUND_MESSAGE = "User not found";
    public static final String USER_ALSO_EXISTS_MSG = "User with this credentials also exists";

    public String checkAdmission(Admission admission) throws UserNotFoundException {
        Buyer buyer = buyerRepository.findByNickNameAndPassword(admission.getUsername(), admission.getPassword());
        if (buyer == null) {
            throw new UserNotFoundException(USER_NOT_FOUND_MESSAGE);
        }
        return Token.generateTokenString(admission.getUsername());
    }

    public Buyer register(Buyer buyer) throws ConstraintViolationException {
        try {
            return buyerRepository.saveAndFlush(buyer);
        } catch (Exception ex) {
            throw new ConstraintViolationException(USER_ALSO_EXISTS_MSG);
        }
    }

    @Transactional
    public Buyer delete(Integer buyerId) throws UserNotFoundException{

        return buyerRepository.findById(buyerId).orElseThrow(() -> new UserNotFoundException("cann"));
    }
}
