package dz.trash.TrashBackend.DAOs;

import dz.trash.TrashBackend.Models.Note;
import dz.trash.TrashBackend.Models.Photo;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

public class NoteDAO extends DAO<Note> {
    public NoteDAO(Session session) {
        super(session);
    }

    @Override
    public boolean create(Note obj) {
        session.save(obj);
        return false;
    }

    @Override
    public boolean delete(Note obj) {
        session.delete(obj);
        return false;
    }

    @Override
    public boolean update(Note obj) {
        session.update(obj);
        return false;
    }

    @Override
    public Note find(int id) {
        Note n= (Note) session.get(Note.class,id);
        return n;
    }

    @Override
    public List<Note> findAll() {
        Criteria cr=session.createCriteria(Note.class);

        List<Note> result=(List<Note>)cr.list();

        return result;
    }
}
