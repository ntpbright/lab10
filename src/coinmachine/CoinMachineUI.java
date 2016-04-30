package lab10.src.coinmachine;

import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Panel;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
/**
 * 
 * @author Nuttapatprom Chongamorkulprapa
 *
 */
public class CoinMachineUI implements Observer {
	//all attribute
	private JFrame frame;
	private JButton btnOnebaht;
	private JButton btnFivebaht;
	private JButton btnTenbaht;
	private JLabel lblBalance;
	private JLabel lblNumbalance;
	private JLabel lblStatus;
	private JProgressBar progressBar;
	private Coin oneBath;
	private Coin fiveBath;
	private Coin tenBath;
	private CoinMachine coinMachine;
	private JPanel panel;

	/**
	 * Create the application.
	 */
	public CoinMachineUI() {
		initialize();
		coinMachine = new CoinMachine(10);
	}

	/**
	 * Create the application.
	 * 
	 * @param cm is CoinMachine form main application
	 */
	public CoinMachineUI(CoinMachine cm) {
		initialize();
		coinMachine = cm;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		oneBath = new Coin(1);
		fiveBath = new Coin(5);
		tenBath = new Coin(10);

		frame = new JFrame();
		frame.setBounds(330, 100, 450, 217);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ActionListener addOneToMachine = new addCoinsOne();
		ActionListener addFiveToMachine = new addCoinsFive();
		ActionListener addTenToMachine = new addCoinsTen();

		lblBalance = new JLabel("Balance:");
		lblBalance.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBalance.setBounds(10, 11, 66, 19);
		frame.getContentPane().add(lblBalance);

		lblNumbalance = new JLabel();
		lblNumbalance.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNumbalance.setBounds(77, 11, 55, 19);
		frame.getContentPane().add(lblNumbalance);

		lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStatus.setBounds(140, 11, 66, 20);
		frame.getContentPane().add(lblStatus);

		progressBar = new JProgressBar();
		progressBar.setMaximum(10);
		progressBar.setBounds(216, 11, 208, 19);
		frame.getContentPane().add(progressBar);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "insert Money",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 36, 414, 134);
		frame.getContentPane().add(panel);

		btnOnebaht = new JButton("");
		panel.add(btnOnebaht);
		btnOnebaht.setIcon(new ImageIcon(CoinMachineUI.class.getResource("/lab10/src/images/1baht.png")));

		btnFivebaht = new JButton("");
		panel.add(btnFivebaht);
		btnFivebaht.setIcon(new ImageIcon(CoinMachineUI.class.getResource("/lab10/src/images/5baht.png")));

		btnTenbaht = new JButton("");
		panel.add(btnTenbaht);
		btnTenbaht.setIcon(new ImageIcon(CoinMachineUI.class.getResource("/lab10/src/images/10baht.png")));
		btnTenbaht.addActionListener(addTenToMachine);
		btnFivebaht.addActionListener(addFiveToMachine);
		btnOnebaht.addActionListener(addOneToMachine);
		frame.setVisible(true);
	}
	/**
	 * If machine is full will show error dialog
	 */
	private void hasBennFull(){
		JOptionPane.showMessageDialog(frame,
			    "Machine is cannot insert more coins.",
			    "Machine is full",
			    JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * Actions Listener to add object coin (One baht) to machine
	 */
	class addCoinsOne implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (!coinMachine.isFull()) {
				System.out.println("Insert 1 baht.");
				coinMachine.insert(oneBath);
			}else{
				hasBennFull();
			}
		}
	}
	/**
	 * Actions Listener to add object coin (Five baht) to machine
	 */
	class addCoinsFive implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (!coinMachine.isFull()) {
				System.out.println("Insert 5 baht.");
				coinMachine.insert(fiveBath);
			}else{
				hasBennFull();
			}
		}
	}
	/**
	 * Actions Listener to add object coin (Ten baht) to machine
	 */
	class addCoinsTen implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (!coinMachine.isFull()) {
				System.out.println("Insert 10 baht.");
				coinMachine.insert(tenBath);
			}else{
				hasBennFull();
			}
		}
	}
	/**
	 * update set balance and set value of progress bar
	 */
	@Override
	public void update(Observable subject, Object info) {
		// TODO Auto-generated method stub
		if (info != null) {
			lblNumbalance.setText(((CoinMachine) info).getBalance() + "");
			progressBar.setValue(((CoinMachine) info).getCount());
			System.out.println("Coin Machine UI has been updated");
			if(((CoinMachine) info).isFull()){
				progressBar.setForeground(Color.RED);;
			}else if (10>((CoinMachine) info).getCount() && ((CoinMachine) info).getCount()>=7){
				progressBar.setForeground(Color.ORANGE);;
			}else if (7> ((CoinMachine) info).getCount() && ((CoinMachine) info).getCount()>=5){
				progressBar.setForeground(Color.YELLOW);;
			}else if (5> ((CoinMachine) info).getCount()){
				progressBar.setForeground(Color.GREEN);;
			}
		}
	}

}
