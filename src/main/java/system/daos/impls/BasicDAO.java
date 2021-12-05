package system.daos.impls;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import system.daos.IBasicDAO;
import system.utils.HibernateUtils;

import java.io.Serializable;
import java.util.List;

public class BasicDAO<Entity> implements IBasicDAO<Entity> {

    // Get session factory
    SessionFactory factory = HibernateUtils.getSessionFactory();

    @Override
    public List<Entity> findAll(String className) {

        // Get session current
        Session session = factory.getCurrentSession();

        try {

            // Begin transaction
            session.getTransaction().begin();

            @SuppressWarnings("unchecked")
            Query<Entity> query = session.createQuery("select e from " + className + " e");

            // Commit into database
            // session.getTransaction().commit();
            System.out.println("Found All Successfully");

            return query.list();
        } catch (Exception e) {

            // Log error
            e.printStackTrace();

            // Rollback if error
            session.getTransaction().rollback();
        } finally {

            // Close transaction
            // factory.close();
            session.close();
        }
        return null;
    }

    @Override /* Insert entity into database */
    public Long insert(Entity entity) {

        // Get session current
        Session session = factory.getCurrentSession();

        // Entity's id after inserted successfully
        Serializable id;

        try {

            // Begin transaction
            session.getTransaction().begin();

            // Get id after insert
            id = session.save(entity);

            // Commit into database
            session.getTransaction().commit();
            System.out.println("Inserted Successfully");

            // Return Entity's id after inserted successfully
            return (Long) id;

        } catch (Exception e) {

            // Log error
            e.printStackTrace();

            // Rollback if error
            session.getTransaction().rollback();

        } finally {

            // Close transaction
            // factory.close();
            session.close();
        }
        // Return null if error
        return null;
    }

    @Override /* Update entity in database */
    public boolean update(Entity entity) {

        // Get session current
        Session session = factory.getCurrentSession();

        try {

            // Begin transaction
            session.getTransaction().begin();

            // Update entity
            session.update(entity);

            // Commit into database
            session.getTransaction().commit();
            System.out.println("Updated Successfully");

            // Return true if updated successfully
            return true;

        } catch (Exception e) {

            // Log error
            e.printStackTrace();

            // Rollback if error
            session.getTransaction().rollback();

        } finally {

            // Close transaction
            // factory.close();
            session.close();
        }
        // Return false if update error
        return false;
    }

    @Override /* Delete entity in database */
    public boolean delete(Entity entity) {

        // Get session current
        Session session = factory.getCurrentSession();

        try {

            // Begin transaction
            session.getTransaction().begin();

            // Delete entity
            session.delete(entity);

            // Commit into database
            session.getTransaction().commit();
            System.out.println("Deleted Successfully");

            // Return true if deleted successfully
            return true;

        } catch (Exception e) {

            // Log error
            e.printStackTrace();

            // Rollback if error
            session.getTransaction().rollback();

        } finally {

            // Close transaction
            // factory.close();
            session.close();
        }
        // Return false if delete error
        return false;
    }

    @Override /* Find entity by primary key */
    public Entity findOne(String className, Long id) {

        // Get session current
        Session session = factory.getCurrentSession();

        try {

            // Begin transaction
            session.getTransaction().begin();

            try {
                // Create query
                @SuppressWarnings("unchecked")
                Query<Entity> query = session.createQuery("select e from " + className + " e where e.id = " + id);

                // Return entity (single)
                return query.getSingleResult();

            } catch (Exception e) {
                return null;
            }

        } catch (Exception e) {

            // Log error
            e.printStackTrace();

            // Rollback if error
            session.getTransaction().rollback();

        } finally {

            // Close transaction
            // factory.close();
            session.close();
        }
        // Return null if error or not found
        return null;
    }

    @Override
    public List<Entity> query(String hqlQuery) {

        // Get session current
        Session session = factory.getCurrentSession();

        try {
            // Begin transaction
            session.getTransaction().begin();

            @SuppressWarnings("unchecked")
            Query<Entity> query = session.createQuery(hqlQuery);

            return query.list();

        } catch (Exception e) {

            // Log error
            e.printStackTrace();

            // Rollback if error
            session.getTransaction().rollback();
        } finally {

            // Close transaction
            // factory.close();
            session.close();
        }
        return null;
    }

    @Override
    public void ExecuteStatement(String hqlQuery) {

        // Get session current
        Session session = factory.getCurrentSession();

        try {
            // Begin transaction
            session.getTransaction().begin();

            session.createQuery(hqlQuery);

        } catch (Exception e) {

            // Log error
            e.printStackTrace();

            // Rollback if error
            session.getTransaction().rollback();
        } finally {

            // Close transaction
            // factory.close();
            session.close();
        }
    }
}
