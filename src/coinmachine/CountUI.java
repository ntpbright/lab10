package lab10.src.coinmachine;

import java.awt.EventQueue;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.jws.Oneway;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.List;
import java.awt.Color;
/**
 * 
 * @author Nuttapatprom Chongamorkulprapa
 *
 */
public class CountUI implements Observer{

	private JFrame frame;
	private JTextField textField;
	private JLabel lblAccepting;
	private CoinMachine coinMachine;
	private List<Coin> lstCoins;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CountUI window = new CountUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CountUI() {
		initialize();
		CoinMachine coinMachine = new CoinMachine(10);
	}
	/**
	 * Create the application.
	 * @param cm is CoinMachine form main application
	 */
	public CountUI(CoinMachine cm) {
		initialize();
		CoinMachine coinMachine = cm ; 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		lstCoins = new java.util.ArrayList<Coin>();
		frame = new JFrame();
		frame.setBounds(100, 100, 248, 124);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblcoins = new JLabel("#Coins:");
		lblcoins.setForeground(Color.BLACK);
		lblcoins.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblcoins.setBounds(10, 11, 78, 44);
		lblcoins.setEnabled(false);
		frame.getContentPane().add(lblcoins);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField.setBounds(82, 20, 140, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		lblAccepting = new JLabel();
		lblAccepting.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAccepting.setBounds(10, 58, 212, 27);
		frame.getContentPane().add(lblAccepting);
		frame.setVisible(true);
	}
	/**
	 * update label lblAccepting when have update or full
	 */
	@Override
	public void update(Observable subject, Object info) {
		lstCoins = ((CoinMachine)info).getCoins();
		if(info != null){
			textField.setText(((CoinMachine)info).getCount()+"");
			if(((CoinMachine)info).isFull()){
				lblAccepting.setForeground(Color.RED);
				lblAccepting.setText("Full!!");
				System.out.println("Machine is full.");
			}else{
				lblAccepting.setForeground(Color.GREEN);
				lblAccepting.setText("Accepting Coins "+lstCoins.get(lstCoins.size()-1).toString());
			}
			System.out.println("Count UI has been updated");
		}
		
	}
}
