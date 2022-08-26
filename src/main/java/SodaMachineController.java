import java.util.Scanner;

public class SodaMachineController {
    Inventory inventory;
    Wallet wallet;
    // Handles type of order
    public SodaMachineController(Inventory inventory, Wallet wallet){
        this.inventory = inventory;
        this.wallet = wallet;
    }
    public void takeOrder(String userInput, Scanner scanner) throws IllegalArgumentException{
        // Later versions of java have better switch statements, avoid break
        try {
            switch (Command.valueOf(userInput)) {
                case INSERT:
                    insertMoney(scanner);
                    break;
                case ORDER: {
                    try {
                        orderSoda(true, scanner);
                        wallet.returnMoney();
                    } catch (IllegalArgumentException e) {
                        // Exception handling to be improved with custom exceptions
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case SMS_ORDER: {
                    try {
                        orderSoda(false, scanner);
                    } catch (IllegalArgumentException e) {
                        // Exception handling to be improved with custom exceptions
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case RECALL:
                    wallet.returnMoney();
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Machine did not recognize input try again");
        }
    }


    public void orderSoda(boolean creditCheck, Scanner scanner) throws IllegalArgumentException {
        System.out.println(inventory.getInventoryOverview());
        // should probably display price
        System.out.println("Insert one of : \n"
                + Soda.COKE + "\n"
                + Soda.FANTA + "\n"
                + Soda.SPRITE);
        // Should have some validation of input here (and allow lower case handling in enum class)
        Soda soda = Soda.valueOf(scanner.next());
        if (creditCheck) {
            handleCredit(soda);
        }
        handleInventory(soda);
    }

    private boolean validateCredit(Soda soda) {
        return soda.getCost() <= wallet.getCredit();
    }
    private void insertMoney(Scanner scanner) {
        System.out.println("Insert input amount: ");
        int input = scanner.nextInt();
        wallet.addToCredit(input);
        System.out.println("Added: " + input + "to credit");
        System.out.println("New credit total: " + wallet.getCredit() + "to credit");
    }
    private void handleCredit(Soda soda) throws IllegalArgumentException {
        if (!validateCredit(soda)) {
            //
            throw new IllegalArgumentException(
                    "Credit check failed due to insufficient funds \n" +
                            "credit on book: " + wallet.getCredit() +
                            "cost of "+ soda + ":" +soda.getCost()
                            + " insert more credit");
        } else {
            wallet.subtractCredit(soda.getCost());
        }
    }
    private void handleInventory(Soda soda) {
        if (inventory.validateInventory(soda)) {
            inventory.reduceInventory(soda, 1);
            System.out.println("Giving " + soda + "out");
        } else {
            throw new IllegalArgumentException(
                    soda + " not in stock. " + inventory.getInventoryOverview());
        }
    }

}
