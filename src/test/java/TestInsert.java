import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestInsert {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        data = String.format(data, System.lineSeparator(),
                System.lineSeparator());
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
    @Test
    public void test_scanner_with_injected_input() {

        Scanner scanner = Mockito.mock(Scanner.class);
        Mockito.when(asker.ask()).thenReturn(1);

        int result = asker.ask();
        Assert.assertEquals(result, 1);
    }

    //System.out.println("Returning " + this.credit + " to customer");
    @Test
    public void testInsertSuccess() {
        int credit = 10;
        final String output = "Returning " + credit + " to customer";
        final String insert10 = "RECALL";
        Inventory inventory = new Inventory(3 ,5,5);
        Wallet wallet = new Wallet();
        SodaMachineController smc = new SodaMachineController(inventory, wallet);

        smc.takeOrder("INSERT", provideInput(insert10));
        assertEquals(output, getOutput());
    }

}
