package entity;

import Main.MainRun;

import java.io.Serializable;
import java.util.Scanner;

public class Student extends Person implements NewDataCreatable, Serializable {
    protected static int AUTO_ID = 10000;
    protected int studentId;
    protected String studentClass;


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Student() {

    }

    public Student(String name, String address, String phoneNumber, String studentClass) {
        int turn = 0;
        for (int i = 0; i < MainRun.student.length; i++) {
            if(MainRun.student[i] != null) turn++;
        }
        if(MainRun.student[0] == null){
            this.studentId = AUTO_ID;
        }
        if(turn != 0){
            this.studentId = MainRun.student[(turn -1)].getStudentId() + 1;
        }
        super.name = name;
        super.address = address;
        super.phoneNumber = phoneNumber;
        this.studentClass = studentClass;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", studentClass='" + studentClass + '\'' +
                '}';
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    @Override
    public void inputNewData(){
    }
}
