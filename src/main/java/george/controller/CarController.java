package george.controller;

import george.repositories.CarRepository;
import george.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    CarRepository carRepo;

    @GetMapping("")
    public Iterable<Car> getCars(@RequestParam(value = "id", required = false) Integer id) {
        if (id == null) { return carRepo.findAll(); }
        List<Car> cars = new ArrayList<Car>();
        Optional<Car> optionalCar= carRepo.findById(id);
        if (optionalCar.isPresent()) { cars.add(optionalCar.get()); }
        return cars;
    }

    @PostMapping("")
    public Car addCar(@RequestParam(value = "naming", required = true) String naming,
                      @RequestParam(value = "price", required = true) float price){
        Car car = new Car(naming,price);
        carRepo.save(car);
        return car;
    }

    @DeleteMapping("")
    public String removeCar(@RequestParam(value = "id", required = true) Integer id) {
        carRepo.deleteById(id);
        return "Deleted";
    }

    @PatchMapping("")
    public String updateCar(@RequestParam(value = "id", required = true) int id,
                         @RequestParam(value = "naming", required = false) String naming,
                         @RequestParam(value = "price", required = false) Float price) {
        Optional<Car> optionalCar= carRepo.findById(id);
        if (optionalCar.isPresent() && (naming != null || price != null)) {
            Car car = optionalCar.get();
            if (naming != null) { car.setNaming(naming); }
            if (price != null) { car.setPrice(price); }
            carRepo.save(car);
            return "Updated";
        }
        return "Unable to update";
    }
}
