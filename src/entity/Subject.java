package entity;

import Main.MainRun;
import constant.SubjectStyle;

import java.io.*;

public class Subject implements Serializable {
    protected static int SUBJECT_ID = 100;
    protected int subjectId;
    protected String subjectName;
    protected int subjectStudingUnit;
    private SubjectStyle subjectStyle;

    public Subject() {
    }

    public Subject(String subjectName, int subjectStudingUnit, SubjectStyle subjectStyle) {
        int turn = 0;
        for (int i = 0; i < MainRun.subject.length; i++) {
            if (MainRun.subject[i] != null) turn++;
        }
        if (MainRun.subject[0] == null) {
            this.subjectId = SUBJECT_ID;
        }
        if (turn != 0) {
            this.subjectId = MainRun.subject[(turn - 1)].getSubjectId() + 1;
        }
        this.subjectName = subjectName;
        this.subjectStudingUnit = subjectStudingUnit;
        this.subjectStyle = subjectStyle;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) { this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSubjectStudingUnit() {
        return subjectStudingUnit;
    }

    public void setSubjectStudingUnit(int subjectStudingUnit) {
        this.subjectStudingUnit = subjectStudingUnit;
    }

    public SubjectStyle getSubjectStyle() {
        return subjectStyle;
    }

    public void setSubjectStyle(SubjectStyle subjectStyle) {
        this.subjectStyle = subjectStyle;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", subjectStudingUnit=" + subjectStudingUnit +
                ", subjectStyle=" + subjectStyle +
                '}';
    }
}
