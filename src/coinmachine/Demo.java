package lab10.src.coinmachine;
import java.util.Scanner;

/**
 * Console dialog for inserting coins into Coin machine.
 * @author James Brucker
 *
 */
public class Demo {
	// create a java.util.Scanner object for use in all methods
	private static Scanner console = new Scanner( System.in );
	
	
	/** run the user interface */
	public void insertDialog(CoinMachine machine) {
		System.out.println("Coin Machine has a capacity of "+ machine.getCapacity());
		System.out.print("Input the value of coins to insert (separated by space). ");
		System.out.println("Enter a blank line to quit.");
		do {
			System.out.print("Values of coins to insert: ");
			String reply = console.nextLine().trim();
			if ( reply.isEmpty() ) break;
			// split the line into tokens and insert values
			String [] words = reply.split("\\s+");
			for(String word : words ) {
				int value = Integer.parseInt(word);
				if ( value <= 0 ) System.out.println("can't insert negative value");
				else {
					Coin coin = new Coin(value);
					if ( machine.insert( coin ) ) {
						System.out.println(coin+" inserted");
					} else {
						System.out.println("Insert "+coin+" FAILED.");
					}
				}
			}
			
			
		} while( ! machine.isFull() );
		
		displayMachineStatus(machine);
		
	}
	
	/** Show the number of coins and their total value. */
	private void displayMachineStatus(CoinMachine machine) {
		// CLUDGE: how to get the currency?  Look at the first coin in machine.
		String currency = "";
		if (machine.getCount() > 0) currency = machine.getCoins().get(0).getCurrency();
//		System.out.printf("Machine contains %d coins and value %d %s\n",
//				machine.getCount(), machine.getBalance(), currency);	
		if (machine.isFull()) System.out.println("Machine is FULL.");
	}

	/**
	 * Run a console demo.
	 * @param args not used
	 */
	public static void main(String[] args) {
		final int capacity = 10;  // how many coins the machine can hold
		CoinMachine machine = new CoinMachine( capacity );
		CountUI countMachine = new CountUI(machine);
		CoinMachineUI coinMachine = new CoinMachineUI(machine);
		machine.addObserver(countMachine);
		machine.addObserver(coinMachine);
		Demo demo = new Demo();
		//TODO add observers
		demo.insertDialog(machine);
	}
}











