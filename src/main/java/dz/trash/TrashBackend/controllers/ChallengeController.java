package dz.trash.TrashBackend.controllers;

import dz.trash.TrashBackend.DAOs.ChallengeDAO;
import dz.trash.TrashBackend.DAOs.ClientDAO;
import dz.trash.TrashBackend.Models.Challenge;
import dz.trash.TrashBackend.Models.Client;
import dz.trash.TrashBackend.Models.Photo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
public class ChallengeController {

    ChallengeDAO challengeD;

    // Create a new challenge
    @GetMapping("/challenge")
    @ResponseBody
    public String addchallenge() throws ParseException {
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
        DateFormat df= DateFormat.getDateInstance(DateFormat.SHORT);
        Date endingDate = df.parse("12/02/2020");
        Date birthday = df.parse("09/10/1995");
        Challenge ch=new Challenge(2,date,1,date,endingDate, 1.5f,1.8f,"khroub","constantine","25000","algeria");
        Client c1 = new Client(8, "atmani","nadia","nadouch","12hgk3",birthday,"0655589656","v9");
        ch.setOwner(c1);
        Photo p=new Photo(6,"photo.png",date);
        ch.addPhoto(p);
        challengeD.create(ch);
        session.getTransaction().commit();
        session.close();
        return"challenge added !! ";
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
    public Challenge updatechallenge(@PathVariable(value = "id") int id,@Valid @RequestBody Challenge challengeDetails) throws ParseException {
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
        DateFormat df= DateFormat.getDateInstance(DateFormat.SHORT);
        Date endingDate = df.parse("15/01/2020");
        ch.setEnding_date(endingDate);
        ch.setState(0);
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

    @GetMapping("/participate/{id}")
    @ResponseBody
    public String participate(@PathVariable int id) throws ParseException {
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
        ClientDAO clientDAO= new ClientDAO(session);
        Challenge challenge=challengeDAO.find(id);
        System.out.println("challenge found:"+challenge);

        DateFormat df= DateFormat.getDateInstance(DateFormat.SHORT);
        Date birthday = df.parse("15/12/1995");
        Client client = new Client(4, "zebair","manel","manel123","123",birthday,"0655358656","v8");
        //Client client=new Client();
       //   client.setId_user(id_user);
        challenge.addParticipants(client);
        System.out.println("user participated in "+challenge);
         challengeDAO.update(challenge);
         return"participation done!";
    }
    //get all participants
    @GetMapping("/participants/{id}")
    public List<Client> getAllParticipant(@PathVariable int id) {
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
        Challenge challenge= challengeD.find(id);
        List<Client>   l = (List<Client>) challenge.getParticipants();
        session.getTransaction().commit();
        session.close();
        return l;
    }




}
