package org.example.POJO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Manufacture")
public class Manufacture  {
    @Id
    private String Id;
    private String Name;
    private String Location;
    private int Employee;

    public Manufacture(){}
    public Manufacture(String id, String name, String location, int employee) {
        this.Id = id;
        this.Name = name;
        this.Location = location;
        this.Employee = employee;
    }
    @OneToMany(mappedBy = "manufacture")
    private List<Phone> Phone = null;

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

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getEmployee() {
        return Employee;
    }

    public void setEmployee(int employee) {
        Employee = employee;
    }

    public List<org.example.POJO.Phone> getPhone() {
        return Phone;
    }

    public void setPhone(List<org.example.POJO.Phone> phone) {
        Phone = phone;
    }
}
