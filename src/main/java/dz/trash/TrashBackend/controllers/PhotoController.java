package dz.trash.TrashBackend.controllers;

import dz.trash.TrashBackend.DAOs.ChallengeDAO;
import dz.trash.TrashBackend.DAOs.PhotoDAO;
import dz.trash.TrashBackend.Models.Challenge;
import dz.trash.TrashBackend.Models.Photo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class PhotoController {

    PhotoDAO photoDAO;

    // Create a new photo
    @GetMapping("/photo")
    @ResponseBody
    public String addphoto(){
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        photoDAO=new PhotoDAO(session);
        Date date =new Date();
        Photo p=new Photo(6,"photo.png",date);
        photoDAO.create(p);

        session.getTransaction().commit();
        session.close();
        return"photo added !! ";
    }

    //get a single photo
    @GetMapping("/photos/{id}")
    public Photo findById(@PathVariable int id){
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        photoDAO=new PhotoDAO(session);
        Photo p =photoDAO.find(id);

        session.getTransaction().commit();
        session.close();
        return p;
    }

    // Delete a photo
    @DeleteMapping("/photosdel/{id}")
    public ResponseEntity<?> deletephoto(@PathVariable(value = "id") int id){
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        photoDAO = new PhotoDAO(session);
        Photo p = photoDAO.find(id);
        photoDAO.delete(p);

        session.getTransaction().commit();
        session.close();
        return ResponseEntity.ok().build();
    }

    //update a photo
    @RequestMapping(value = "/photosup/{id}", method = RequestMethod.PUT)
    //@GetMapping("/photosup/{id}")
    public Photo updatephoto(@PathVariable(value = "id") int id) {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        photoDAO=new PhotoDAO(session);
        Photo p = photoDAO.find(id);
        Date newDate=new Date();
        p.setPath("new path");
        p.setCreation_date(newDate);
        photoDAO.update(p);

        session.getTransaction().commit();
        session.close();
        return p;

    }

    //get all photo
    @GetMapping("/photos")
    public List<Photo> getAllphoto() {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        photoDAO=new PhotoDAO(session);
        List<Photo> l= photoDAO.findAll();

        session.getTransaction().commit();
        session.close();
        return l;
    }

}
