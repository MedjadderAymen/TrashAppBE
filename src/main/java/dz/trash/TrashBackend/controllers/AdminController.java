package dz.trash.TrashBackend.controllers;

import dz.trash.TrashBackend.DAOs.AdminDAO;
import dz.trash.TrashBackend.Model.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;


@RestController
public class AdminController {
    AdminDAO adminD;

    // Create a new admin
    @PostMapping("/admin")
    public Admin addadmin(@RequestBody Admin admin) throws ParseException {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        adminD = new AdminDAO(session);
        adminD.create(admin);
        session.getTransaction().commit();
        session.close();
        return admin;
    }

    //get a single admin
    @GetMapping("/admins/{id}")
    public Admin findById(@PathVariable int id){
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        adminD = new AdminDAO(session);
        Admin a =adminD.find(id);
        session.getTransaction().commit();
        session.close();
        return a;
    }

    // Delete admin
    @DeleteMapping("/admins/{id}")
    public ResponseEntity<?> deleteaAdmin(@PathVariable(value = "id") int id){
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        adminD = new AdminDAO(session);
        Admin a = adminD.find(id);
        adminD.delete(a);
        session.getTransaction().commit();
        session.close();
        return ResponseEntity.ok().build();
    }

    //update admin
    @PutMapping("/admins/{id}")
    public Admin updateadmin(@PathVariable int id,@Valid @RequestBody Admin admin) {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        adminD = new AdminDAO(session);
        Admin a = adminD.find(id);
        a.setEmail(admin.getEmail());
        adminD.update(a);
        session.getTransaction().commit();
        session.close();
        return a;

    }

    //get all admin
    @GetMapping("/admins")
    public List<Admin> getAlladmin() {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        adminD = new AdminDAO(session);
        List<Admin> l = adminD.findAll();
        session.getTransaction().commit();
        session.close();
        return l;
    }

}

