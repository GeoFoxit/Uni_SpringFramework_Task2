package models;

import java.io.Serializable;

public class Client {
    public Client(int id,String full_name,String license_category,int age) {
        this.id = id;
        this.full_name = full_name;
        this.license_category = license_category;
        this.age = age;
    }

    public int id;
    public String full_name;
    public String license_category;
    public int age;
}