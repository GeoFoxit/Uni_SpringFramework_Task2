package george.models;

public class Order {

    private Integer id;
    private Integer carId;
    private Integer clientId;

    public Order() {}

    public Order(Integer id, Integer carId, Integer clientId) {
        this.id = id;
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
