package logic_handle;
import Main.MainRun;
import entity.Student;
import java.io.*;
import java.util.Scanner;

public class StudentService {
    public static final String STUDENT_FILE_NAME = "student.dat";
    public static Student[] addStudent() {
        Student[] student = MainRun.student;
        int time = 0;
        if (MainRun.student == null) {
        } else {
            for (int i = 0; i < MainRun.student.length; i++) {
                if (MainRun.student[i] != null) {
                    time++;
                }
            }
        }
        System.out.print("Nhập số lượng sinh viên muốn nhập: ");
        int n = InputMissMatch.inputCheck();
        for (int i = 0 + time; i < n + time; i++) {
            System.out.println("Nhập sinh viên thứ " + (i - time + 1) + " : ");
            System.out.print("Nhập tên sinh viên: ");
            String name = new Scanner(System.in).next();
            System.out.print("Nhập địa chỉ của sinh viên: ");
            String address = new Scanner(System.in).next();
            System.out.print("Nhập số điện thoại của sinh viên: ");
            String phoneNumber = new Scanner(System.in).next();
            System.out.print("Nhập lớp của sinh viên: ");
            String studentClass = new Scanner(System.in).next();
            student[i] = new Student(name,address,phoneNumber,studentClass);
        }
        MainRun.student = student;
        return MainRun.student;
    }

    public static void createNewStudent() {  //ghi theo Object
        addStudent();
        try {   // dat try cacth de tranh ngoai le khi tao va ghi File
            FileOutputStream f = new FileOutputStream(STUDENT_FILE_NAME);   // tao file f tro den student.dat
            ObjectOutputStream oStream = new ObjectOutputStream(f); // dung de ghi theo Object vao file f
            oStream.writeObject(MainRun.student);  // ghi student theo kieu Object vao file
            oStream.close();
        } catch (IOException e) {
            System.out.println("Error Write file");
        }
    }

    public static Student[] read() {
        // doc theo Object
        Student[] student = MainRun.student;
        File file = new File(STUDENT_FILE_NAME);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
                FileInputStream f = new FileInputStream(STUDENT_FILE_NAME);
                if (f.available() != 0) {
                    ObjectInputStream inStream = new ObjectInputStream(f);
                    student = (Student[]) inStream.readObject();
                    inStream.close();
                }
            } catch (ClassNotFoundException e) {
                System.out.println("Không tìm thấy lớp. ");
            } catch (IOException e) {
                System.out.println("Lỗi đọc file. ");
            }
        MainRun.student = student;
        return MainRun.student;
    }

    public static void showStudent(){      //In data doc duoc tu file ra man hinh
        //Student[] student
        read();
        if(MainRun.student[0] == null) {
            System.out.println("File đang rỗng. ");
        }
        try {
            for (int i = 0; i < MainRun.student.length; i++) {
                if(MainRun.student[i] !=null) {
                    System.out.println(MainRun.student[i]);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("File đang rỗng.");
        }
    }
}

