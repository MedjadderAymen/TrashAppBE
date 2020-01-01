package dz.trash.TrashBackend.DAOs;

import dz.trash.TrashBackend.Models.Photo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class PhotoDAO extends DAO<Photo> {
    public PhotoDAO(Session session) {
        super(session);
    }

    @Override
    public boolean create(Photo obj) {
        session.save(obj);
        return false;
    }

    @Override
    public boolean delete(Photo obj) {
        session.delete(obj);
        return false;
    }

    @Override
    public boolean update(Photo obj) {
        session.update(obj);
        return false;
    }

    @Override
    public Photo find(int id) {
        Photo p=(Photo)session.get(Photo.class,id);
        return p;
    }

    @Override
    public List<Photo> findAll() {
        Criteria cr=session.createCriteria(Photo.class);

        List<Photo> result=(List<Photo>)cr.list();

        return result;
    }
}
