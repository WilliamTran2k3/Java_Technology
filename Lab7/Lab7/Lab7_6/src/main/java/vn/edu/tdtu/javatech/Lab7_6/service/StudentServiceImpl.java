package vn.edu.tdtu.javatech.Lab7_6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.javatech.Lab7_6.model.Student;
import vn.edu.tdtu.javatech.Lab7_6.repository.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll(Sort.by("age").descending().and(Sort.by("ieltsScore")));
    }

    @Override
    public Iterable<Student> getCustomizedStudentList() {
        Pageable sortedByAgeDescIeltsAsc =
                PageRequest.of(0, 10, Sort.by("age").descending().and(Sort.by("ieltsScore")));
        Page<Student> studentPage = studentRepository.findAll(sortedByAgeDescIeltsAsc);
        return studentPage.getContent().subList(4,7);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

}
