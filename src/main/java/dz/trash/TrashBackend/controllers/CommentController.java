package dz.trash.TrashBackend.controllers;


import dz.trash.TrashBackend.DAOs.ChallengeDAO;
import dz.trash.TrashBackend.DAOs.CommentDAO;
import dz.trash.TrashBackend.DAOs.PhotoDAO;
import dz.trash.TrashBackend.Models.Challenge;
import dz.trash.TrashBackend.Models.Client;
import dz.trash.TrashBackend.Models.Comment;
import dz.trash.TrashBackend.Models.Photo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
public class CommentController {
    // Create a new comment
    @GetMapping("/comment")
    @ResponseBody
    public String addcomment() throws ParseException {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CommentDAO commentDAO = new CommentDAO(session);
        Date date= new Date();
        DateFormat df= DateFormat.getDateInstance(DateFormat.SHORT);
        Date birthDate = df.parse("15/12/1995");
        Date endingDate = df.parse("12/01/2020");
        Challenge ch=new Challenge(2,date,1,date,endingDate, 1.5f,1.8f,"khroub","constantine","25000","algeria");
        Client c1 = new Client(4, "zebair","manel","manel123","123", birthDate,"0655358656","v8");
        Comment c =new Comment(1,"best ever",date,true,c1,2 );
        ch.addComment(c);
        c.addClient(c1);
        commentDAO.create(c);

        session.getTransaction().commit();
        session.close();
        return"comment added !! ";
    }

    //get a comment
    @GetMapping("/comments/{id}")
    public Comment findById(@PathVariable int id){
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

      CommentDAO commentDAO =new CommentDAO(session);
        Comment c =commentDAO.find(id);

        session.getTransaction().commit();
        session.close();
        return c;
    }
    // Delete a comment
    @DeleteMapping("/commentsdel/{id}")
    public ResponseEntity<?> deletecomment(@PathVariable(value = "id") int id){
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CommentDAO commentdao = new CommentDAO(session);
        Comment c= commentdao.find(id);
        commentdao.delete(c);

        session.getTransaction().commit();
        session.close();
        return ResponseEntity.ok().build();
    }

    //update a comment
    @RequestMapping(value = "/commentup/{id}", method = RequestMethod.PUT)
    //@GetMapping("/commentup/{id}")
    public Comment updatecomment(@PathVariable(value = "id") int id) {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CommentDAO commentDAO=new CommentDAO(session);
        Comment c = commentDAO.find(id);
        c.setContent("best");
        c.setIs_enabled(false);
        commentDAO.update(c);

        session.getTransaction().commit();
        session.close();
        return c;

    }

    //get all comment
    @GetMapping("/comments")
    public List<Comment> getAllcomment() {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CommentDAO commentDAO=new CommentDAO(session);
        List<Comment> l= commentDAO.findAll();

        session.getTransaction().commit();
        session.close();
        return l;
    }


}
