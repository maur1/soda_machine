public class Wallet {
    private int credit = 0;

    public int getCredit() {
        return this.credit;
    }
    public void addToCredit(String credit) {
        this.credit += Integer.parseInt(credit);
    }
    public void subtractCredit(int credit) {
        this.credit -= credit;
    }

    public void returnMoney() {
        this.credit = 0;
    }

    private boolean validateCredit(Soda soda) {
        return soda.getCost() <= getCredit();
    }

    public void creditCheck(Soda soda) throws IllegalArgumentException {
        if (!validateCredit(soda)) {
            //
            throw new IllegalArgumentException(
                    "Credit check failed due to insufficient funds \n" +
                            "Credit on book: " + getCredit() +
                            "\nCost of "+ soda + ":" +soda.getCost()
                            + "\nInsert more credit");
        }
    }


}
