package app.week06.DAO;

import app.week06.Persistence.HibernateConfig;
import app.week06.Persistence.Role;
import app.week06.Persistence.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;

public class UserDAO implements ISecurityDAO {

    private EntityManagerFactory emf;

    public UserDAO(EntityManagerFactory _emf) {
        this.emf = _emf;
    }

    @Override
    public User getVerifiedUser(String username, String password) throws EntityNotFoundException {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
            query.setParameter("id", username);

            User user = em.find(User.class, username);

            if (user == null) {
                throw new EntityNotFoundException("No user found with username: " + username);
            }

            if (!user.verifyPassword(password)) {
                throw new EntityNotFoundException("Wrong password");
            }
            return user;
        }
    }


    @Override
    public User createUser(String username, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            User createdUser = new User(username, password);
            em.persist(createdUser);
            em.getTransaction().commit();
            return createdUser;
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
        UserDAO dao = new UserDAO(emf);
        //User user = dao.createUser("user", "1234");
        //System.out.println(user.getUsername());
        try {
            User user = dao.verifyUser("user", "1234");
            System.out.println(user.getUsername());
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Role createRole(String role) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Role createdRole = new Role();
            createdRole.setName(role);
            em.persist(createdRole);
            em.getTransaction().commit();
            return createdRole;
        } finally {
            em.close();
        }
    }

    @Override
    public User addUserRole(String username, String role) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            User foundUser = em.find(User.class, username);
            Role foundRole = em.find(Role.class, role);
            foundUser.addRole(foundRole);
            em.merge(foundUser);
            em.getTransaction().commit();
            return foundUser;
        } finally {
            em.close();
        }
    }
    public User find(String username) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
        User user;
        try (EntityManager em = emf.createEntityManager()) {
            user = em.find(User.class, username);
        }
        return user;
    }


    public User verifyUser(String username, String password) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, username);
        if (user == null) throw new EntityNotFoundException("No user found");
        if (!user.verifyPassword(password)) throw new EntityNotFoundException("Wrong password");
        return user;
    }

    public int update(User user) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            User updatedUser = em.merge(user);
            em.getTransaction().commit();
            return updatedUser != null ? 1 : 0;
        }
    }

    public void assignRoleToUser(String username, String roleName) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, username);
            if (user == null) {
                throw new EntityNotFoundException("User not found");
            }
            Role role = em.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class)
                    .setParameter("name", roleName)
                    .getSingleResult();
            if (role == null) {
                role = new Role(roleName);
                em.persist(role);
            }
            user.addRole(role);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
