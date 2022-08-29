import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestVendingMachine {


    //System.out.println("Returning " + this.credit + " to customer");
    @Test
    public void test_defaultStateInfoMessage_noCoins() {
        int credit = 0;

        Inventory inventory = new Inventory(3 ,5,5);
        Wallet wallet = new Wallet();

        SodaMachineState state = SodaMachineState.DefaultScreen;

        String message = "\n\nAvailable commands:"
                + "\n INSERT - Money put into money slot"
                + "\n ORDER- Order from machines buttons"
                + "\n SMS_ORDER - Order sent by sms"
                + "\n RECALL - gives money back"
                + "\n-------"
                + "\nInserted credit: " + credit
                + "\n-------\n\n";
        assertEquals(message, state.info(wallet, inventory));
    }

    @Test
    public void test_insertCoinsProcess_valid() {
        String input = "20";
        Inventory inventory = new Inventory(3 ,5,5);
        Wallet wallet = new Wallet();

        SodaMachineState state = SodaMachineState.InsertMoney;

        String message = "INPUT INSERT AMOUNT 5,10,20";
        assertEquals(message, state.info(wallet, inventory));
        assertEquals(0, wallet.getCredit());
        state = state.process(input, wallet, inventory);
        assertEquals(20, wallet.getCredit());
        // Should also assert the system out message
        assertEquals(SodaMachineState.DefaultScreen, state);

    }

    @Test
    public void test_orderCoke_invalid_dueTo_emptyInventory() {
        String input = "FANTA";
        int coke_amount = 0;
        int fanta_amount = 0;
        int sprite_amount = 0;
        String infoOutput =  "Inventory: coke:" + coke_amount + " fanta:" + fanta_amount + " sprite:" + sprite_amount +
                "\n Insert one of : \n"
                + Soda.COKE + "\n"
                + Soda.FANTA + "\n"
                + Soda.SPRITE;

        Inventory inventory = new Inventory(coke_amount ,fanta_amount,sprite_amount);
        Wallet wallet = new Wallet();
        wallet.addToCredit("100");
        SodaMachineState state = SodaMachineState.OrderReceived;

        assertEquals(infoOutput, state.info(wallet, inventory));
        assertEquals(100, wallet.getCredit());
        state = state.process(input, wallet, inventory);
        assertEquals(0, wallet.getCredit());
        // Should also assert the system out message - Dispensing soda not shown
        assertEquals(SodaMachineState.DefaultScreen, state);

    }
}
