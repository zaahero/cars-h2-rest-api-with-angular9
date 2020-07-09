package za.co.trustlink.cars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.trustlink.cars.models.Car;
import za.co.trustlink.cars.services.CarService;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
//@CrossOrigin(origins = "http://localhost:4200")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/load-cars")
    public Object loadCars() {
        HashMap response = new HashMap();
        try {
            carService.loadCarsFromXMLFile();
            response.putIfAbsent("status", Boolean.TRUE);
            return response;
        } catch (Exception e) {
            response.putIfAbsent("status", Boolean.FALSE);
            return response;
        }
    }

    @GetMapping("/cars")
    public Object getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public Object getCarById(@PathVariable String id) {
        return carService.getCarById(Long.valueOf(id));
    }

    @PostMapping("/cars")
    public Object saveCar(@RequestBody Car car) {

        return carService.saveCar(car);
    }

    @PutMapping("/cars")
    public Object updateCar(@RequestBody Car car) {

        return carService.updateCar(car);
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable String id) {
        carService.deleteCar(Long.valueOf(id));
    }


}
