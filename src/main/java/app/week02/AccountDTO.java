package app.week02;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AccountDTO {
    private String fullName;
    private String city;
    private int zipCode;
    private boolean isActive;

    public static void printAccounts(List<AccountDTO> accounts) {
        for (AccountDTO account : accounts) {
            System.out.println("Full Name: " + account.getFullName());
            System.out.println("City: " + account.getCity());
            System.out.println("Zip Code: " + account.getZipCode());
            System.out.println("Is Active: " + (account.isActive() ? "Yes" : "No"));
            System.out.println("------------------------");
        }
    }

    public static void main(String[] args) {
        try {
            List<Account> accounts = Account.readAccountsFromFile("src/main/java/app/week02/account.json");
            List<AccountDTO> accountDTOs = convertToDTO(accounts);
            printAccounts(accountDTOs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<AccountDTO> convertToDTO(List<Account> accounts) {
        List<AccountDTO> accountDTOs = new ArrayList<>();
        for (Account account : accounts) {
            AccountDTO dto = new AccountDTO();
            dto.setFullName(account.getFirstName() + " " + account.getLastName());
            dto.setCity(account.getAddress().getCity());
            dto.setZipCode(account.getAddress().getZipCode());
            dto.setActive(account.getAccount().isActive());
            accountDTOs.add(dto);
        }
        return accountDTOs;
    }
}
