package george.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer carId;
    private Integer clientId;

    public Order() {}

    public Order(Integer carId, Integer clientId) {
        this.carId = carId;
        this.clientId = clientId;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }
    public Integer getCarId() {
        return carId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
    public Integer getClientId() {
        return clientId;
    }
}
