package org.example.POJO;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Phone")
public class Phone {
    @Id
    private String Id;
    private String Name;
    private  int Price;
    private  String Color;
    private  String Country;
    private int Quanlity;

    public Phone() {}
    public Phone(String id, String name, int price, String color, String country, int quanlity, Manufacture manufacture) {
        Id = id;
        Name = name;
        Price = price;
        Color = color;
        Country = country;
        Quanlity = quanlity;
        this.manufacture = manufacture;
    }

    @ManyToOne
    @JoinColumn(name = "Id_Manufacture")
    private Manufacture manufacture;

    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getQuanlity() {
        return Quanlity;
    }

    public void setQuanlity(int quanlity) {
        Quanlity = quanlity;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "Id='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                ", Price=" + Price +
                ", Color='" + Color + '\'' +
                ", Country='" + Country + '\'' +
                ", Quanlity=" + Quanlity +
                ", manufacture=" + manufacture.toString() +
                '}';
    }
}
