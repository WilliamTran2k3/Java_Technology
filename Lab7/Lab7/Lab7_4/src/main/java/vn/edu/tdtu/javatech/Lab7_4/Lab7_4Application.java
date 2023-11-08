package vn.edu.tdtu.javatech.Lab7_4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.edu.tdtu.javatech.Lab7_4.model.Student;
import vn.edu.tdtu.javatech.Lab7_4.service.StudentService;

import java.util.List;

@SpringBootApplication
public class Lab7_4Application implements CommandLineRunner {

	@Autowired
	private StudentService studentService;
	public static void main(String[] args) {
		SpringApplication.run(Lab7_4Application.class, args);
	}

	private void showStudents() {
		List<Student> studentList = (List<Student>) this.studentService.getAllStudents();
		for (Student student : studentList) {
			System.out.println(student);
		}
	}

	private void showStudentList(List<Student> studentList) {
		for (Student student : studentList) {
			System.out.println(student);
		}
	}

	@Override
	public void run(String... args) throws Exception {
		Student student1 = new Student(1L, "Alex", 18, "alex@tdtu.edu.vn", 6.5);
		Student student2 = new Student(2L, "Bob", 19, "bob@tdtu.edu.vn", 7.0);
		Student student3 = new Student(3L, "Mary", 20, "mary@tdtu.edu.vn", 7.5);
		this.studentService.save(student1);
		this.studentService.save(student2);
		this.studentService.save(student3);
		showStudents();
		List<Student> studentList = studentService.findStudentsWithAgeGreaterThanEqual(19);
		System.out.println("Students with age greater than or equal to 19:");
		showStudentList(studentList);
		System.out.println("The number of students with Ielts score of 7.0 is " + studentService.countStudentsWithIeltsScore(7.0));
		studentList = studentService.searchStudentsByName("ar");
		System.out.println("The students are found. Their information is: ");
		showStudentList(studentList);
	}
}
