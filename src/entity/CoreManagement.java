package entity;

import java.io.Serializable;
import java.util.Arrays;

public class CoreManagement implements Serializable {
    protected Student student;
    protected Subject subject;
    protected float core;

    public CoreManagement() {
    }

    public CoreManagement(Student student, Subject subject, float core) {
        this.student = student;
        this.subject = subject;
        this.core = core;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public float getCore() {
        return core;
    }

    public void setCore(float core) {
        this.core = core;
    }

    @Override
    public String toString() {
        return "CoreManagement{" +
                "student=" + student +
                ", subject=" + subject +
                ", core=" + core +
                '}';
    }
}
