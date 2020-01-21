package dz.trash.TrashBackend.controllers;
import dz.trash.TrashBackend.DAOs.ChallengeDAO;
import dz.trash.TrashBackend.Model.*;
import dz.trash.TrashBackend.exception.RecordNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private static List<Challenge> challenges = new ArrayList<Challenge>();
    private static List<Photo> photos = new ArrayList<Photo>();


    static {
        ChallengeController challengeController;
        challenges.addAll(new ChallengeController().getAllchallengesAdmin());
        // photos.add(new Photo(1,"gbhknj", d));
        //photos.add(new Photo(2,"gjhkhbknj", d));
        PhotoController photoController;
        photos.addAll(new PhotoController().getAllphoto());
    }

    // Injectez (inject) via application.properties.
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("message", message);
        return "index";
    }


    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String logo(Model model) {
        UserForm userForm=new UserForm();
        model.addAttribute("userForm",userForm);
        return "login";
    }


    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String tester(Model model ,@ModelAttribute("userForm") UserForm userForm) {
        String user_name= userForm.getUser_name();
        String  password = userForm.getPassword();
        UserForm name= new UserForm();
        name.setUser_name("mmm");
        name.setPassword("mmm");


        if (user_name.equals("manel")  &&  password.equals("aymen") ) {
            return "redirect:/table";
        }
        else{
            model.addAttribute("errorMessage", errorMessage);
            return "login";
        }

    }


    @RequestMapping(value = {"/challengeList"}, method = RequestMethod.GET)
    public String challengeList(Model model) {

        model.addAttribute("challenges", challenges);

        return "challengeList";
    }


    @RequestMapping(path = "/delete/{id}")
    public String updateAdmin(Model model, @PathVariable("id") int id) {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml").addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml").addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml").configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ChallengeDAO challengeDAO=new ChallengeDAO(session);
        challengeDAO.up(id);
        Challenge c= challengeDAO.find(id);
        challengeDAO.update(c);
        session.getTransaction().commit();
      // model.addAttribute("challenge", new ChallengeController().getAllchallengesAdmin());
        return "redirect:/table";
    }





    //**************************************************************************************
@RequestMapping(value = {"/table"}, method = RequestMethod.GET)
public String photoList(Model model) {
    model.addAttribute("challenges", new ChallengeController().getAllchallengesAdmin());

    return "table";
}


    @RequestMapping(value = {"/photoList"}, method = RequestMethod.GET)
    public String tablelist(Model model) {

        model.addAttribute("photos", photos);

        return "personList";
    }

}



