package controller;

import exceptions.ConstraintViolationException;
import exceptions.UserNotFoundException;
import model.Buyer;
import model.dto.Admission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.BuyerService;

import java.util.Date;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class BuyerController {

    @Scheduled(fixedDelay = 60000)
    public void scheduleFixedDelayTask() {
        System.out.println(new Date(System.currentTimeMillis()));
    }

    @Autowired
    private BuyerService buyerService;

    @PostMapping("/authorize")
    public ResponseEntity<String> checkAdmission(@RequestBody Admission admission) {
        try {
            String token = buyerService.checkAdmission(admission);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (UserNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Buyer buyer) {
        try{
            return new ResponseEntity<>(buyerService.register(buyer), HttpStatus.OK);
        } catch (ConstraintViolationException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
