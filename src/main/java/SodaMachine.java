import java.util.Scanner;

public class SodaMachine
{

    // Hard to test - should make it easier to do so (refactoring into methods)
    // Where is this initialized? -- in the input

    /// <summary>
    /// This is the starter method for the machine
    /// </summary>
    public static void displaySummaryMessage(int credit) {
        System.out.println("\n\nAvailable commands:");
        System.out.println("INSERT - Money put into money slot");
        System.out.println("ORDER- Order from machines buttons");
        System.out.println("SMS_ORDER - Order sent by sms");
        System.out.println("RECALL - gives money back");
        System.out.println("-------");
        System.out.println("Inserted credit: " + credit);
        System.out.println("-------\n\n");
    }

    public void Start()
    {
        Scanner scanner = new Scanner(System.in);
        Wallet wallet = new Wallet();
        Inventory inventory = new Inventory(3, 5, 5);
        while (true) {
            SodaMachineController controller = new SodaMachineController(inventory, wallet);
            displaySummaryMessage(wallet.getCredit());
            String userInput = scanner.next();
            controller.takeOrder(userInput, scanner);
        }
    }
}
