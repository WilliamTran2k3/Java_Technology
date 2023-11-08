package vn.edu.tdtu.javatech.Lab7_3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.javatech.Lab7_3.model.Student;
import vn.edu.tdtu.javatech.Lab7_3.repository.StudentRepository;

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
}
