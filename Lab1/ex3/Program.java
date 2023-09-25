import java.util.Arrays;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        
        // 1. Khoi tao danh sach sinh vien
        List<Student> list = StudentUtils.generate();
        StudentUtils.print(list);

        // 2. Sap xep theo ten roi in ket qua
        StudentUtils.sortByName(list);
        StudentUtils.print(list);

        // 3. Sap xep tang dan theo diem trung binh roi in ket qua
        StudentUtils.sortByAvg(list);
        StudentUtils.print(list);

        
        // Sap xep giam dan theo tuoi roi in ket qua
        StudentUtils.sortByAgeDescending(list);
        StudentUtils.print(list);
        // Tinh diem trung binh cua toan bo sinh vien roi in ket qua
        double avg = StudentUtils.avg(list);
        System.out.println(avg);
        // Lay danh sach hoc sinh Gioi roi in ket qua
        List<String> nameGoodStudent = StudentUtils.goodStudentName(list);
        System.out.println(Arrays.toString(nameGoodStudent.toArray()));
    }
}
