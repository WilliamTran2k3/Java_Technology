package vn.edu.tdtu.javatech.Lab7_2.repository;

import org.springframework.data.repository.CrudRepository;
import vn.edu.tdtu.javatech.Lab7_2.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
