package dz.trash.TrashBackend.DAOs;

import dz.trash.TrashBackend.Models.Comment;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CommentDAO extends DAO<Comment>{

    public CommentDAO(Session session) {
        super(session);
    }

    @Override
    public boolean create(Comment obj) {
        session.save(obj);
        return false;
    }

    @Override
    public boolean delete(Comment obj) {
        session.delete(obj);
        return false;
    }

    @Override
    public boolean update(Comment obj) {
        session.update(obj);
        return false;
    }

    @Override
    public Comment find(int id) {
        session.get(Comment.class,id);
        return null;
    }

    @Override
    public List<Comment> findAll() {

        return null;
    }
}
