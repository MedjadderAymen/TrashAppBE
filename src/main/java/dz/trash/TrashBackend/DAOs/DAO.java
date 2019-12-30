package dz.trash.TrashBackend.DAOs;

/**
 *
 * @author BDA2
 */

import org.hibernate.Session;

import java.util.List;

public abstract class DAO<T> {
  protected Session session = null;
   
  public DAO(Session session){
    this.session = session;
  }
   
  public abstract boolean create(T obj);
  public abstract boolean delete(T obj);
  public abstract boolean update(T obj);
  public abstract T find(int id);
  public abstract List<T> findAll();
}
