/*
class “Student”
    name, roll no and an array of five subject names
    constructor and get/set methods 

class “TabulationSheet”
    contains roll numbers and marks of each student for a particular subject
    method for adding the marks and roll no of a student

class “MarkSheet”
    contains marks of all subjects for a particular student
    method to add name of a student and marks in each subject

Write a main() function
    create three “Student” objects
    Five “Tabulationsheet” objects for Five subjects
    three “Marksheet” object for three students
    Print the mark sheets
*/

package q12;

import java.util.Arrays;

class Student {
    private String name;
    private int rollNo;
    private String[] subjectNames;

    public Student(String name, int rollNo, String[] subjectNames) {
        this.name = name;
        this.rollNo = rollNo;
        this.subjectNames = Arrays.copyOf(subjectNames, subjectNames.length);
    }

    public String getName() {
        return name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String[] getSubjectNames() {
        return Arrays.copyOf(subjectNames, subjectNames.length);
    }
}

class TabulationSheet {
    private int[] rollNumbers;
    private int[][] marks;

    public TabulationSheet(int[] rollNumbers) {
        this.rollNumbers = Arrays.copyOf(rollNumbers, rollNumbers.length);
        this.marks = new int[rollNumbers.length][1];
    }

    public void addMarks(int rollNo, int[] subjectMarks) {
        int index = Arrays.binarySearch(rollNumbers, rollNo);
        if (index >= 0) {
            marks[index] = Arrays.copyOf(subjectMarks, subjectMarks.length);
        } else {
            System.out.println("Roll number not found: " + rollNo);
        }
    }
}

class MarkSheet {
    private String[] studentNames;
    private int[][] subjectMarks;

    public MarkSheet(String[] studentNames, int numSubjects) {
        this.studentNames = Arrays.copyOf(studentNames, studentNames.length);
        this.subjectMarks = new int[studentNames.length][numSubjects];
    }

    public void addMarks(String studentName, int[] marks) {
        int index = Arrays.asList(studentNames).indexOf(studentName);
        if (index >= 0) {
            subjectMarks[index] = Arrays.copyOf(marks, marks.length);
        } else {
            System.out.println("Student not found: " + studentName);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        String[] subjects = {"Math", "Physics", "Chemistry", "English", "History"};
        int numberOfSubjects = subjects.length;

        Student s1 = new Student("Alice", 101, subjects);
        Student s2 = new Student("Bob", 102, subjects);
        Student s3 = new Student("Charlie", 103, subjects);

        int[] rollNumbers = {s1.getRollNo(), s2.getRollNo(), s3.getRollNo()};
        TabulationSheet ts1 = new TabulationSheet(rollNumbers); // subjects[0]
        TabulationSheet ts2 = new TabulationSheet(rollNumbers); // subjects[1]
        TabulationSheet ts3 = new TabulationSheet(rollNumbers); // subjects[2]
        TabulationSheet ts4 = new TabulationSheet(rollNumbers); // subjects[3]
        TabulationSheet ts5 = new TabulationSheet(rollNumbers); // subjects[4]

        String[] studentNames = {s1.getName(), s2.getName(), s3.getName()};
        MarkSheet ms1 = new MarkSheet(studentNames, numberOfSubjects);
        MarkSheet ms2 = new MarkSheet(studentNames, numberOfSubjects);
        MarkSheet ms3 = new MarkSheet(studentNames, numberOfSubjects);
        MarkSheet ms4 = new MarkSheet(studentNames, numberOfSubjects);
        MarkSheet ms5 = new MarkSheet(studentNames, numberOfSubjects);

        ts1.addMarks(101, new int[]{95});
        ts2.addMarks(102, new int[]{88});
        ts3.addMarks(103, new int[]{75});
        ts4.addMarks(101, new int[]{85});
        ts5.addMarks(102, new int[]{92});

        ms1.addMarks("Alice", new int[]{95});
        ms2.addMarks("Bob", new int[]{88});
        ms3.addMarks("Charlie", new int[]{75});
        ms4.addMarks("Alice", new int[]{85});
        ms5.addMarks("Bob", new int[]{92});

        printMarkSheet("Math", ts1, ms1);
        printMarkSheet("Physics", ts2, ms2);
        printMarkSheet("Chemistry", ts3, ms3);
        printMarkSheet("English", ts4, ms4);
        printMarkSheet("History", ts5, ms5);
    }

    private static void printMarkSheet(String subject, TabulationSheet tabulationSheet, MarkSheet markSheet) {
        System.out.println("Subject: " + subject);
        for (int i = 0; i < tabulationSheet.getRollNumbers().length; i++) {
            int rollNo = tabulationSheet.getRollNumbers()[i];
            String studentName = Arrays.asList(markSheet.getStudentNames()).get(i);
            int[] marks = tabulationSheet.getMarks()[i];
            System.out.println("Roll No: " + rollNo + ", Student: " + studentName + ", Marks: " + Arrays.toString(marks));
        }
        System.out.println();
    }
}

