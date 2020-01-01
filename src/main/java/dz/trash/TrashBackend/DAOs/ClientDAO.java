package dz.trash.TrashBackend.DAOs;

import dz.trash.TrashBackend.Models.Client;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDAO extends DAO<Client>{
    public ClientDAO(Session session) {
        super(session);
    }

    @Override
    public boolean create(Client obj) {
        session.save(obj);
        return false;
    }

    @Override
    public boolean delete(Client obj) {
        session.delete(obj);
        return false;
    }

    @Override
    public boolean update(Client obj) {
        session.update(obj);
        return false;
    }

    @Override
    public Client find(int id) {
        Client c= (Client) session.get(Client.class,id);
        return  c;
    }

    @Override
    public List<Client> findAll() {
        List<Client> list= session.createQuery(String.format("from Client")).list();
        return list;
    }
}
