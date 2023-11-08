package vn.edu.tdtu.javatech.Lab7_3.service;

import vn.edu.tdtu.javatech.Lab7_3.model.Student;

public interface StudentService {
    public Iterable<Student> getAllStudents();

    public Student getStudent(long id) throws Exception;

    public void deleteStudent(long id);

    public Student save(Student student);
}
