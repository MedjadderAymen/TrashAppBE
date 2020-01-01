package dz.trash.TrashBackend.controllers;

import dz.trash.TrashBackend.DAOs.AdminDAO;
import dz.trash.TrashBackend.Models.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@RestController
public class AdminController {
    AdminDAO adminD;

    // Create a new admin
    @GetMapping("/admin")
    @ResponseBody
    public String addadmin(){
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
        Date date = new Date();
        Admin a1 = new Admin(6, "zertal","youcef","youc","123456", date,"youcef@gmail.com");
        adminD.create(a1);
        session.getTransaction().commit();
        session.close();
        return"Admin added !! ";

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
    public Admin updateadmin(@PathVariable(value = "id") int id,@Valid @RequestBody Admin adminDetails) {
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
        a.setEmail(adminDetails.getEmail());
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

