package george.controller;

import george.repositories.OrderRepository;
import george.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderRepository orderRepo;

    @GetMapping("")
    public Iterable<Order> getOrders(@RequestParam(value = "id", required = false) Integer id) {
        if (id == null) { return orderRepo.findAll(); }
        List<Order> orders = new ArrayList<Order>();
        Optional<Order> optionalOrder= orderRepo.findById(id);
        if (optionalOrder.isPresent()) { orders.add(optionalOrder.get()); }
        return orders;
    }

    @PostMapping("")
    public Order addOrder(@RequestParam(value = "carId", required = true) Integer carId,
                            @RequestParam(value = "clientId", required = true) Integer clientId){
        Order order = new Order(carId,clientId);
        orderRepo.save(order);
        return order;
    }

    @DeleteMapping("")
    public String removeOrder(@RequestParam(value = "id", required = true) Integer id) {
        orderRepo.deleteById(id);
        return "Deleted";
    }

    @PatchMapping("")
    public String updateOrder(@RequestParam(value = "id", required = true) Integer id,
                             @RequestParam(value = "carId", required = false) Integer carId,
                             @RequestParam(value = "clientId", required = false) Integer clientId) {
        Optional<Order> optionalOrder= orderRepo.findById(id);
        if (optionalOrder.isPresent() && (carId != null || clientId != null)) {
            Order order = optionalOrder.get();
            if (carId != null) { order.setCarId(carId); }
            if (clientId != null) { order.setClientId(clientId); }
            orderRepo.save(order);
            return "Updated";
        }
        return "Unable to update";
    }
}
