package controller;

import exceptions.ThingNotFoundException;
import model.FactOverride;
import model.Thing;
import model.dto.FactOverrideDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import repository.ThingRepository;
import service.CategoryService;
import service.FactOverrideService;
import service.ThingService;

import java.util.List;

@Controller
@RequestMapping("/thing")
@CrossOrigin(origins = "*")
public class ThingController {

    @Autowired
    private ThingService thingService;

    @Autowired
    private FactOverrideService factOverrideService;

    @GetMapping("/getAllByUser/{userId}")
    public ResponseEntity<List<Thing>> getAllThingsOwnedByUser(@PathVariable Integer userId) {
        return new ResponseEntity<>(thingService.findOwnedByUser(userId), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Thing> update(@RequestBody Thing thing) {
        return new ResponseEntity<>(thingService.save(thing), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Thing> save(@RequestBody Thing thing){
        return new ResponseEntity<>(thingService.save(thing), HttpStatus.OK);
    }

    @GetMapping("/getAllByCategory/{categoryId}/")
    public ResponseEntity<List<Thing>> getAllThingsByCategory(@PathVariable Integer categoryId,
                                                              @RequestParam("page") Integer page) {
        return new ResponseEntity<>(thingService.getByCategoryAndPage(categoryId, page), HttpStatus.OK);
    }

    @GetMapping("/getById/{thingId}")
    public ResponseEntity<Thing> getThingById(@PathVariable Integer thingId) {
        try {
            return new ResponseEntity<>(thingService.findById(thingId), HttpStatus.OK);
        } catch (ThingNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getDataByThingId/{thingId}")
    public ResponseEntity<List<FactOverrideDto>> getThingDataById(@PathVariable Integer thingId) {
        return new ResponseEntity<>(factOverrideService.getDataOfThing(thingId), HttpStatus.OK);
    }

    @GetMapping("/getThingByName/{name}")
    public ResponseEntity<List<Thing>> getThingByName(@PathVariable String name) {
        try {
            return new ResponseEntity<>(thingService.findThingByName(name), HttpStatus.OK);
        } catch (ThingNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
