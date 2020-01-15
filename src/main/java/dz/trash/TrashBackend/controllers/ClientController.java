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

    // registration a new client
    @PostMapping("/client/{last_name},{first_name},{username},{password},{androidV},{tlfn},{birthday}")
    public Client addclient(@PathVariable ("first_name") String first_name,
                            @PathVariable ("last_name") String last_name,
                            @PathVariable ("username") String username,
                            @PathVariable ("password") String password,
                            @PathVariable ("androidV") String androidV,
                            @PathVariable ("tlfn") String tlfn,
                            @PathVariable ("birthday") String birthday) throws ParseException {
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
        Date dt = df.parse("20/08/2019");
        Client c1 = new Client(4, last_name,first_name,username,password,dt,tlfn,androidV);
      clientD.create(c1);
      session.getTransaction().commit();
      session.close();
      return c1;

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
