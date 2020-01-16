package dz.trash.TrashBackend.controllers;

import dz.trash.TrashBackend.DAOs.ChallengeDAO;
import dz.trash.TrashBackend.DAOs.ClientDAO;
import dz.trash.TrashBackend.DAOs.PhotoDAO;
import dz.trash.TrashBackend.Models.Challenge;
import dz.trash.TrashBackend.Models.Client;
import dz.trash.TrashBackend.Models.Photo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
public class ChallengeController {

    ChallengeDAO challengeD;
    ClientDAO clientD;
    PhotoDAO photoD;

    // Create a new challenge
    @PostMapping(path = "/challenge/{id_user}", consumes = "application/json")
    public Challenge addchallenge(@PathVariable int id_user, @RequestBody Challenge challenge) throws ParseException {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml").addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml").addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml").configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        challengeD = new ChallengeDAO(session);
        Integer id = challengeD.findAll().size() + 1;
        challenge.setId_challenge(id);
        clientD=new ClientDAO(session);
        Client c1= clientD.find(id_user);
        challenge.setOwner(c1);
        //photoD=new PhotoDAO(session);
        //Photo p=photoD.find(id_photo);
        //challenge.addPhoto(p);
        challengeD.create(challenge);
        session.getTransaction().commit();
        session.close();
        return challenge;
    }

    //get a single challenge
    //@RequestMapping(value = "/challenges/{id}")
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
        challengeD = new ChallengeDAO(session);
       Challenge ch =challengeD.find(id);
        session.getTransaction().commit();
        session.close();
        return ch;
    }

    // Delete a challenge
    @DeleteMapping("/challenges/{id}")
    public ResponseEntity<?> deletechallenge(@PathVariable(value = "id") int id){
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml").addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml").addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml").configure("hibernate.cfg.xml").buildSessionFactory();
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
    @PutMapping("/challenges/{id_challenge}")
    public Challenge updatechallenge(@PathVariable int id_challenge, @RequestBody Challenge challenge) throws ParseException {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml").addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml").addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml").configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        challengeD = new ChallengeDAO(session);
        Challenge ch = challengeD.find(id_challenge);
        ch.setState(challenge.getState());
        ch.setEnding_date(challenge.getEnding_date());
        ch.setCity(challenge.getCity());
        ch.setCountry(challenge.getCountry());
        ch.setStarting_date(challenge.getStarting_date());
        ch.setLatitude(challenge.getLatitude());
        ch.setLongitude(challenge.getLongitude());
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

        challengeD = new ChallengeDAO(session);
        List<Challenge> l= challengeD.findAll();

        session.getTransaction().commit();
        session.close();
        return l;
    }

//*******************************************************************************************

    //create participation
    @PostMapping("/challenges/{id_challenge}/participate/{id_user}")
    @ResponseBody
    public String participate(@PathVariable int id_challenge,@PathVariable int id_user) throws ParseException {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ChallengeDAO challengeDAO = new ChallengeDAO(session);
        Challenge challenge=challengeDAO.find(id_challenge);
         Client client=new Client();
         client.setId_user(id_user);
        challenge.addParticipants(client);
         challengeDAO.update(challenge);
        session.getTransaction().commit();
        session.close();
         return"participation done!";
    }

    //get all participants
    @GetMapping("/participants/{id_challenge}")
    public Set<Client> getAllParticipant(@PathVariable int id_challenge) {
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
        Challenge challenge= challengeD.find(id_challenge);
        Set<Client> l = (Set<Client>) challenge.getParticipants();
        session.getTransaction().commit();
        session.close();
        return l;
    }


    //delete  participant
    @DeleteMapping("/challenges/{id_challenge}/delparticipant/{id_user}")
    public String deleteParticipant(@PathVariable int id_challenge,@PathVariable int id_user) throws ParseException {
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
        Challenge challenge= challengeD.find(id_challenge);
        Query q = session.createSQLQuery("delete FROM participants WHERE id_challenge=:id_challenge and id_user=:id_user").setParameter("id_challenge",id_challenge).setParameter("id_user",id_user);
        int c=q.executeUpdate();
        if (c==1) {
            session.getTransaction().commit();
            return "participant removed!!";
        }
        else {
            return "participant not  removed!!";
        }
    }




}
