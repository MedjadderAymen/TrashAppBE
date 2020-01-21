package dz.trash.TrashBackend.DAOs;

import dz.trash.TrashBackend.Model.Photo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class PhotoDAO extends DAO<Photo> {
    @Autowired
    private JdbcTemplateAutoConfiguration jdbcTemplateAutoConfiguration;
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
        Photo p=(Photo) session.get(Photo.class,id);
        return p;
    }

    @Override
    public List<Photo> findAll() {
        Criteria cr=session.createCriteria(Photo.class);

        List<Photo> result=(List<Photo>)cr.list();

        return result;
    }
}
