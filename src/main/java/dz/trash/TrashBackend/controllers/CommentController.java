package dz.trash.TrashBackend.controllers;


import dz.trash.TrashBackend.DAOs.ChallengeDAO;
import dz.trash.TrashBackend.DAOs.ClientDAO;
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
    CommentDAO commentDAO;
    ChallengeDAO challengeDAO;
    ClientDAO clientDAO;
    // Create a new comment  ????????,mazal matamchch
    @PostMapping("/comment/{id_user}/{id_challenge}")
    public Comment addcomment(@PathVariable int id_user, @PathVariable int id_challenge,@RequestBody Comment comment) throws ParseException {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        commentDAO = new CommentDAO(session);
        Integer id = commentDAO.findAll().size() + 1;
        comment.setId_comment(id);
        Challenge challenge=challengeDAO.find(id_challenge);
        Client c1 = clientDAO.find(id_user);
        comment.addClient(c1);
        challenge.addComment(comment);
        challengeDAO.update(challenge);
        commentDAO.create(comment);

        session.getTransaction().commit();
        session.close();
        return comment;
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
    @PutMapping("/commentup/{id}")
    public Comment updatecomment(@PathVariable(value = "id") int id,@RequestBody Comment comment) {
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
        c.setContent(comment.getContent());
        c.setIs_enabled(comment.getIs_enabled());
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
