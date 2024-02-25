package kea.exercise.hogwarts_api.controllers;

import kea.exercise.hogwarts_api.models.Course;
import kea.exercise.hogwarts_api.models.House;
import kea.exercise.hogwarts_api.repositories.HouseRepository;
import kea.exercise.hogwarts_api.services.HouseService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("houses")
public class HouseController {


    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping
    public List<House> getAll() {
        return houseService.findAll();
    }

    @GetMapping("/{name}")
    public ResponseEntity<House> getHouseByName(@PathVariable String name) {
        return ResponseEntity.of(houseService.findById(name));
    }

}
