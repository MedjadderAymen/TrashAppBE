package dz.trash.TrashBackend.DAOs;

import dz.trash.TrashBackend.Models.Note;
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
        session.get(Note.class,id);
        return null;
    }

    @Override
    public List<Note> findAll() {
        return null;
    }
}
