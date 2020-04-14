package service;

import domain.Tema;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.TemaXMLRepository;
import validation.TemaValidator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddAssignmentTest {

    private File assignmentFile = new File("testAssignment.xml");
    private TemaXMLRepository assignmentXMLRepository;
    private Service service;

    @Before
    public void initialize() {
        try {
            assignmentFile.createNewFile();
            FileWriter writer = new FileWriter(assignmentFile);
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<Entitati>\n</Entitati>\n");
            writer.close();
        } catch (IOException e) {
        }
        assignmentXMLRepository = new TemaXMLRepository(new TemaValidator(), "testAssignment.xml");
        service = new Service(null, assignmentXMLRepository, null);
    }

    @After
    public void tearDown() {
        service = null;
        assignmentXMLRepository = null;
        assignmentFile.delete();
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
    public void AddAssignment_ValidData2_AssignmentAddedToRepo() {
        // arrange
        String id = "testId";
        String descriere = "testDescriere";
        int deadline = 14;
        int startline = 14;

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
    public void AddAssignment_EmptyDescription_AssignmentNotAddedToRepo() {
        // arrange
        String id = "testId";
        String descriere = "";
        int deadline = 4;
        int startline = 3;

        // act
        service.saveTema(id, descriere, deadline, startline);

        // assert
        Tema tema = null;
        for (Tema tema1 : service.findAllTeme()) {
            if (tema1.getID().equals(id)) {
                tema = tema1;
            }
        }
        assert tema == null;
    }

    @Test
    public void AddAssignment_NullDescription_AssignmentNotAddedToRepo() {
        // arrange
        String id = "testId";
        String descriere = null;
        int deadline = 4;
        int startline = 3;

        // act
        service.saveTema(id, descriere, deadline, startline);

        // assert
        Tema tema = null;
        for (Tema tema1 : service.findAllTeme()) {
            if (tema1.getID().equals(id)) {
                tema = tema1;
            }
        }
        assert tema == null;
    }

    @Test
    public void AddAssignment_EmptyId_AssignmentNotAddedToRepo() {
        // arrange
        String id = "";
        String descriere = "testDescriere";
        int deadline = 4;
        int startline = 3;

        // act
        service.saveTema(id, descriere, deadline, startline);

        // assert
        Tema tema = null;
        for (Tema tema1 : service.findAllTeme()) {
            if (tema1.getID().equals(id)) {
                tema = tema1;
            }
        }
        assert tema == null;
    }

    @Test
    public void AddAssignment_NullId_AssignmentNotAddedToRepo() {
        // arrange
        String id = null;
        String descriere = "testDescriere";
        int deadline = 4;
        int startline = 3;

        // act
        service.saveTema(id, descriere, deadline, startline);

        // assert
        Tema tema = null;
        for (Tema tema1 : service.findAllTeme()) {
            if (tema1.getID().equals(id)) {
                tema = tema1;
            }
        }
        assert tema == null;
    }

    @Test
    public void AddAssignment_DeadlineWeek0_AssignmentNotAddedToRepo() {
        // arrange
        String id = "testId";
        String descriere = "testDescriere";
        int deadline = 0;
        int startline = 3;

        // act
        service.saveTema(id, descriere, deadline, startline);

        // assert
        Tema tema = null;
        for (Tema tema1 : service.findAllTeme()) {
            if (tema1.getID().equals(id)) {
                tema = tema1;
            }
        }
        assert tema == null;
    }

    @Test
    public void AddAssignment_DeadlineWeek15_AssignmentNotAddedToRepo() {
        // arrange
        String id = "testId";
        String descriere = "testDescriere";
        int deadline = 15;
        int startline = 3;

        // act
        service.saveTema(id, descriere, deadline, startline);

        // assert
        Tema tema = null;
        for (Tema tema1 : service.findAllTeme()) {
            if (tema1.getID().equals(id)) {
                tema = tema1;
            }
        }
        assert tema == null;
    }

    @Test
    public void AddAssignment_DeadlineEarlierThanStartline_AssignmentNotAddedToRepo() {
        // arrange
        String id = "testId";
        String descriere = "testDescriere";
        int deadline = 3;
        int startline = 4;

        // act
        service.saveTema(id, descriere, deadline, startline);

        // assert
        Tema tema = null;
        for (Tema tema1 : service.findAllTeme()) {
            if (tema1.getID().equals(id)) {
                tema = tema1;
            }
        }
        assert tema == null;
    }

    @Test
    public void AddAssignment_StartlineWeek0_AssignmentNotAddedToRepo() {
        // arrange
        String id = "testId";
        String descriere = "testDescriere";
        int deadline = 4;
        int startline = 0;

        // act
        service.saveTema(id, descriere, deadline, startline);

        // assert
        Tema tema = null;
        for (Tema tema1 : service.findAllTeme()) {
            if (tema1.getID().equals(id)) {
                tema = tema1;
            }
        }
        assert tema == null;
    }

    @Test
    public void AddAssignment_StartlineWeek15_AssignmentNotAddedToRepo() {
        // arrange
        String id = "testId";
        String descriere = "testDescriere";
        int deadline = 4;
        int startline = 15;

        // act
        service.saveTema(id, descriere, deadline, startline);

        // assert
        Tema tema = null;
        for (Tema tema1 : service.findAllTeme()) {
            if (tema1.getID().equals(id)) {
                tema = tema1;
            }
        }
        assert tema == null;
    }

    @Test
    public void AddAssignment_AddTwice_AssignmentAddedToRepo() {
        // arrange
        String id = "testId";
        String descriere = "testDescriere";
        int deadline = 4;
        int startline = 3;

        // act
        service.saveTema(id, descriere, deadline, startline);
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
}
