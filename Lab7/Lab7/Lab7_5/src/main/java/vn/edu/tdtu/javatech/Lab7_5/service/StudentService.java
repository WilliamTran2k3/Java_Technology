package vn.edu.tdtu.javatech.Lab7_5.service;

import vn.edu.tdtu.javatech.Lab7_5.model.Student;

import java.util.List;

public interface StudentService {
    public Iterable<Student> getAllStudents();

    public Student getStudent(long id) throws Exception;

    public void deleteStudent(long id);

    public Student save(Student student);

    public List<Student> searchByAge(int age);

    public Integer countStudentsWithIeltsScore(double score);

    public List<Student> searchStudentsByName(String keyword);
}
