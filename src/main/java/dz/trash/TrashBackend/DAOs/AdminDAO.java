package dz.trash.TrashBackend.DAOs;

import dz.trash.TrashBackend.Models.Admin;
import org.hibernate.Session;

import java.util.List;

public class AdminDAO extends DAO<Admin> {


    public AdminDAO(Session session) {
        super(session);
    }

    @Override
    public boolean create(Admin obj) {
        session.save(obj);

        return true;
    }

    @Override
    public boolean delete(Admin obj) {
        session.delete(obj);
        return false;
    }

    @Override
    public boolean update(Admin obj) {
        session.update(obj);
        return false;
    }

    @Override
    public Admin find(int id) {
        session.get(Admin.class,id);
        return null;
    }

    @Override
    public List<Admin> findAll() {
        return null;
    }
}
