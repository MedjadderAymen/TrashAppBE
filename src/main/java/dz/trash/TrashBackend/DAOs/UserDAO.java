package dz.trash.TrashBackend.DAOs;

import dz.trash.TrashBackend.Models.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.Query;
import java.util.List;

public class UserDAO extends DAO<User> {
    public UserDAO(Session session) {
        super(session);
    }

    @Override
    public boolean create(User obj) {
        session.save(obj);
        return false;
    }

    @Override
    public boolean delete(User obj) {
        session.delete(obj);
        return false;
    }

    @Override
    public boolean update(User obj) {
        session.update(obj);
        return false;
    }

    @Override
    public User find(int id) {
        session.get(User.class,id);
        return null;
    }

    @Override
    public List<User> findAll() {

        Criteria cr=session.createCriteria(User.class);
        cr.add(Restrictions.gt("TYPE_USER","Client"));

        List<User> result=(List<User>)cr.list();

        return result;
    }
}
