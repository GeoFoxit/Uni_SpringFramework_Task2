package george.models;

public class Car {

    private Integer id;
    private String naming;
    private Float price;

    public Car() {}

    public Car(Integer id, String naming, Float price) {
        this.id = id;
        this.naming = naming;
        this.price = price;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public void setNaming(String naming) {
        this.naming = naming;
    }
    public String getNaming() {
        return naming;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
    public Float getPrice() {
        return price;
    }
}
