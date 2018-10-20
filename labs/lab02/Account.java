/**
 * @author Alfred
 * The Account class represents a bank account whose
 * current balance is a nonnegative amount in US dollars.
 * */
public class Account {
    /**
     * balance is a private member of every Account object.
     */
    private int balance;
    /**
     * parentAccount is a private member of every Account object.
     */
    private Account parentAccount;

    /**
     * A constructor.
     * @param amount ** Represents the balance in the account.
     * Initialize an account with the given BALANCE. */
    public Account(int amount) {
        this.balance = amount;
        this.parentAccount = null;
    }

    /**
     * Another constructor.
     * @param amount ** Represents the balance in the account.
     * @param parent ** parentAccount provides overdraft protection.
     */
    public Account(int amount, Account parent) {
        this.balance = amount;
        this.parentAccount = parent;
    }

    /**
     * @param amount ** The amount to be deposited into an account.
     * Deposits AMOUNT into the current account.
     * */
    public void deposit(int amount) {
        if (amount < 0) {
            System.out.println("Cannot deposit negative amount.");
        } else {
            balance += amount;
        }
    }

    /**
     * @param amount * The amount to be subtracted.
     * @return a bool value.
     * Subtract AMOUNT from the account if possible. If subtracting AMOUNT
     * would leave a negative balance, print an error message and leave the
     * balance unchanged.
     */
    public boolean withdraw(int amount) {
        int total = 0;
        Account temp = this;
        while (temp.parentAccount != null) {
            total += temp.balance;
            temp = temp.parentAccount;
        }

        total += temp.balance;
        if (amount < 0) {
            System.out.println("Cannot withdraw negative amount.");
            return false;
        } else if (this.balance < amount) {
            if (this.parentAccount != null) {
                if (total >= amount) {
                    this.parentAccount.withdraw(amount - this.balance);
                    this.balance = 0;
                    return true;
                } else {
                    System.out.println("Insufficient funds");
                    return false;
                }
            } else {
                System.out.println("Insufficient funds");
                return false;
            }
        } else {
            this.balance -= amount;
            return true;
        }
    }

    /**
     * @param other * Another account to be merged
     * Merge account OTHER into this account by removing all money from OTHER
     * and depositing it into this account.
     */
    public void merge(Account other) {
        this.balance += other.balance;
        other.balance = 0;
    }

    /**
     * The main method, for testing purposes.
     * You can simply comment the main method.
     * @param args ** A command line arguments.
     */
    public static void main(String[] args) {
        Account linda = new Account(1000);
        Account kathy = new Account(500, linda);
        Account megan = new Account(100, kathy);
        int times = 10;
        for (int i = 1; i < times; i++) {
            int temp = i * 100;
            megan.withdraw(temp);
            System.out.println("megan's balance: " + megan.balance);
            System.out.println("kathy's balance: " + kathy.balance);
            System.out.println("linda's balance: " + linda.balance);
        }
    }

}
