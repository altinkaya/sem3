package app.week06.DTO;


import java.util.Set;

import app.week06.Persistence.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class UserDTO {

    private String username;
    private String password;

    private Set<String> roles;

    public UserDTO(String username, String[] roles) {
        this.username = username;
        this.roles = Set.of(roles);
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.roles = user.getRolesAsStrings();
    }

    public static List<UserDTO> toUserDTOList(List<User> users) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : users) {
            userDTOList.add(new UserDTO(user.getUsername(), user.getRolesAsStrings().toArray(new String[0])));
        }
        return userDTOList;

    }

    public String getRolesAsStrings() {
        if (roles.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();

        for (String role : roles) {
            sb.append(role).append(",");
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }
}
