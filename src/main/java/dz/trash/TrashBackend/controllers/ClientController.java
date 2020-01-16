
//****************************************************************
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

    // REGISTRATION  a new client
    @PostMapping("/client")
    public Client addclient(@RequestBody Client client) throws ParseException {
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
        clientD.create(client);
        session.getTransaction().commit();
        session.close();
        return client;
    }

    //get a single client
    @GetMapping("/clients/{id_user}")
    public Client findById(@PathVariable int id_user){
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
        Client c=clientD.find(id_user);
        session.getTransaction().commit();
        session.close();
        return c;
    }

    // Delete a client
    @DeleteMapping("/clients/{id_user}")
    public ResponseEntity<?> deleteClient(@PathVariable int id_user){
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
        Client c = clientD.find(id_user);
        clientD.delete(c);
        session.getTransaction().commit();
        session.close();
        return ResponseEntity.ok().build();
    }

    //update a client (mazal static)=>mandirouhch
    @PutMapping("/clients/{id_user}")
    public Client updateclient(@PathVariable int id_user, @RequestBody Client client) {
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
        Client c = clientD.find(id_user);
        c.setPhone_number(client.getPhone_number());
        c.setUser_name(client.getUser_name());
        c.setPassword(client.getPassword());
        clientD.update(client);
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
    public Client login( @PathVariable String user_name,@PathVariable String password) throws ParseException {
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
        Query q = session.createSQLQuery("select id_user FROM users  WHERE user_name=:user_name and password=:password").setParameter("user_name",user_name).setParameter("password",password);
        Object id =  q.uniqueResult();
        Client c= clientD.find(Integer.valueOf(id+""));
        session.getTransaction().commit();
        return c;
    }


}
