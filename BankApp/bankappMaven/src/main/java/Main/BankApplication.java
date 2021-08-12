package Main;

import Accounts.AccountInfo;
import Accounts.AccountInfoDAO;
import Accounts.AccountInfoDAOimpl;
import Customer.Customer;
import Customer.CustomerDAO;
import Customer.CustomerDAOimpl;
import java.sql.Date;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BankApplication {

    public static Scanner sc = new Scanner(System.in);
    public static boolean flag = true;
    public static int menuSelection;
    public static CustomerDAO customer = new CustomerDAOimpl();
    public static AccountInfoDAO ac = new AccountInfoDAOimpl();


    public static void main(String[] args) throws SQLException {


        System.out.println("***********************************************************************************");
        System.out.println("|                WELCOME TO CONSOLE BASED BANK APPPLICATION                       |");
        System.out.println("***********************************************************************************");
        try {
            while (flag) {
                System.out.println("_________________________________________________________________________________");
                System.out.println("   Select an option from below to continue :                                     ");
                System.out.println("     1.Enroll Now , Get Started! We are excited to sign you up!                  ");
                System.out.println("     2.Login as Customer                                                         ");
                System.out.println("     3.Login as Bank Employee                                                    ");
                System.out.println("     4.Exit from Application                                                     ");
                System.out.println("_________________________________________________________________________________");
                System.out.print(">>>Enter your option here : ");

                menuSelection = sc.nextInt();
                switch (menuSelection) {
                    case 1:
                        //Enroll for mobile banking
                        System.out.println("____________________________________________________________");
                        System.out.println("We are ready to enroll you today. Get started by telling us about you... ");
                        System.out.print("Enter Last Name: ");
                        String lastName = sc.next();
                        System.out.print("Enter SSN: ");
                        String ssn = sc.next();
                        // getmobilebanking - value will either return ['Y' / 'N' - if account exist] or [null - if account does not exist]
                        int onlineAcc = customer.getmobile_banking(lastName, ssn);
                        //System.out.println(onlineAcc);
                        if (onlineAcc == 1) {
                            // as account already exist , suggests customer to login and continue
                            OnlineAccountExistAlready(onlineAcc);
                        }else if (onlineAcc == 2) {
                            // bank account exist but customer has not opted for mobile_banking,
                            // so setting up a password to register existing customer for mobile banking
                            settingPassword(onlineAcc, ssn);
                        }else if (onlineAcc == 3) {
                            // Neither online nor bank account exist, hence start creating one
                            NewBankAccount(lastName, ssn);
                        }
                        break;

                    case 2:
                        System.out.println("|_________________________________________________________________________|");
                        System.out.println("\nWelcome to Customer Login Page!! ");
                        System.out.print("Enter e-mail: ");
                        String email = sc.next();
                        System.out.print("Enter password: ");
                        String password = sc.next();
                        ac.getAccount(email, password);
                        // if login is success , get customer's last name - else return null
                        String lname = customer.ifLoginSuccessGetLastName(email, password);
                        if (lname == null) {
                            // validation failed...
                            System.out.println("Hmm. The information you provided doesn't match our records. Try again?");
                        } else {
                            // customer has successfully loggedin
                            System.out.println("-------------------------------------");
                            // displaying last name from DB for provided email and pswd
                            System.out.println("   ****  Welcome " + lname + "   ****");
                            // logged in loop will be true till customer chooses to logout
                            Boolean isLoggedIn = true;
                            System.out.println("You are successfully logged in..");
                            System.out.println();
                            while (isLoggedIn) {
                                System.out.println("-------------------------------------");
                                System.out.println("  Select an option from below to continue:  ");
                                System.out.println("     1.Print Balance");
                                System.out.println("     2.Create a new bank account");
                                System.out.println("     3.Withdraw Money");
                                System.out.println("     4.Deposit Money");
                                System.out.println("     5.Transfer Money");
                                System.out.println("     6.Accept Transfer");
                                System.out.println("     7.Logout from Application");
                                System.out.println("______________________________________");
                                System.out.print("Enter your option here : ");
                                int input = sc.nextInt();
                                switch (input) {
                                    case 1:
                                        // capturing email from previous entry as cust is still in logged in state
                                        printCustBalance(email);
                                        break;

                                    case 2:
                                        System.out.println("We are ready to enroll you today. Get started by telling us about you... ");
                                        // scanning last name and ssn so we can re-use NewBankAccount(lastname,ssn) method from case 1
                                        System.out.print("Enter Last Name: ");
                                        String lName = sc.next();
                                        System.out.print("Enter SSN: ");
                                        String ssn1 = sc.next();
                                        // using same method as Case 1 when new cust signs ups to avoid repitition
                                        NewBankAccount(lname, ssn1);
                                        break;

                                    case 3:
                                        //withdraw method
                                        custWithdraw(email);
                                        break;
                                    case 4:
                                        //deposit method
                                        custDeposit(email);
                                        break;
                                    case 5:
                                        // transfer
                                        transferAmnt(email);
                                        break;
                                    case 6:
                                        // accept post trtansfer
                                        acceptTransfer(email);
                                    case 7:
                                        // this will break the loop of login window
                                        System.out.println("Thank You for choosing Bank App.Visit Again!");
                                        isLoggedIn = false;
                                        break;
                                    default:
                                        System.out.println("Choose between 1 - 6");
                                        break;
                                }
                            }
                        }
                    case 3:
                        loginAsEmployee();
                        break;
                    case 4:
                        System.out.println("|_________________________________________________________________________________|");
                        System.out.println("Thank You for choosing Bank App.Visit Again!");
                        flag = false;
                        break;
                    default:
                        System.out.println("Choose between 1 - 4");
                        break;
                }
            }
        } catch (InputMismatchException err) {
            System.out.println("_______________________________________________");
            System.out.println("No such entry, wrong input.");
            System.out.println("Thank You for choosing Bank App.Visit Again!");
        } sc.close();
    }


    // contains all the methods individually...
    // This was easy to track error / exceptions caught all are indepedent of one another
    // Also Junit can be applied on individual methods to check accuracy..
    public static void OnlineAccountExistAlready(int onlineAcc) {
        if (onlineAcc == 1) {
            // Scenario 1. cust has bank ac and also online ac
            System.out.println("---------------------------------------------------");
            System.out.println("Account already exist.You are already enrolled for mobile banking.");
            System.out.println("Select a correct option to login.");

        }
    }

    public static void settingPassword(int onlineAcc, String ssn) throws SQLException {
        if (onlineAcc == 2) {
            //Scenario 2. cust has bank ac  but no online ac

            System.out.println("You are almost there...");
            System.out.println("------------------------------------------");
            System.out.print("Enter Email: ");
            String email = sc.next();
            // In case 1 , we validated last name and ssn, account exist but cust is not registered for
            // mobile banking. hence setting password will skip all the personal information pain for cust...
            System.out.print("Set Password: ");
            String password = sc.next();
            // successfulyy registered password..
            Boolean isSuccess = customer.updatePassword(email, password, ssn);

            //isSuccess - true cust registered successfully
            if (isSuccess) {
                System.out.println("You are successfully registered for mobile banking.");
                System.out.println("Login and enjoy benefits!!");
            } else {
                // if email enetered does not correspond to ssn/lastname entered it will be false
                System.out.println("Email you entered is incorrect.");
                System.out.println("Enter email that is already registered with us.");
            }
        }
    }

    public static void NewBankAccount(String lastName, String ssn) throws
            SQLException {


            //Scenario 3. cust has no bank ac and no online ac
            System.out.println();
            System.out.print("Enter First Name: ");
            String first_name = sc.next();
            System.out.print("Enter Email: ");
            String email = sc.next();
            System.out.print("Enter Password: ");
            String password = sc.next();
            System.out.print("Enter Date Of Birth in YYYY-MM-DD format: ");
            Date dob = Date.valueOf(sc.next());
            System.out.print("Enter Phone Number: ");
            String contactNo = sc.next();
            System.out.print("Enter Street: ");
            String street = sc.next();
            System.out.print("Enter City: ");
            String city = sc.next();
            System.out.print("Enter state: ");
            String state = sc.next();
            System.out.print("Enter Country: ");
            String country = sc.next();
            System.out.print("Enter zip: ");
            int zip = sc.nextInt();
            System.out.print("Enter amount you want to deposit: ");
            System.out.println("Minimum balance should be $25.00 ");
            Double openingBalance = sc.nextDouble();
            System.out.println("Enter Account Type from below options: ");
            System.out.println(" -- Checking Account ");
            System.out.println(" -- Savings Account");
            System.out.print("Enter your option here: ");
            String accountType = sc.next();

            // setting the scanned input using setter methods
            //creating Customer object
            Customer c = new Customer();
            AccountInfo account = new AccountInfo();

            c.setEmail(email);
            c.setPassword(password);
            c.setFirstName(first_name);
            c.setLastName(lastName);
            c.setSsn(ssn);
            c.setDateOfBirth(dob);
            c.setContactNo(contactNo);
            c.setStreet(street);
            c.setCity(city);
            c.setState(state);
            c.setZip(zip);
            c.setCountry(country);
            account.setOpeningBalance(openingBalance);
            account.setAccount_type(accountType);

            Boolean customerAdded = customer.addCustomer(c, account);
            if (customerAdded) {
                System.out.println("----------------------------------------------------------------");
                System.out.println("You have successfully submitted information...");
                System.out.println("Your account is under review, we will get back to you shortly.");
                flag = false;
            } else {
                System.out.println("Something went wrong..");
                System.out.println("One or more fields have wrong information, please try again...");
            }

    }

    public static void printCustBalance(String email) throws SQLException {
        System.out.print("Enter Account Number : ");
        Integer account_num = sc.nextInt();
        // applying join on customers and accounts table , to get unique correct value
        Double balance = ac.getCurrentBalanceByAccNumber(account_num,email);
        System.out.println("Your balance in Account Number : " + account_num + " is : " + balance);
    }

    public static void custWithdraw(String email) throws SQLException {
        System.out.println(" How much would you like to withdraw today??");
        System.out.print("Enter here: ");
        Double withdrawAmount = sc.nextDouble();
        System.out.println(" Please confirm your Account Number: ");
        int account_number = sc.nextInt();
        Boolean status;
        Double availbal = ac.getCurrentBalanceByAccNumber(account_number,email);
        System.out.println("Balance before withdrawal: "+availbal);
        if (availbal > (withdrawAmount + 25)) {
            Double newBalance = availbal - withdrawAmount;
            Boolean balanceUpdate = ac.updateBalance(newBalance, account_number);
            if (balanceUpdate) {
                System.out.println("Withdraw completed successfully!");
                Double updatedbal = ac.getCurrentBalanceByAccNumber(account_number,email);
                System.out.println("Updated balance after withdrawal is : "+updatedbal);
            }
        } else {
            System.out.println("There are no sufficient funds for withdrawal.");
        }
    }

    public static void custDeposit( String email) throws SQLException {
        System.out.println(" How much would you like to deposit today??");
        System.out.print("Enter here: ");
        Double depositMoney = sc.nextDouble();
        System.out.println(" Please confirm your Account Number: ");
        int account_number = sc.nextInt();
        Double availbal = ac.getCurrentBalanceByAccNumber(account_number,email);
        System.out.println("Balance before deposit: "+availbal);
        Double newBalance = availbal + depositMoney;
        Boolean balanceUpdate = ac.updateBalance(newBalance, account_number);
        if (balanceUpdate) {
            System.out.println("Withdraw completed successfully!");
            Double updatedbal = ac.getCurrentBalanceByAccNumber(account_number,email);
            System.out.println("Updated balance after deposit is : "+updatedbal);
        } else {
            System.out.println("Oops! Something went wrong, try again...");
        }
    }

    public static void transferAmnt(String email) throws SQLException {
        System.out.println(" How much would you like to transfer??");
        System.out.print("Enter here: ");
        Double transferMoney = sc.nextDouble();
        System.out.println(" Please confirm your Account Number: ");
        int account_number_S = sc.nextInt();
        Double availbal = ac.getCurrentBalanceByAccNumber(account_number_S,email);
        System.out.println("Balance before transfer in Sender's A/C: "+availbal);
        if((availbal+25) > transferMoney) {
            System.out.println("Enter the Receiver's Account Number: ");
            int account_number_R = sc.nextInt();
            Boolean account_exist = ac.getAccountByAccountNumber(account_number_R);
            //System.out.println(account_exist);
            if (account_exist) {
                    ac.addTransfer(account_number_S,account_number_R,transferMoney,"Pending Accept");
                    System.out.println("Transfer sent, waiting for receiver to accept the transfer.");
                    availbal = ac.getCurrentBalanceByAccNumber(account_number_S,email);
                    System.out.println(" Available balance is: " + availbal);
                }
        }else {
            System.out.println("Oops! Something went wrong, try again...");
        }

    }

    public static void acceptTransfer(String email) throws SQLException {

        System.out.println("Hi, let me check if you have any pending transfers...");
        System.out.println("Enter your account_number: ");
        int acc_num_rec = sc.nextInt();
        System.out.println("Enter sender's account num: ");
        int acc_num_send = sc.nextInt();
        System.out.println("Enter transfer amount");
        Double transferMoney = sc.nextDouble();
        int pendingTransferCount = ac.pendingTransferUpdate(acc_num_send,acc_num_rec);
        if ( pendingTransferCount > 0 ) {
            System.out.println("Do you want to accept the transfer?");
            System.out.println(" Enter 'Y' for Yes and 'N' for No");
            String input = sc.next();
            if (input.charAt(0) == 'y' || input.charAt(0) == 'Y') {
                Double availbal = ac.getCurrentBalanceByAccNumber(acc_num_rec, email);
                Double newBalance = availbal + transferMoney;
                Boolean balanceUpdate = ac.updateBalance(newBalance, acc_num_rec);
                String transfer_status = "transfer_accepted";
                ac.statusUpdateafterTransfer(acc_num_send, acc_num_rec,transfer_status);
                System.out.println("Successful transaction..");
            } else if (input.charAt(0) == 'n' || input.charAt(0) == 'N') {
                //no change in both profiles;
                String transfer_status = "transfer_rejected";
                ac.statusUpdateafterTransfer(acc_num_send, acc_num_rec,transfer_status);
            } else {
                System.out.println("Hmm... that was a wrong input..");            }
        }else {
            System.out.println(" No [ending transfers...");
        }

    }


    public static void loginAsEmployee() throws SQLException {
        System.out.println("\nWelcome to Employee Login Page!! ");
        System.out.print("Enter username ");
        System.out.println(" // default username : Syeda ");
        String eemail = sc.next();
        System.out.print("Enter password: ");
        System.out.println(" // default password : syeda ");
        String epassword = sc.next();
        Boolean login = customer.employeeLogin(eemail,epassword);
        if(login) {
            System.out.println("You are successfully logged in.");
            System.out.println("You can now check details of customers:  ");
            System.out.println("Enter email of customer you want to review: ");
            String echeck = sc.next();
            List<Customer> cust = customer.printcustomers(echeck);
            for (Customer customer1 : cust) {
                System.out.println(customer1);

            }
        }
    }
}
