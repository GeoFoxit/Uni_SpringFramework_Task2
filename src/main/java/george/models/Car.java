package george.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String naming;
    private Float price;

    public Car() {}

    public Car(String naming, Float price) {
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
