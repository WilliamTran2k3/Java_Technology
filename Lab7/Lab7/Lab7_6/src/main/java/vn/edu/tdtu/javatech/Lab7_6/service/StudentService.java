package vn.edu.tdtu.javatech.Lab7_6.service;

import vn.edu.tdtu.javatech.Lab7_6.model.Student;

import java.util.List;

public interface StudentService {
    public Iterable<Student> getAllStudents();
    public Iterable<Student> getCustomizedStudentList();

    public Student save(Student student);
}
