package dz.trash.TrashBackend.controllers;

import dz.trash.TrashBackend.DAOs.ClientDAO;
import dz.trash.TrashBackend.Models.Client;
import org.hibernate.Query;
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
import java.util.Set;


@RestController
public class ClientController {
    ClientDAO clientD;

    // REGISTRATION a new client
    @GetMapping("/client/{last_name},{first_name},{user_name},{password},{birthdate},{phone_number},{android_version}")
    @ResponseBody
    public Client addclient(@PathVariable String last_name, @PathVariable String first_name,
                            @PathVariable String user_name, @PathVariable String password, @PathVariable Date birthdate, @PathVariable String phone_number,
                            @PathVariable String android_version) throws ParseException {
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
        //DateFormat df= DateFormat.getDateInstance(DateFormat.SHORT);
       // Date birthday = df.parse("15/12/1995");
        Client c1 = new Client(10, last_name,first_name,user_name,password,birthdate,phone_number,android_version);
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

    //update a client (mazal static)=>mandirouhch
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
    // login
    @GetMapping("/client/{user_name}/{password}")
    public List<Client> login( @PathVariable String user_name,@PathVariable String password) throws ParseException {
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
        Query q = session.createSQLQuery(" select * FROM users WHERE user_name=:user_name and password=:password").setParameter("user_name",user_name).setParameter("password",password);
        List<Client> c= (List<Client>) q.list();
        session.getTransaction().commit();
        return c;
    }


}
