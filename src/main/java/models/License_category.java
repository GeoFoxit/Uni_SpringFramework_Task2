package models;

public class License_category {

    private Integer id;
    private String naming;
    private String description;

    public License_category() { }

    public License_category(Integer id, String naming, String description) {
        this.id = id;
        this.naming = naming;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaming() {
        return naming;
    }
    public void setNaming(String naming) {
        this.naming = naming;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
