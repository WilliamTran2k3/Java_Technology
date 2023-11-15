package vn.edu.tdtu.javatech.Lab8_2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.javatech.Lab8_2.model.Employee;
import vn.edu.tdtu.javatech.Lab8_2.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(long id) throws Exception {
        return employeeRepository.findById(id).orElseThrow(() -> new Exception("Student not found"));
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void removeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
