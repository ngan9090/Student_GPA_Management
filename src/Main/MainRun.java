package Main;

import entity.CoreManagement;
import entity.Student;
import entity.Subject;
import logic_handle.CoreManagementService;
import logic_handle.CountCoreService;
import logic_handle.StudentService;
import logic_handle.SubjectService;
import java.io.*;
import java.util.*;

public class MainRun {
    public static Student[] student = new Student[100] ;
    public static Subject[] subject = new Subject[100] ;
    public static CoreManagement[] coreManagement  = new CoreManagement[100] ;

    public static void main(String[] args) {
        // Lấy dữ liệu sẵn có từ file đã lưu
        initData();
        menu();
    }

    private static void initData() {
        // Đọc dữ liệu từ tập tin
        StudentService.read();
        SubjectService.read();
        CoreManagementService.read();
    }

    private static void menu() {
        do {
            int functionChoice = showMenu();
            switch (functionChoice) {
                case 1:
                    StudentService.createNewStudent();
                    break;
                case 2:
                    StudentService.showStudent();
                    break;
                case 3:
                    SubjectService.createNewSubject();
                    break;
                case 4:
                    SubjectService.showSubject();
                    break;
                case 5:
                    CoreManagementService.createNewCoreManagement();
                    break;
                case 6:
                    CoreManagementService.showStudentCore();
                    break;
                case 7:
                     CoreManagementService.sortCoreManagerment();
                    break;
                case 8:
                    CountCoreService.countCore();
                    break;
                case 9:
                    return;
            }
        } while (true);
    }

    private static int showMenu() {
        printMenu();
        int functionChoice = 0;
        do {
            try {
                functionChoice = new Scanner(System.in).nextInt();
            } catch (InputMismatchException ex) {
                System.out.print("Chức năng cần chọn là 1 số nguyên, yêu cầu nhập lại: ");
                continue;
            }
            if (functionChoice > 0 && functionChoice <= 9) {
                break;
            }
            System.out.print("Chức năng vừa chọn không hợp lệ, vui lòng nhập lại: ");
        } while (true);
        return functionChoice;
    }

    private static void printMenu() {
        System.out.println("--------PHẦN MỀM QUẢN LÝ ĐIỂM SINH VIÊN------");
        System.out.println("1. Nhập danh sách sinh viên mới.");
        System.out.println("2. In ra danh sách sinh viên mới.");
        System.out.println("3. Nhập danh sách môn học mới.");
        System.out.println("4. In ra danh sách môn học mới.");
        System.out.println("5. Nhập điểm cho sinh viên.");
        System.out.println("6. In ra danh sách bảng điểm ra màn hình.");
        System.out.println("7. Sắp xếp danh sách bảng điểm.");
        System.out.println("8. Tính điểm tổng kết chung cho mỗi sinh viên.");
        System.out.println("9. Thoát");
        System.out.print(" Xin mời chọn chức năng: ");
    }
}
