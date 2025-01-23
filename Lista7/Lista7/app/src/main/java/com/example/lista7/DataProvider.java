package com.example.lista7;

import com.example.lista7.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public final class DataProvider {
    public DataProvider() {}
    private static final List<String> firstNames = Arrays.asList(
            "Adam", "Ewa", "Jan", "Anna", "Piotr", "Maria", "Tomasz", "Małgorzata", "Krzysztof", "Alicja",
            "Andrzej", "Joanna", "Michał", "Barbara", "Kamil", "Magdalena", "Robert", "Monika", "Mateusz", "Natalia"
    );

    private static final List<String> lastNames = Arrays.asList(
            "Nowak", "Kowalski", "Wiśniewski", "Wójcik", "Kowalczyk", "Kamiński", "Lewandowski", "Zieliński", "Szymański",
            "Woźniak", "Dąbrowski", "Kozłowski", "Jankowski", "Mazur", "Kwiatkowski", "Krawczyk", "Piotrowski", "Grabowski",
            "Nowakowski", "Pawłowski"
    );
    private static final Random random = new Random();

    public static List<Student> getStudents() {
        return generateStudents(41);
    }

    private static List<Student> generateStudents(int count) {
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Student newStudent = new Student();
            newStudent.setName(getRandomElement(firstNames));
            newStudent.setLastName(getRandomElement(lastNames));
            newStudent.setAcademicYear(random.nextInt(3) + 1);

            String indexNumber = String.format(Locale.getDefault(),"3%d%d%d%d%d",
                    random.nextInt(10), random.nextInt(10), random.nextInt(10),
                    random.nextInt(10), random.nextInt(10));
            newStudent.setIndexNumber(indexNumber);

            double gradesMean = 2.0 + (5.0 - 2.0) * random.nextDouble();
            gradesMean = Math.round(gradesMean * 100) / 100.0;
            newStudent.setGradesMean(gradesMean);

            students.add(newStudent);
        }
        return students;
    }

    private static String getRandomElement(List<String> list) {
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
