package dz.trash.TrashBackend.DAOs;

import dz.trash.TrashBackend.Models.Comment;
import dz.trash.TrashBackend.Models.Photo;
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
        Comment c= (Comment)session.get(Comment.class,id);
        return c;
    }

    @Override
    public List<Comment> findAll() {
        Criteria cr=session.createCriteria(Comment.class);
        List<Comment> result=(List<Comment>)cr.list();
        return result;
    }
}
