package vn.edu.tdtu.javatech.Lab8_2.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import vn.edu.tdtu.javatech.Lab8_2.model.Employee;
import vn.edu.tdtu.javatech.Lab8_2.service.EmployeeService;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping(value = "/employees")
    String getEmployees(Model model) {
        List<Employee> employees = (List<Employee>) employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
//        return "index";
        return "idx";
    }

    @GetMapping(value="/employees/add")
    String addEmployee() {
        return "add";
    }

    @PostMapping("/employees/add")
    RedirectView add(HttpServletRequest request) {
        Employee employee = new Employee(request.getParameter("name"),
                request.getParameter("email"),
                request.getParameter("address"),
                request.getParameter("phone"));
        employeeService.save(employee);
        return new RedirectView("/employees");
    }

    @GetMapping(value="/employees/edit/{id}")
    String editEmployee(HttpServletRequest request, Model model, @PathVariable(value="id") Long id) throws Exception {
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);
        return "edit";
    }

    @PostMapping(value="/employees/edit/{id}")
    RedirectView updateEmployee(HttpServletRequest request, @PathVariable(value="id") Long id) throws Exception {
        Employee employee = employeeService.getEmployee(id);
        employee.setAddress(request.getParameter("address"));
        employee.setName(request.getParameter("name"));
        employee.setEmail(request.getParameter("email"));
        employee.setPhone(request.getParameter("phone"));
        employeeService.save(employee);
        return new RedirectView("/employees");
    }

    @PostMapping(value="/employees/delete/{id}")
    RedirectView deleteEmployee(@PathVariable(value="id") Long id) throws Exception {
        employeeService.removeById(id);
        return new RedirectView("/employees");
    }

}
