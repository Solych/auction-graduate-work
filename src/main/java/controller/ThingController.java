package controller;

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
public class ThingController {

    @Autowired
    private ThingService thingService;

    @Autowired
    private FactOverrideService factOverrideService;

    @GetMapping("/getAllByUser/{userId}")
    public ResponseEntity<List<Thing>> getAllThingsOwnedByUser(@PathVariable Integer userId) {
        System.out.println(thingService.findOwnedByUser(userId));
        System.out.println(userId);
        return new ResponseEntity<>(thingService.findOwnedByUser(userId), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Thing> update(@RequestBody Thing thing) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Thing> save(@RequestBody Thing thing){
        return new ResponseEntity<>(thingService.save(thing), HttpStatus.OK);
    }

    @GetMapping("/getAllByCategory/{categoryId}/{page}")
    public ResponseEntity<List<Thing>> getAllThingsByCategory(@PathVariable Integer categoryId,
                                                              @PathVariable Integer page) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getById/{thingId}")
    public ResponseEntity<Thing> getThingById(@PathVariable Integer thingId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getDataByThingId/{thingId}")
    public ResponseEntity<List<FactOverrideDto>> getThingDataById(@PathVariable Integer thingId) {
        return new ResponseEntity<>(factOverrideService.getDataOfThing(thingId), HttpStatus.OK);
    }


}
