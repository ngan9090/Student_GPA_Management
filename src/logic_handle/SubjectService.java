package logic_handle;
import Main.MainRun;
import constant.SubjectStyle;
import entity.Subject;
import java.io.*;
import java.util.Scanner;

public class SubjectService {
    public static final String SUBJECT_FILE_NAME = "subject.dat";
    public static Subject[] addSubject() {      // Input the data from Keyboard
        Subject[] subject = MainRun.subject;
        int time = 0;
        if (MainRun.subject == null) {
        } else {
            for (int i = 0; i < MainRun.subject.length; i++) {
                if (MainRun.subject[i] != null) {
                    time++;
                }
            }
        }
        System.out.print("Nhập số lượng môn học: ");
        int n = InputMissMatch.inputCheck();
        for (int i = 0 + time; i < n + time; i++) {
            System.out.println("Nhập môn thứ " + (i - time + 1) + " : ");
            System.out.print("Nhập tên môn học: ");
            String name = new Scanner(System.in).next();
            System.out.print("Nhập số đơn vị học trình: ");
            int subjectStudingUnit = InputMissMatch.inputCheck();
            SubjectStyle subjectStyle = subjectStyleData();
            subject[i] = new Subject(name,subjectStudingUnit,subjectStyle);
        }
        MainRun.subject = subject;
        return MainRun.subject;
    }
    public static SubjectStyle subjectStyleData(){
        SubjectStyle a = null;
        System.out.println("Nhập loại môn học là một trong những loại dưới đây : ");
        System.out.println("1. " + SubjectStyle.D_C);
        System.out.println("2. " + SubjectStyle.CO_SO);
        System.out.println("3. " + SubjectStyle.C_N);
        System.out.print("Nhập lựa chọn của bạn: ");
        int choice = 0;
        do {
            choice = InputMissMatch.inputCheck();
            if(choice < 1 || choice > 3){
                System.out.print("Xin vui lòng nhập lại loại bạn đọc từ 1 đến 3: ");
            }
        } while (choice < 1 || choice > 3);
        switch (choice){
            case 1:
                a = SubjectStyle.D_C;
                break;
            case 2:
                a = SubjectStyle.CO_SO;
                break;
            case 3:
                a = SubjectStyle.C_N;
                break;
        }
        return a;
    }

    public static void createNewSubject() {  //ghi theo Object
        addSubject();
        try {   // dat try cacth de tranh ngoai le khi tao va ghi File
            FileOutputStream f = new FileOutputStream(SUBJECT_FILE_NAME);   // tao file f tro den student.dat
            ObjectOutputStream oStream = new ObjectOutputStream(f); // dung de ghi theo Object vao file f
            oStream.writeObject(MainRun.subject);  // ghi student theo kieu Object vao file
            oStream.close();
        } catch (IOException e) {
            System.out.println("Lỗi viết file");
        }
    }

    public static Subject[] read() {
        // doc theo Object
        Subject[] subject = MainRun.subject;
        File files = new File(SUBJECT_FILE_NAME);
        try {
            files.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileInputStream f = new FileInputStream(SUBJECT_FILE_NAME);
            if (f.available() != 0) {
                ObjectInputStream inStream = new ObjectInputStream(f);
                subject = (Subject[]) inStream.readObject();
                //System.out.println(subject);
                inStream.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy lớp. ");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Lỗi đọc file. ");
        }
        MainRun.subject = subject;
        return MainRun.subject;
    }

    public static void showSubject() {      //In data doc duoc tu file ra man hinh
        read();
        if(MainRun.subject[0] == null) {
            System.out.println("File đang rỗng. ");
        }
        try {
            for (int i = 0; i < MainRun.subject.length; i++) {
                if(MainRun.subject[i] !=null) {
                    System.out.println(MainRun.subject[i]);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("File đang rỗng.");
        }
    }
}


