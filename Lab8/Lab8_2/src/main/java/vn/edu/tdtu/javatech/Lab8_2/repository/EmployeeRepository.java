package vn.edu.tdtu.javatech.Lab8_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.tdtu.javatech.Lab8_2.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
