/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

    public int balance;
    public Account parentAccount;

    /** Initialize an account with the given BALANCE. */
    public Account(int balance) {
        this.balance = balance;
        this.parentAccount = null;
    }

    public Account(int balance, Account parentAccount){
        this.balance = balance;
        this.parentAccount = parentAccount;
    }

    /** Deposits AMOUNT into the current account. */
    public void deposit(int amount) {
        if (amount < 0) {
            System.out.println("Cannot deposit negative amount.");
        } else {
            balance += amount;
        }
    }

    /**
     * Subtract AMOUNT from the account if possible. If subtracting AMOUNT
     * would leave a negative balance, print an error message and leave the
     * balance unchanged.
     */ 
    public boolean withdraw(int amount) {
        int total = 0;
        Account temp = this;
        while(temp.parentAccount != null){
            total += temp.balance;
            temp = temp.parentAccount;
        }

        total += temp.balance;
        if(amount < 0){
            System.out.println("Cannot withdraw negative amount.");
            return false;
        }else if (this.balance < amount) {
            if(this.parentAccount != null){
                if(total >= amount){
                    this.parentAccount.withdraw(amount - this.balance);
                    this.balance = 0;
                    return true;
                }else{
                    System.out.println("Insufficient funds");
                    return false;
                }
            }else{
                System.out.println("Insufficient funds");
                return false;
            }
        }else{
            this.balance -= amount;
            return true;
        }
    }

    /**
     * Merge account OTHER into this account by removing all money from OTHER
     * and depositing it into this account.
     */
    public void merge(Account other) {
        this.balance += other.balance;
        other.balance = 0;
    }

    public static void main(String[] args){
        Account linda = new Account(1000);
        Account kathy = new Account(500, linda);
        Account megan = new Account(100, kathy);
        for(int i = 1; i < 10; i ++){
            int temp = i * 100;
            megan.withdraw(temp);
            System.out.println("megan's balance: " + megan.balance);
            System.out.println("kathy's balance: " + kathy.balance);
            System.out.println("linda's balance: " + linda.balance);
        }
    }

}
