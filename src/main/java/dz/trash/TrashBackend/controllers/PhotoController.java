package dz.trash.TrashBackend.controllers;

import dz.trash.TrashBackend.DAOs.ChallengeDAO;
import dz.trash.TrashBackend.DAOs.PhotoDAO;
import dz.trash.TrashBackend.Model.Challenge;
import dz.trash.TrashBackend.Model.Photo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhotoController {

    PhotoDAO photoDAO;
ChallengeDAO challengeDAO;
    // Create a new photo
    @PostMapping("/photo/{id_challenge}")
    public Photo addphoto(@PathVariable int id_challenge, @RequestBody Photo photo){
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
        challengeDAO= new ChallengeDAO(session);
        Integer id = photoDAO.findAll().size() + 1;
        photo.setId_photo(id);
        photoDAO.create(photo);
        Challenge challenge=challengeDAO.find(id_challenge);
        challenge.addPhoto(photo);
        challengeDAO.update(challenge);
        session.getTransaction().commit();
        session.close();
        return photo;
        //return"photo added !! ";
    }

    //get a single photo
    @GetMapping("/photos/{id_photo}")
    public Photo findById(@PathVariable int id_photo){
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
        Photo photo =photoDAO.find(id_photo);
        session.getTransaction().commit();
        session.close();
        return photo;
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
    @PutMapping("/photosup/{id}")
    public Photo updatephoto(@PathVariable int id,@RequestBody Photo photo) {
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
        p.setPath(photo.getPath());
        p.setCreation_date(photo.getCreation_date());
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


    //***********************************************************************

}
