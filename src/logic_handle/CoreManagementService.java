package logic_handle;

import Main.MainRun;
import entity.CoreManagement;
import entity.Student;
import entity.Subject;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class CoreManagementService {
    public static final String CORE_FILE_NAME = "core.dat";

    public static CoreManagement[] addStudentCore() {      // Input the data from Keyboard
        CoreManagement[] coreManagement = MainRun.coreManagement;
        int time = 0;
        if (MainRun.coreManagement == null) {
        } else {
            for (int i = 0; i < MainRun.coreManagement.length; i++) {
                if (MainRun.coreManagement[i] != null) {
                    time++;
                }
            }
        }
        System.out.print("Nhập số lượng dữ liệu bạn muốn nhập điểm: ");
        int n = InputMissMatch.inputCheck();
        for (int i = 0 + time; i < n + time; i++) {
            System.out.println("Nhập sinh viên thứ nhất " + (i - time + 1) + " : ");
            System.out.print("Nhập mã sinh viên: ");
            Student student = findStudentId(i);
            int studentId = student.getStudentId();
            System.out.print("Nhập mã môn học: ");
            Subject subject = inputSubject(studentId);
            System.out.print("Nhập điểm môn học: ");
            float core = inputCore();
            coreManagement[i] = new CoreManagement(student, subject, core);
        }
        MainRun.coreManagement = coreManagement;
        return MainRun.coreManagement;
    }

    public static Subject inputSubject(int studentId) {
        //Nhập sách và số lượng muốn mượn tương ứng
        int subjectId = 0;
        Subject subject = null;
        do {
            do {
                try {
                    subjectId = new Scanner(System.in).nextInt();
                } catch (InputMismatchException ex) {
                    System.out.print("Mã môn học phải là 3 số và bé hơn 999, yêu cầu nhập lại: ");
                    continue;
                }
                if (subjectId > 99 && subjectId < 999 && findSubjectId(subjectId) != 0 && checkUniqePairId(studentId, subjectId) == 0) {
                    break;
                }
                System.out.print("Mã môn học phải là 3 số và bé hơn 999 hoặc không tồn tại hoặc mã môn học đã nhập cho sinh viên này rồi, yêu cầu nhập lại: ");
            } while (true);
            for (int i = 0; i < MainRun.subject.length; i++) {
                if (MainRun.subject[i] != null && MainRun.subject[i].getSubjectId() == subjectId) {
                    subject = MainRun.subject[i];
                    break;
                }
            }
            if (subject != null) {
                break;
            }
            System.out.print("Không tìm thấy sách với mã vừa nhập, vui lòng nhập lại mã: ");
        } while (true);
        return subject;
    }

    public static float inputCore() {
        float core = -1;
        do {
            try {
                core = new Scanner(System.in).nextFloat();
            } catch (InputMismatchException e) {
                System.out.print("Bạn cần nhập vào là số thực, không được nhập chữ.");
            }
            if (core >= 0 && core <= 10) {
                break;
            }
            System.out.print("Bạn chỉ có thể nhập điểm từ 0 đến 10. Xin vui lòng nhập lại: ");
        } while (true);
        return core;
    }

    //Kiểm tra cặp : môn đã tồn tại và mã sinh viên đã từng tồn tại trong bảng này hay chưa
    public static int checkUniqePairId(int studentId, int subjectId) {
        int value = 0;
        for (int i = 0; i < MainRun.coreManagement.length; i++) {
            if (MainRun.coreManagement[i] != null &&
                    MainRun.coreManagement[i].getSubject().getSubjectId() == subjectId &&
                    MainRun.coreManagement[i].getStudent().getStudentId() == studentId) {
                value = 1;
            }
        }
        return value;
    }

    public static Student findStudentId(int time) {
        Student student = null;
        do {
            int studentId = InputMissMatch.inputCheck();
            for (int i = 0; i < MainRun.student.length; i++) {
                if (MainRun.student[i] != null && MainRun.student[i].getStudentId() == studentId) {
                    student = MainRun.student[i];
                    break;
                }
            }
            if (student != null) {
                break;
            }
            System.out.print("Không tìm thấy sinh viên, vui lòng nhập lại: ");
        } while (true);
        return student;
    }

    public static int findSubjectId(int subjectId) {
        int find = 0;
        for (int i = 0; i < MainRun.subject.length; i++) {
            if (MainRun.subject[i] != null && MainRun.subject[i].getSubjectId() == subjectId) {
                find = 1;
            }
        }
        return find;
    }

    public static void createNewCoreManagement() {  //ghi theo Object
        addStudentCore();
        try {   // dat try cacth de tranh ngoai le khi tao va ghi File
            FileOutputStream f = new FileOutputStream(CORE_FILE_NAME);   // tao file f tro den student.dat
            ObjectOutputStream oStream = new ObjectOutputStream(f); // dung de ghi theo Object vao file f
            oStream.writeObject(MainRun.coreManagement);  // ghi student theo kieu Object vao file
            oStream.close();
        } catch (IOException e) {
            System.out.println("Lỗi ghi vào file");
        }
    }

    public static CoreManagement[] read() {
        // doc theo Object
        CoreManagement[] coreManagement = MainRun.coreManagement;
        File file = new File(CORE_FILE_NAME);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileInputStream f = new FileInputStream(CORE_FILE_NAME);
            if (f.available() != 0) {
                ObjectInputStream inStream = new ObjectInputStream(f);
                coreManagement = (CoreManagement[]) inStream.readObject();
                inStream.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy lớp. ");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file. ");
        }
        MainRun.coreManagement = coreManagement;
        return MainRun.coreManagement;
    }

    public static void showStudentCore() {      //In data doc duoc tu file ra man hinh
        read();
        if (MainRun.coreManagement[0] == null) {
            System.out.println("File đang rỗng. ");
        }
        try {
            for (int i = 0; i < MainRun.coreManagement.length; i++) {
                if (MainRun.coreManagement[i] != null) {
                    System.out.println(MainRun.coreManagement[i]);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("File đang rỗng.");
        }
    }

    //Sắp xếp bảng điểm
    public static void sortCoreManagerment() {
        if (MainRun.coreManagement[0] == null) {
            System.out.println("File bảng điểm đang rỗng, Bạn vui lồng nhập thông tin vào bảng điểm. ");
        } else {
            System.out.println("Sắp sếp danh sách bảng điểm");
            System.out.println("1.Theo tên sinh viên");
            System.out.println("2.Theo tên môn học");
            System.out.print("Nhập sự lựa chọn của bạn: ");
            int turn = 0;
            do {
                try {
                    turn = new Scanner(System.in).nextInt();
                } catch (InputMismatchException e) {
                    System.out.print("Lựa chọn sắp sếp danh sách phải là số nguyên, yêu cầu nhập lại: ");
                    continue;
                }
                if (turn > 0 && turn < 3) break;
                System.out.print("Lựa chọn cần nhập từ 1 đến 2. Xin vui lòng nhập lại: ");
            } while (true);

            switch (turn) {
                case 1:
                    sortByStudentName();
                    break;
                case 2:
                    sortBySubjectName();
                    break;
            }
        }
    }

    private static void sortDisplay() {
        for (int i = 0; i < MainRun.coreManagement.length; i++) {
            if (MainRun.coreManagement[i] != null) {
                System.out.println(MainRun.coreManagement[i]);
            }
        }
    }

    private static void sortByStudentName() {
        for (int i = 0; i < MainRun.coreManagement.length - 1; i++) {
            if (MainRun.coreManagement[i] == null) {
                continue;
            }
            for (int j = 0; j < MainRun.coreManagement.length; j++) {
                if (MainRun.coreManagement[j] == null) {
                    continue;
                }
                if (MainRun.coreManagement[i].getStudent().getName().toLowerCase(Locale.ROOT).compareTo(
                        MainRun.coreManagement[j].getStudent().getName().toLowerCase(Locale.ROOT)) < 0) {
                    CoreManagement temp = MainRun.coreManagement[i];
                    MainRun.coreManagement[i] = MainRun.coreManagement[j];
                    MainRun.coreManagement[j] = temp;
                }
            }
        }
        sortDisplay();
    }

    private static void sortBySubjectName() {
        for (int i = 0; i < MainRun.coreManagement.length - 1; i++) {
            if (MainRun.coreManagement[i] == null) {
                continue;
            }
            for (int j = 0; j < MainRun.coreManagement.length; j++) {
                if (MainRun.coreManagement[j] == null) {
                    continue;
                }
                if (MainRun.coreManagement[i].getSubject().getSubjectName().toLowerCase(Locale.ROOT).compareTo(
                        MainRun.coreManagement[j].getSubject().getSubjectName().toLowerCase(Locale.ROOT)) < 0) {
                    CoreManagement temp = MainRun.coreManagement[i];
                    MainRun.coreManagement[i] = MainRun.coreManagement[j];
                    MainRun.coreManagement[j] = temp;
                }
            }
        }
        sortDisplay();
    }

}
