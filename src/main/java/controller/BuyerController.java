package controller;

import com.google.gson.JsonObject;
import exceptions.ConstraintViolationException;
import exceptions.UserNotFoundException;
import model.Buyer;
import model.FactOverride;
import model.dto.Admission;
import model.dto.CategoryCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.BuyerService;
import service.CategoryService;
import service.FactOverrideService;

import java.util.List;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FactOverrideService factOverrideService;

    @PostMapping("/authorize")
    public ResponseEntity<?> checkAdmission(@RequestBody Admission admission) {
        try {
            String token = buyerService.checkAdmission(admission);
            JsonObject jsonToken = new JsonObject();
            jsonToken.addProperty("token", token);
            return new ResponseEntity<>(jsonToken.toString(), HttpStatus.OK);
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

    @GetMapping("/category/{id}")
    public ResponseEntity<List<CategoryCount>> getDataOfCategories(@PathVariable Integer id) {
        return new ResponseEntity<>(categoryService.getDataOfCategories(id), HttpStatus.OK);
    }

    @GetMapping("/prices/{id}")
    public ResponseEntity<List<FactOverride>> getDataOfPrices(@PathVariable Integer id) {
        return new ResponseEntity<>(factOverrideService.getOverridesByUser(id), HttpStatus.OK);
    }
}
