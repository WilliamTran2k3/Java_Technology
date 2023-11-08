package vn.edu.tdtu.javatech.Lab7_6.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Setter @Getter
@Entity
public class Student {
    @Id
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Double ieltsScore;

    public Student(Long id, String name, Integer age, String email, Double ieltsScore) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.ieltsScore = ieltsScore;
    }

    public String toString() {
        String value = "Id = " + this.id + "\t" + "name = " + this.name + "\t" + "age = " + this.age
                + "\t" + "email = " + this.email + "\t" + "ielts = " + ieltsScore;
        return value;
    }
}
