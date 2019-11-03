package george.controller;

import george.dao.CarDao;
import george.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    CarDao carDao;

    @GetMapping("")
    public List<Car> getCars(@RequestParam(value = "id", required = false) Integer id) {
        if (id == null) {
            return carDao.getAll();
        } else {
            List<Car> cars = new ArrayList<Car>();
            try {
                Car car = carDao.getEntityById(id);
                cars.add(car);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return cars;
        }

    }

    @PostMapping("")
    public Car addCar(@RequestParam(value = "id", required = true) int id,
                      @RequestParam(value = "naming", required = true) String naming,
                      @RequestParam(value = "price", required = true) float price){
        Car car = new Car();
        car.setId(id);
        car.setNaming(naming);
        car.setPrice(price);
        carDao.insert(car);
        return car;
    }

    @DeleteMapping("")
    public Car removeCar(@RequestParam(value = "id", required = true) Integer id) {
        try {
            Car car = carDao.getEntityById(id);
            carDao.delete(id);
            return car;
        } catch (Exception e) {
            e.printStackTrace();
            return new Car();
        }
    }

    @PatchMapping("")
    public Car updateCar(@RequestParam(value = "id", required = true) int id,
                         @RequestParam(value = "naming", required = true) String naming,
                         @RequestParam(value = "price", required = true) float price){
        try {
            Car car = carDao.getEntityById(id);
            car.setNaming(naming);
            car.setPrice(price);
            carDao.update(car);
            return car;
        } catch (Exception e) {
            e.printStackTrace();
            return new Car();
        }
    }
}
