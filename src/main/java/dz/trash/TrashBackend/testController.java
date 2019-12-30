package dz.trash.TrashBackend;

import dz.trash.TrashBackend.DAOs.PhotoDAO;
import dz.trash.TrashBackend.Models.Photo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class testController {
    @GetMapping("/hello")
    @ResponseBody
    public String test(){

        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
      PhotoDAO photoDAO=new PhotoDAO(session);
      Date date =new Date();
      Photo p=new Photo(2,"internal/hh.png",date);
      photoDAO.create(p);
      session.getTransaction().commit();
      session.close();
      //bonne nuit
      return "helloyeeew";
    }
}
