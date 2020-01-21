package dz.trash.TrashBackend.controllers;

import dz.trash.TrashBackend.DAOs.ChallengeDAO;
import dz.trash.TrashBackend.DAOs.ClientDAO;
import dz.trash.TrashBackend.DAOs.NoteDAO;
import dz.trash.TrashBackend.Model.Challenge;
import dz.trash.TrashBackend.Model.Client;
import dz.trash.TrashBackend.Model.Note;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class NoteController {
    NoteDAO noteDAO;
    // Create a new note
    @PostMapping("/note/{id_challenge}/{id_user}")
    public Note addnote(@PathVariable int id_challenge, @PathVariable int id_user, @RequestBody Note note) throws ParseException {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        noteDAO = new NoteDAO(session);
        int  idkayn=0;
        //pour que un client peut noter une seule fois
        boolean b = false;
        for (int i=1;i<=noteDAO.findAll().size();i++){
            if((noteDAO.find(i).getId_challenge()==id_challenge)&&(noteDAO.find(i).getOwner().getId_user()==id_user)){
                idkayn=i;
                b=true;  break; }
            break;
        }
        if(b==false){
            Integer id = noteDAO.findAll().size() + 1;
            Note n= new Note();
            n.setId_note(id);

            ClientDAO clientDAO= new ClientDAO(session);
            Client c1 = clientDAO.find(id_user);
            n.addowner(c1);
            n.setId_challenge(id_challenge);
            n.setNote_value(note.getNote_value());
         noteDAO.create(n);
        ChallengeDAO challengeDAO= new ChallengeDAO(session);
        Challenge challenge = challengeDAO.find(id_challenge);
        challenge.addNote(n);
        challengeDAO.update(challenge);
        session.getTransaction().commit();
        session.close();
        return n ;}
        else{
            Note n =noteDAO.find(idkayn);
            n.setNote_value(note.getNote_value());
            noteDAO.update(n);
            session.getTransaction().commit();
            session.close();
            return n;}
        }


    //get a note
    @GetMapping("/notes/{id_note}")
    public Note findById(@PathVariable int id_note){
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        noteDAO =new NoteDAO(session);
        Note note =noteDAO.find(id_note);

        session.getTransaction().commit();
        session.close();
        return note;
    }

    //get all notes
    @GetMapping("/notes")
    public List<Note> getAllnote() {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        noteDAO=new NoteDAO(session);
        List<Note> l= noteDAO.findAll();

        session.getTransaction().commit();
        session.close();
        return l;
    }

    //update note
    @PutMapping("/noteup/{id_note}")
    public Note updatenote(@PathVariable int id_note,@RequestBody Note note) {
        SessionFactory sessionFactory = new Configuration()
                .addResource("Hibernate/Challenge.hbm.xml")
                .addResource("Hibernate/User.hbm.xml")
                .addResource("Hibernate/Comment.hbm.xml")
                .addResource("Hibernate/Note.hbm.xml")
                .addResource("Hibernate/Photo.hbm.xml")
                .configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
         noteDAO=new NoteDAO(session);
        Note n = noteDAO.find(id_note);
        n.setNote_value(note.getNote_value());
        noteDAO.update(n);
        session.getTransaction().commit();
        session.close();
        return n;

    }

}
