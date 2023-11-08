package vn.edu.tdtu.javatech.Lab7_4.repository;

import org.springframework.data.repository.CrudRepository;
import vn.edu.tdtu.javatech.Lab7_4.model.Student;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    public List<Student> findByAgeGreaterThanEqual(Integer age);

    public List<Student> findByIeltsScore(Double ieltsScore);

    List<Student> findByNameContaining(String keyword);

}
