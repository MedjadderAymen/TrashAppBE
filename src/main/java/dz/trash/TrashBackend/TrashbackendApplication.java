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

	}

}
