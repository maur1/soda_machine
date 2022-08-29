import java.util.Scanner;

public class SodaMachine
{
    /// <summary>
    /// Soda machine class, takes inputs and processes it accordingly
    /// </summary>

    public void start() {
        Wallet wallet = new Wallet();
        Inventory inventory = new Inventory(3, 5, 5);
        SodaMachineState state = SodaMachineState.DefaultScreen;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(state.info(wallet, inventory));
            String userInput = scanner.next();
            try {
                state = state.process(userInput, wallet, inventory);
            } catch (IllegalArgumentException e) {
                System.out.println("Input not recognized, please try again.");
            }
        }
    }
}
