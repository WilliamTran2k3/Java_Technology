package vn.edu.tdtu.javatech.Lab7_5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.javatech.Lab7_5.model.Student;
import vn.edu.tdtu.javatech.Lab7_5.repository.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(long id) throws Exception {
        return studentRepository.findById(id)
                .orElseThrow(() -> new Exception("Student not found"));
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> searchByAge(int age) {
        return (List<Student>) studentRepository.searchByAge(age);
    }

    @Override
    public Integer countStudentsWithIeltsScore(double score) {
        return studentRepository.searchByIeltsScore(score).size();
    }

    @Override
    public List<Student> searchStudentsByName(String keyword) {
        return studentRepository.searchByName(keyword);
    }
}
