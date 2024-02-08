package app.week02;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Account {
    private String firstName;
    private String lastName;
    private String birthDate;
    private Address address;
    private AccountDetails account;

    @Getter
    @Setter
    public static class Address {
        private String street;
        private String city;
        private int zipCode;
    }

    @Getter
    @Setter
    public static class AccountDetails {
        private String id;
        private String balance;
        @JsonProperty("isActive")
        private boolean active;
    }

    public static List<Account> readAccountsFromFile(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(new File(filePath), Account[].class));
    }
}

