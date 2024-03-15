package app.week06.DAO;

import app.week06.Persistence.Role;
import app.week06.Persistence.User;

public interface ISecurityDAO {
    User getVerifiedUser(String username, String password);

    User createUser(String username, String password);

    Role createRole(String role);

    User addUserRole(String username, String role);
}
