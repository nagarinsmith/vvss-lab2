package service;

import domain.Nota;
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

public class AddGradeTest {

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
        assignmentXMLRepository = null;
        gradeXMLRepository = null;
        studentFile.delete();
        assignmentFile.delete();
        gradeFile.delete();
    }

    @Test
    public void AddGrade_ValidData_GradeAddedToRepo() {
        // arrange
        String idStudent = "5";
        String name = "TestStudent";
        int group = 111;

        String idTema = "testId";
        String descriere = "testDescriere";
        int deadline = 1;
        int startline = 1;

        double notaVal = 9.0;
        int predata = 1;
        String feedback = "";

        service.saveStudent(idStudent, name, group);
        service.saveTema(idTema, descriere, deadline, startline);

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
}
