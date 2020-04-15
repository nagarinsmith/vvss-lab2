package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BigBangTest {

    private File studentFile = new File("testStudent.xml");
    private File assignmentFile = new File("testAssignment.xml");
    private File gradeFile = new File("testGrade.xml");
    private StudentXMLRepository studentXMLRepository;
    private TemaXMLRepository assignmentXMLRepository;
    private NotaXMLRepository gradeXMLRepository;
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

        try {
            assignmentFile.createNewFile();
            FileWriter writer = new FileWriter(assignmentFile);
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<Entitati>\n</Entitati>\n");
            writer.close();
        } catch (IOException e) {
        }
        assignmentXMLRepository = new TemaXMLRepository(new TemaValidator(), "testAssignment.xml");

        try {
            gradeFile.createNewFile();
            FileWriter writer = new FileWriter(gradeFile);
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<Entitati>\n</Entitati>\n");
            writer.close();
        } catch (IOException e) {
        }
        gradeXMLRepository = new NotaXMLRepository(new NotaValidator(), "testGrade.xml");

        service = new Service(studentXMLRepository, assignmentXMLRepository, gradeXMLRepository);
    }

    @After
    public void tearDown() {
        service = null;
        studentXMLRepository = null;
        studentFile.delete();
    }

    @Test
    public void AddAssignment_ValidData_AssignmentAddedToRepo() {
        // arrange
        String id = "testId";
        String descriere = "testDescriere";
        int deadline = 1;
        int startline = 1;

        // act
        service.saveTema(id, descriere, deadline, startline);

        // assert
        Tema tema = null;
        for (Tema tema1 : service.findAllTeme()) {
            if (tema1.getID().equals(id)) {
                tema = tema1;
            }
        }
        assert tema != null;
        assert tema.getDescriere().equals(descriere);
        assert tema.getDeadline() == deadline;
        assert tema.getStartline() == startline;
    }

    @Test
    public void AddStudent_ValidData_UserAddedToRepo() {
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
    public void AddGrade_ValidData_GradeAddedToRepo() {
        // arrange
        String idStudent = "5";
        String idTema = "testId";
        double notaVal = 9.0;
        int predata = 1;
        String feedback = "";

        AddAssignment_ValidData_AssignmentAddedToRepo();
        AddStudent_ValidData_UserAddedToRepo();

        // act
        service.saveNota(idStudent, idTema, notaVal, predata, feedback);

        // assert
        Nota nota = null;
        for (Nota n : service.findAllNote()) {
            if (n.getID().getObject1().equals(idStudent) && n.getID().getObject2().equals(idTema)) {
                nota = n;
            }
        }
        assert nota != null;
        assert nota.getNota() == notaVal;
        assert nota.getSaptamanaPredare() == predata;
        assert nota.getFeedback().equals(feedback);
    }

    @Test
    public void AddObjects_ValidData_ObjectsAddedToRepo() {
        AddAssignment_ValidData_AssignmentAddedToRepo();
        AddStudent_ValidData_UserAddedToRepo();
        AddGrade_ValidData_GradeAddedToRepo();
    }
}
