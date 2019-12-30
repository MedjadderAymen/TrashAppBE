package dz.trash.TrashBackend;

import dz.trash.TrashBackend.DAOs.PhotoDAO;
import dz.trash.TrashBackend.Models.Photo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class TrashbackendApplication {

	public static void main(String[] args) {

	SpringApplication.run(TrashbackendApplication.class, args);
		SessionFactory sessionFactory = new Configuration()
				.addResource("Hibernate/Challenge.hbm.xml")
				.addResource("Hibernate/User.hbm.xml")
				.addResource("Hibernate/Comment.hbm.xml")
				.addResource("Hibernate/Note.hbm.xml")
				.addResource("Hibernate/Photo.hbm.xml")
				.configure("hibernate.cfg.xml").buildSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Date date=new Date();
		Photo p=new Photo(1,"gg/gg.png",date);
        PhotoDAO photoDAO=new PhotoDAO(session);
        photoDAO.create(p);
		session.getTransaction().commit();
		session.close();
	}

}
