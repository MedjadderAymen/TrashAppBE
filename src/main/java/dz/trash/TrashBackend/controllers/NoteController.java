package dz.trash.TrashBackend.controllers;

import dz.trash.TrashBackend.DAOs.CommentDAO;
import dz.trash.TrashBackend.DAOs.NoteDAO;
import dz.trash.TrashBackend.Models.Challenge;
import dz.trash.TrashBackend.Models.Client;
import dz.trash.TrashBackend.Models.Comment;
import dz.trash.TrashBackend.Models.Note;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
@RestController
public class NoteController {
    // Create a new note
    @GetMapping("/note")
    @ResponseBody
    public String addnote() throws ParseException {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        DateFormat df= DateFormat.getDateInstance(DateFormat.SHORT);
        Date birthDate = df.parse("15/12/1995");
        Client c1 = new Client(4, "zebair","manel","manel123","123", birthDate,"0655358656","v8");
        NoteDAO noteDAO = new NoteDAO(session);


        Note n=new Note(1,5,c1,1);

        noteDAO.create(n);
        session.getTransaction().commit();
        session.close();
        return"note added !!";
    }

}
