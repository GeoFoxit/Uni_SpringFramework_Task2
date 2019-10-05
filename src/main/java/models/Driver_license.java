package models;

import java.util.Date;

public class Driver_license {

    private Integer id;
    private Date extraditionDate;
    private Date expirationDate;
    private License_category licenseCategory;
    private License_convention license_convention;

    public Driver_license() { }

    public Driver_license(Integer id,
                          Date extraditionDate,
                          Date expirationDate,
                          License_category licenseCategory,
                          License_convention license_convention) {
        this.id = id;
        this.extraditionDate = extraditionDate;
        this.expirationDate = expirationDate;
        this.licenseCategory = licenseCategory;
        this.license_convention = license_convention;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getExtraditionDate() {
        return extraditionDate;
    }
    public void setExtraditionDate(Date extraditionDate) {
        this.extraditionDate = extraditionDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public License_category getLicenseCategory() {
        return licenseCategory;
    }
    public void setLicenseCategory(License_category licenseCategory) {
        this.licenseCategory = licenseCategory;
    }

    public License_convention getLicense_convention() {
        return license_convention;
    }
    public void setLicense_convention(License_convention license_convention) {
        this.license_convention = license_convention;
    }
}
