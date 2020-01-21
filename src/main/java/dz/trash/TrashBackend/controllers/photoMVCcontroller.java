package dz.trash.TrashBackend.controllers;

import dz.trash.TrashBackend.Model.Photo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class photoMVCcontroller {

    PhotoController service;
     @GetMapping("/home")
    public String home()
    {
        //List<Photo> list = service.getAllphoto();
       // model.addAttribute("photos", list);
        return "home";
    }
    @GetMapping("/")
    public String hello(Model model)
    {
        List<Photo> list = service.getAllphoto();
         model.addAttribute("photos", list);
        return "hel";
    }
    @RequestMapping("/hello")
    public String hello(Map<String, Object> model) {
        model.put("message", "HowToDoInJava Reader !!");
        return "hello";
    }
    @RequestMapping(value = "/home.html", method = RequestMethod.POST)
    public String newEmployee(Photo photo) {

       new PhotoController().photoDAO.create(photo);
        return ("redirect:/home.html");
    }
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public ModelAndView addphoto() {

        Photo emp = new Photo();
        return new ModelAndView("newEmployee", "form", emp);
    }

}
