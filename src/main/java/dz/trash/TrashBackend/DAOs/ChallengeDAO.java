package dz.trash.TrashBackend.DAOs;

import dz.trash.TrashBackend.Model.Challenge;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class ChallengeDAO extends DAO<Challenge> {

    public ChallengeDAO(Session session) {
        super(session);
    }

    @Override
    public boolean create(Challenge obj) {
        session.save(obj);
        return false;
    }

    @Override
    public boolean delete(Challenge obj) {
        session.delete(obj);
        return false;
    }
    public boolean up(int id ) {
        Query q = session.createSQLQuery("update Challenge set state=0 where id_challenge =:id").setParameter("id", id);
        int r = q.executeUpdate();
        if (r == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Challenge obj) {
        session.saveOrUpdate(obj);
        return false;
        }


    @Override
    public Challenge find(int id) {
        Challenge c= (Challenge) session.get(Challenge.class,id);
        return c;
    }

    @Override
    public List<Challenge> findAll() {
        List<Challenge> list= session.createQuery(String.format("from Challenge")).list();
        return list;
    }
}
