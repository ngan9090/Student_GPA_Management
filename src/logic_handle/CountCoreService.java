package logic_handle;

import Main.MainRun;

public class CountCoreService {
    public static void countCore() {
        if (MainRun.coreManagement[0] == null) {
            System.out.println("File bảng điểm đang rỗng, Bạn vui lồng nhập thông tin vào bảng điểm. ");
        } else {
            System.out.println("Điểm tổng cho sinh viên đã có điểm như bên dưới: ");
            int[] studentId = new int[100];
            int a = 0;
            String inform = " ";

            for (int i = 0; i < MainRun.student.length; i++) {
                for (int j = 0; j < MainRun.coreManagement.length; j++) {
                    if (MainRun.student[i] != null && MainRun.coreManagement[j] != null &&
                            MainRun.student[i].getStudentId() == MainRun.coreManagement[j].getStudent().getStudentId()) {
                        studentId[a] = MainRun.student[i].getStudentId();
                        inform += "Mã Sinh viên " + studentId[a] + "  Tên: " + MainRun.student[i].getName() + " Điểm tổng: " + core(studentId[a]) + "\n ";
                        a = a + 1;
                        break;
                    }
                }
            }
            System.out.println(inform);
        }
    }

    public static float core(int studentId) {
        float core = 0;
        int unit = 0;
        for (int i = 0; i < MainRun.coreManagement.length; i++) {
            if (MainRun.coreManagement[i] != null && MainRun.coreManagement[i].getStudent().getStudentId() == studentId) {
                core += MainRun.coreManagement[i].getCore() * MainRun.coreManagement[i].getSubject().getSubjectStudingUnit();
                unit += MainRun.coreManagement[i].getSubject().getSubjectStudingUnit();
            }
        }
        float allCore = (float) Math.round((core / unit) * 100);
        return allCore / 100;
    }
}
