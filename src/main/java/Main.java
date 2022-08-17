import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserDao dao = UserDaoFactory.getUserDao();
        AccountDao accountDao = AccountDaoFactory.getAccountDao();
        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        while (flag) {
            System.out.println("_______________________________");
            System.out.println("Welcome User!");
            System.out.println("Select from the options below");
            System.out.println("_______________________________");
            System.out.println("PRESS 1: Login");
            System.out.println("PRESS 2: Create Account");
            System.out.println("PRESS 3: Apply For Open Positions");
            System.out.println("PRESS 4: Exit");
            System.out.println("_______________________________");
            System.out.print("Enter Number: ");
            int input = scanner.nextInt();
            switch (input) {

                case 1: {   // 2nd menu
                    System.out.print("Enter Email: ");
                    String email = scanner.next();
                    System.out.print("Enter Password: ");
                    String password = scanner.next();
                    int identity = dao.Login(email, password);

                    if (identity == 1) {
                        boolean flag2 = true;
                        while (flag2){
                            System.out.println("_______________________________");
                            System.out.println("Select from the options below");
                            System.out.println("_______________________________");
                            System.out.println("PRESS 1: Add Customer Bank Account");
                            System.out.println("PRESS 2: View Balance");
                            System.out.println("PRESS 3: Make Withdrawal");
                            System.out.println("PRESS 4: Make Deposit");
                            System.out.println("PRESS 5: Post Transfer");
                            System.out.println("PRESS 6: Accept Transfers");
                            System.out.println("PRESS 7: Exit");
                            System.out.println("_______________________________");
                            System.out.print("Enter Number: ");
                            int input3 = scanner.nextInt();
                            switch (input3) {
                                case 1: {
                                    System.out.print("Enter Starting Balance: ");
                                    int balance = scanner.nextInt();
                                    int userId = dao.getId(email, password);
                                    accountDao.addAccount(balance, userId);
                                    break;
                                }

                                case 2: {
                                    System.out.print("Enter Id: ");
                                    int id = scanner.nextInt();
                                    int balance = accountDao.getAccountBalanceById(id);
                                    System.out.println(balance);
                                    break;
                                }

                                case 3: {
                                    System.out.print("Enter Id: ");
                                    int id = scanner.nextInt();
                                    if (accountDao.getAccountStatusById(id) == 1){
                                        System.out.println("Account Pending");
                                        break;
                                    }
                                    int balance = accountDao.getAccountBalanceById(id);
                                    System.out.println("Current Balance: " + balance);
                                    System.out.print("Enter Withdrawal Amount: ");
                                    int withdrawal = scanner.nextInt();
                                    if (withdrawal <= 0){
                                        System.out.println("please enter a positive amount");
                                        break;
                                    }
                                    int newBalance = balance - withdrawal;
                                    if (newBalance <= 0){
                                        System.out.println("not enough money in the account");
                                    }
                                    else if (newBalance >= 0){
                                        accountDao.updateAccountBalance(id, newBalance);
                                        System.out.println("New Balance: " + accountDao.getAccountBalanceById(id));
                                    }
                                    break;
                                }

                                case 4: {
                                    System.out.print("Enter Id: ");
                                    int id = scanner.nextInt();
                                    if (accountDao.getAccountStatusById(id) == 1){
                                        System.out.println("Account Pending");
                                        break;
                                    }
                                    int balance = accountDao.getAccountBalanceById(id);
                                    System.out.println("Current Balance: " + balance);
                                    System.out.print("Enter Deposit Amount: ");
                                    int deposit = scanner.nextInt();
                                    if (deposit <= 0){
                                        System.out.println("please enter a positive amount");
                                        break;
                                    }
                                    int newBalance = balance + deposit;
                                    accountDao.updateAccountBalance(id, newBalance);
                                    System.out.println("New Balance: " + accountDao.getAccountBalanceById(id));
                                    break;
                                }

                                case 5: {
                                    System.out.print("Enter Id of source account: ");
                                    int id = scanner.nextInt();
                                    System.out.print("Enter Id of account you are transferring to: ");
                                    int id2 = scanner.nextInt();
                                    System.out.print("Enter amount: ");
                                    int amount = scanner.nextInt();
                                    if( amount <= 0){
                                        System.out.println("amount must be positive");
                                        break;
                                    }
                                    int balance = accountDao.getAccountBalanceById(id);
                                    int newBalance = balance - amount;
                                    if (newBalance <= 0){
                                        System.out.println("not enough money in the account");
                                        break;
                                    }
                                    accountDao.updateAccountBalance(id, newBalance);
                                    accountDao.updateAccountTransfer(id2, amount);
                                    break;
                                }

                                case 6: {
                                    System.out.print("Enter Id of source account: ");
                                    int id = scanner.nextInt();
                                    int transfer = accountDao.getAccountTransferById(id);
                                    System.out.println("You have been offered " + transfer);
                                    System.out.println("PRESS 1: Approve");
                                    System.out.println("PRESS 2: Reject");
                                    int decision = scanner.nextInt();
                                    if (decision == 1){
                                        accountDao.updateAccountTransfer(id, 0);
                                        accountDao.updateAccountBalance(id, transfer);
                                        System.out.println("New Balance: " + accountDao.getAccountBalanceById(id));
                                    }
                                    else if (decision == 2){
                                        accountDao.updateAccountTransfer(id, 0);
                                        System.out.println("Transfer Cancelled");
                                    }
                                    break;
                                }

                                case 7: {
                                    flag2 = false;
                                    break;
                                }
                            }
                        }
                    }

                    else if (identity == 2){
                        //Employee Menu
                        boolean flag3 = true;
                        while (flag3) {
                            System.out.println("_______________________________");
                            System.out.println("Select from the options below");
                            System.out.println("_______________________________");
                            System.out.println("PRESS 1: View Customer Accounts");
                            System.out.println("PRESS 2: Approve or Reject Customer Accounts");
                            System.out.println("PRESS 3: View All Customer Transactions");
                            System.out.println("PRESS 4: Exit");
                            System.out.println("_______________________________");
                            System.out.print("Enter Number: ");
                            int input4 = scanner.nextInt();
                            switch (input4) {
                                case 1: {
                                    // display account # column by user
                                    System.out.print("Enter Id: ");
                                    int Id = scanner.nextInt();
                                    System.out.println(accountDao.getAccounts(Id));
                                    break;
                                }

                                case 2: {
                                    // delete account
                                    System.out.println(accountDao.getPendingAccounts());
                                    System.out.print("Enter Id: ");
                                    int Id = scanner.nextInt();
                                    System.out.println("PRESS 1: Approve");
                                    System.out.println("PRESS 2: Reject");
                                    int decision = scanner.nextInt();
                                    if (decision == 1){
                                        accountDao.updateAccountStatus(0);
                                        System.out.println("Account confirmed");
                                    }
                                    else if (decision == 2){
                                        accountDao.deleteAccount(Id);
                                        System.out.println("Account Deleted");
                                    }
                                    break;
                                }

                                case 3: {
                                    // open log
                                    break;
                                }

                                case 4: {
                                    flag3 = false;
                                    break;
                                }
                            }
                        }
                    }

                    else {
                        System.out.println("Login is false");
                    }
                    break;
                }

                case 2: {   // add & validate if the user wants to be a customer or employee
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Email: ");
                    String email = scanner.next();
                    System.out.print("Enter Password: ");
                    String password = scanner.next();
                    User user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    user.setPassword(password);
                    dao.addUser(user);
                    break;
                }

                case 3: { // update
                    System.out.print("Enter Email: ");
                    String email = scanner.next();
                    System.out.print("Enter Password: ");
                    String password = scanner.next();
                    int identity = dao.Login(email, password);

                    if (identity == 1) {
                        dao.upgradeUser(email, password);
                    }

                    if (identity == 2) {
                        System.out.println("No promotions available");
                    }

//                    System.out.print("Enter Id: ");
//                    int id = scanner.nextInt();
//                    System.out.print("Enter Name: ");
//                    String name = scanner.next();
//                    System.out.print("Enter Email: ");
//                    String email = scanner.next();
//                    System.out.print("Enter Password: ");
//                    String password = scanner.next();
//                    User user = new User(id, name, email, password);
//                    dao.updateUser(user);
                    else if (identity == 3) {
                        System.out.println("Wrong credentials");
                    }
                    break;
                }
//                case 3: {  // delete
//                    System.out.print("Enter Id: ");
//                    int id = scanner.nextInt();
//                    dao.deleteUser(id);
//                    break;
//                }
//                case 4: { // get all
//                    List<User> users = dao.getUsers();
//                    for(User user: users){
//                        System.out.println(user);
//                    }
//                    break;
//                }
//                case 5: { // get by id
//                    System.out.print("Enter Id: ");
//                    int id = scanner.nextInt();
//                    User user = dao.getUserById(id);
//                    System.out.println(user);
//                    break;
//                }
                case 4: {  //exit
                    System.out.println("Thank you");
                    System.out.println("Exiting...");
                    flag = false;
                    break;
                }

                default: System.out.println("Choose between 1 - 6");
            }
        }
    }
}

