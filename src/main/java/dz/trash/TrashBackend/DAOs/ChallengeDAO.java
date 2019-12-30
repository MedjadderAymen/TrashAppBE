package dz.trash.TrashBackend.DAOs;

import dz.trash.TrashBackend.Models.Challenge;
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

    @Override
    public boolean update(Challenge obj) {
        session.update(obj);
        return false;
    }

    @Override
    public Challenge find(int id) {
        session.get(Challenge.class,id);
        return null;
    }

    @Override
    public List<Challenge> findAll() {
        return null;
    }
}
