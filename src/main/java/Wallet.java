public class Wallet {
    private int credit = 0;

    public int getCredit() {
        return this.credit;
    }
    public void addToCredit(int credit) {
        this.credit += credit;
    }
    public void subtractCredit(int credit) {
        this.credit -= credit;
    }

    public void returnMoney() {
        // Should be moved into money handling class
        System.out.println("Returning " + this.credit + " to customer");
        this.credit = 0;
    }



}
