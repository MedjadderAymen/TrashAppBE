package dz.trash.TrashBackend.controllers;

import dz.trash.TrashBackend.DAOs.ChallengeDAO;
import dz.trash.TrashBackend.DAOs.ClientDAO;
import dz.trash.TrashBackend.Models.Challenge;
import dz.trash.TrashBackend.Models.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class ChallengeController {

    ChallengeDAO challengeD;

    // Create a new challenge
    @GetMapping("/challenge")
    @ResponseBody
    public String addchallenge(){
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        challengeD = new ChallengeDAO(session);
        Date date =new Date();
        Challenge ch=new Challenge(1,date,1,date,date, 1.5f,1.8f,"rout batna","khenchela","40000","algeria");
        challengeD.create(ch);

        session.getTransaction().commit();
        session.close();
        return"challenge added !! ";
    }

    //get a single challenge
    @GetMapping("/challenges/{id}")
    public Challenge findById(@PathVariable int id){
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

       Challenge ch =challengeD.find(id);

        session.getTransaction().commit();
        session.close();
        return ch;
    }

    // Delete a challenge
    @DeleteMapping("/challenges/{id}")
    public ResponseEntity<?> deletechallenge(@PathVariable(value = "id") int id){
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        challengeD = new ChallengeDAO(session);
        Challenge ch = challengeD.find(id);
        challengeD.delete(ch);

        session.getTransaction().commit();
        session.close();
        return ResponseEntity.ok().build();
    }

    //update a challenge
    @PutMapping("/challenges/{id}")
    public Challenge updatechallenge(@PathVariable(value = "id") int id,@Valid @RequestBody Challenge challengeDetails) {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Challenge ch = challengeD.find(id);
        ch.setEnding_date(challengeDetails.getEnding_date());
        ch.setState(challengeDetails.getState());
        challengeD.update(ch);

        session.getTransaction().commit();
        session.close();
        return ch;

    }

    //get all challenge
    @GetMapping("/challenges")
    public List<Challenge> getAllchallenge() {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Challenge> l= challengeD.findAll();

        session.getTransaction().commit();
        session.close();
        return l;
    }

}
