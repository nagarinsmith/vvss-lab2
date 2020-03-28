package service;

import domain.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.StudentXMLRepository;
import validation.StudentValidator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddStudentTest {

    private File studentFile = new File("testStudent.xml");
    private StudentXMLRepository studentXMLRepository;
    private Service service;

    @Before
    public void initialize() {
        try {
            studentFile.createNewFile();
            FileWriter writer = new FileWriter(studentFile);
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<Entitati>\n</Entitati>\n");
            writer.close();
        } catch (IOException e) {
        }
        studentXMLRepository = new StudentXMLRepository(new StudentValidator(), "testStudent.xml");
        service = new Service(studentXMLRepository, null, null);
    }

    @After
    public void tearDown() {
        service = null;
        studentXMLRepository = null;
        studentFile.delete();
    }

    @Test
    public void SaveStudent_ValidData_UserAddedToRepo() {
        // arrange
        String id = "5";
        String name = "TestStudent";
        int group = 111;

        // act
        service.saveStudent(id, name, group);

        // assert
        Student student = null;
        for (Student stud : service.findAllStudents()) {
            if (stud.getID().equals(id)) {
                student = stud;
            }
        }
        assert student != null;
        assert student.getNume().equals(name);
        assert student.getGrupa() == group;
    }

    @Test
    public void SaveStudent_InvalidGroup_UserNotAddedToRepo() {
        // arrange
        String id = "5";
        String name = "TestStudent";
        int group = 109; // Invalid group

        // act
        service.saveStudent(id, name, group);

        // assert
        Student student = null;
        for (Student stud : service.findAllStudents()) {
            if (stud.getID().equals(id)) {
                student = stud;
            }
        }
        assert student == null;
    }

    @Test
    public void SaveStudent_InvalidName_UserNotAddedToRepo() {
        // arrange
        String id = "5";
        String name = "";
        int group = 111;

        // act
        service.saveStudent(id, name, group);

        // assert
        Student student = null;
        for (Student stud : service.findAllStudents()) {
            if (stud.getID().equals(id)) {
                student = stud;
            }
        }
        assert student == null;
    }

    @Test
    public void SaveStudent_InvalidId_UserNotAddedToRepo() {
        // arrange
        String id = "";
        String name = "TestStudent";
        int group = 111;

        // act
        service.saveStudent(id, name, group);

        // assert
        Student student = null;
        for (Student stud : service.findAllStudents()) {
            if (stud.getID().equals(id)) {
                student = stud;
            }
        }
        assert student == null;
    }

    @Test
    public void SaveStudent_NullName_UserNotAddedToRepo() {
        // arrange
        String id = "5";
        String name = null;
        int group = 111;

        // act
        service.saveStudent(id, name, group);

        // assert
        Student student = null;
        for (Student stud : service.findAllStudents()) {
            if (stud.getID().equals(id)) {
                student = stud;
            }
        }
        assert student == null;
    }

    @Test
    public void SaveStudent_NullId_UserNotAddedToRepo() {
        // arrange
        String id = null;
        String name = "TestStudent";
        int group = 111;

        // act
        service.saveStudent(id, name, group);

        // assert
        Student student = null;
        for (Student stud : service.findAllStudents()) {
            if (stud.getID().equals(id)) {
                student = stud;
            }
        }
        assert student == null;
    }

    @Test
    public void SaveStudent_ValidDataAddTwice_UserAddedToRepo() {
        // arrange
        String id = "5";
        String name = "TestStudent";
        int group = 111;

        // act
        service.saveStudent(id, name, group);
        service.saveStudent(id, name, group);

        // assert
        Student student = null;
        for (Student stud : service.findAllStudents()) {
            if (stud.getID().equals(id)) {
                student = stud;
            }
        }
        assert student != null;
        assert student.getNume().equals(name);
        assert student.getGrupa() == group;
    }
}