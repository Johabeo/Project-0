public class Account {

    private int bankAccountNumber;

    private int userId;

    private int status;

    private int balance;

    public Account(int bankAccountNumber, int userId, int status, int balance) {
        this.bankAccountNumber = bankAccountNumber;
        this.userId = userId;
        this.status = status;
        this.balance = balance;
    }

    public Account () {}

    public int getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(int bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "bankAccountNumber=" + bankAccountNumber +
                ", userId=" + userId +
                ", status=" + status +
                ", balance=" + balance +
                "}\n";
    }
}
