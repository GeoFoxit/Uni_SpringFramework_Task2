package george.controller;

import george.dao.OrderDao;
import george.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderDao orderDao;

    @GetMapping("")
    public List<Order> getOrders(@RequestParam(value = "id", required = false) Integer id) {
        if (id == null) {
            return orderDao.getAll();
        } else {
            List<Order> orders = new ArrayList<Order>();
            try {
                Order order = orderDao.getEntityById(id);
                orders.add(order);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return orders;
        }

    }

    @PostMapping("")
    public Order addOrder(@RequestParam(value = "id", required = true) Integer id,
                            @RequestParam(value = "carId", required = true) Integer carId,
                            @RequestParam(value = "clientId", required = true) Integer clientId){
        Order order = new Order();
        order.setId(id);
        order.setCarId(carId);
        order.setClientId(clientId);
        orderDao.insert(order);
        return order;
    }

    @DeleteMapping("")
    public Order removeOrder(@RequestParam(value = "id", required = true) Integer id) {
        try {
            Order order = orderDao.getEntityById(id);
            orderDao.delete(id);
            return order;
        } catch (Exception e) {
            e.printStackTrace();
            return new Order();
        }
    }

    @PatchMapping("")
    public Order updateOrder(@RequestParam(value = "id", required = true) Integer id,
                             @RequestParam(value = "carId", required = true) Integer carId,
                             @RequestParam(value = "clientId", required = true) Integer clientId){
        try {
            Order order = orderDao.getEntityById(id);
            order.setCarId(carId);
            order.setClientId(clientId);
            orderDao.update(order);
            return order;
        } catch (Exception e) {
            e.printStackTrace();
            return new Order();
        }
    }
}
