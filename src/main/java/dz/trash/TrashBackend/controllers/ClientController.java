package dz.trash.TrashBackend.controllers;

import dz.trash.TrashBackend.DAOs.ClientDAO;
import dz.trash.TrashBackend.Models.Client;
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
public class ClientController {
    ClientDAO clientD;

    // Create a new client
    @GetMapping("/client")
    @ResponseBody
    public String addclient() throws ParseException {
    SessionFactory sessionFactory = new Configuration()
            .addResource("Hibernate/Challenge.hbm.xml")
            .addResource("Hibernate/User.hbm.xml")
            .addResource("Hibernate/Comment.hbm.xml")
            .addResource("Hibernate/Note.hbm.xml")
            .addResource("Hibernate/Photo.hbm.xml")
            .configure("hibernate.cfg.xml").buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();

       clientD = new ClientDAO(session);
        DateFormat df= DateFormat.getDateInstance(DateFormat.SHORT);
        Date birthday = df.parse("15/12/1995");
        Client c1 = new Client(4, "zebair","manel","manel123","123",birthday,"0655358656","v8");
      clientD.create(c1);
      session.getTransaction().commit();
      session.close();
      return"client added !! ";

}

    //get a single client
    @GetMapping("/clients/{id}")
    public Client findById(@PathVariable int id){
        Configuration configuration = new Configuration();
        configuration.addResource("Hibernate/Challenge.hbm.xml");
        configuration.addResource("Hibernate/User.hbm.xml");
        configuration.addResource("Hibernate/Comment.hbm.xml");
        configuration.addResource("Hibernate/Note.hbm.xml");
        configuration.addResource("Hibernate/Photo.hbm.xml");
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        clientD = new ClientDAO(session);
        Client c=clientD.find(id);

        session.getTransaction().commit();
        session.close();
        return c;
    }

    // Delete a client
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") int id){
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        clientD = new ClientDAO(session);
        Client c = clientD.find(id);
        clientD.delete(c);

        session.getTransaction().commit();
        session.close();
        return ResponseEntity.ok().build();
    }

    //update a client
    @PutMapping("/clients/{id}")
    public Client updateclient(@PathVariable(value = "id") int id,@Valid @RequestBody Client clientDetails) {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        clientD = new ClientDAO(session);
        Client c = clientD.find(id);
        //c.setPhone_number(clientDetails.getPhone_number());
        c.setPhone_number("0654895415");
        c.setUser_name("manelmanel");
        clientD.update(c);

        session.getTransaction().commit();
        session.close();
        return c;

    }

    //get all clients
    @GetMapping("/clients")
    public List<Client> getAllclient() {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        clientD = new ClientDAO(session);

        List<Client> l= clientD.findAll();

        session.getTransaction().commit();
        session.close();
        return l;
    }
}
