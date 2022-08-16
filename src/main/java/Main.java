import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserDao dao = UserDaoFactory.getUserDao();
        Scanner scanner = new Scanner(System.in);


        boolean flag = true;
        while (flag) {
            System.out.println("_______________________________");
            System.out.println("Welcome User!");
            System.out.println("Select from the options below");
            System.out.println("_______________________________");
            System.out.println("PRESS 1: Login");
            System.out.println("PRESS 2: Create Account");
            System.out.println("PRESS 3: Reset Credentials");
//            System.out.println("PRESS 4: Get All Employee");
//            System.out.println("PRESS 5: Get Employee By Id");
            System.out.println("PRESS 6: Exit");
            System.out.println("_______________________________");
            int input = scanner.nextInt();
            switch (input) {

                case 1: {   // 2nd menu

                    System.out.println("_______________________________");
                    System.out.println("Select from the options below");
                    System.out.println("_______________________________");
                    System.out.println("PRESS 1: Login with Customer Credentials");
                    System.out.println("PRESS 2: Register For a Customer Account");
                    System.out.println("PRESS 3: Login with Employee Credentials");
                    System.out.println("PRESS 4: Apply for an Employee Account");
                    System.out.println("_______________________________");
                    int input2 = scanner.nextInt();

                    switch (input2) {
                        case 1: {
                            //Login menu

                                System.out.println("_______________________________");
                                System.out.println("Select from the options below");
                                System.out.println("_______________________________");
                                System.out.println("PRESS 1: Add Customer Bank Account");
                                System.out.println("PRESS 2: Make Withdrawal");
                                System.out.println("PRESS 3: Make Deposit");
                                System.out.println("PRESS 4: Transfer funds");
                                System.out.println("_______________________________");
                                int input3 = scanner.nextInt();
                            switch (input3) {
                                case 1: {
                                    // add account
                                    break;
                                }

                                case 2: {
                                    // negative balance
                                    break;
                                }

                                case 3: {
                                    // positive balance
                                    break;
                                }

                                case 4: {
                                    // negative from 1, positive to the other but must be able to reference another person's account
                                    break;
                                }
                            }
                            break;
                        }

                        case 2: {
                            //Add Customer Account
                            break;
                        }

                        case 3: {
                            //Employee Menu
                            System.out.println("_______________________________");
                            System.out.println("Select from the options below");
                            System.out.println("_______________________________");
                            System.out.println("PRESS 1: View Customer Accounts");
                            System.out.println("PRESS 2: Approve or Reject Customer Accounts");
                            System.out.println("PRESS 3: View All Customer Transactions");
                            System.out.println("_______________________________");
                            int input4 = scanner.nextInt();
                            switch (input4) {
                                case 1: {
                                    // display account # column by user
                                    break;
                                }

                                case 2: {
                                    // delete account
                                    break;
                                }

                                case 3: {
                                    // open log
                                    break;
                                }

                            break;
                        }

                        case 4: {
                            //Add Employee Account
                            break;
                        }
                    }
                    break;
                }
                case 2: {   // add
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Email: ");
                    String email = scanner.next();
                    User user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    dao.addUser(user);
                    break;
                }
                case 3: { // update
                    System.out.print("Enter Id: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Email: ");
                    String email = scanner.next();
                    User user = new User(id, name, email);
                    dao.updateUser(user);
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
                case 6: {  //exit
                    System.out.println("Thank you");
                    System.out.println("Exiting...");
                    flag = false;
                    break;
                }
                default:
                    System.out.println("Choose between 1 - 6");
            }
        }
    }
}
