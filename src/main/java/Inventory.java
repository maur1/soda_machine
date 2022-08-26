public class Inventory {

    // Not handled restock of supplies - might want to reconsider this class
    private int coke_amount;
    private int fanta_amount;
    private int sprite_amount;

    public Inventory(int coke_amount, int fanta_amount, int sprite_amount) {
        this.coke_amount = coke_amount;
        this.fanta_amount = fanta_amount;
        this.sprite_amount = sprite_amount;
    }


    public boolean validateInventory(Soda soda) {
        switch (soda) {
            case COKE:
                    return this.coke_amount > 0;
            case FANTA:
                    return this.fanta_amount > 0;
            case SPRITE:
                    return this.sprite_amount > 0;
            default:
                    return false;
        }
    }

    public void reduceInventory(Soda soda, int amount) {
        // should probably have amuont, but handling only one case now
        System.out.println(soda);
        switch (soda) {
            case COKE:
                this.coke_amount -= amount;
                break;
            case FANTA:
                this.fanta_amount -= amount;
                break;
            case SPRITE:
                this.sprite_amount -= amount;
                break;
            default:
                    throw new AssertionError("Unknown Soda " + soda);
        }
    }
    public String getInventoryOverview() {
        return "Inventory: coke:" + coke_amount + " fanta:" + fanta_amount + " sprite:" + sprite_amount;
    }
}
