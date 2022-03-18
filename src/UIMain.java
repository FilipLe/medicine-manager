import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author nguyenle
 *
 * Startup Screen of Application
 */
public class UIMain {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIMain window = new UIMain();
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
	public UIMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Home Medication Tracker");
		//Frame light blue background
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Logo Icon
		JLabel lblLogo = new JLabel(new ImageIcon("/Users/nguyenle/workspace-2021-swing-applications/Medicine_Manager_REAL/src/NguyenLogo.png"));
		lblLogo.setBounds(3, 0, 100, 93);
		frame.getContentPane().add(lblLogo);
		
		//Quote 1
		JLabel lblQuote1 = new JLabel("Keeping track of medication is difficult. I can help.");
		lblQuote1.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblQuote1.setBounds(46, 100, 347, 33);
		frame.getContentPane().add(lblQuote1);
		
		//Quote 2
		JLabel lblQuote2 = new JLabel("Integrating support into families' everyday lives.");
		lblQuote2.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblQuote2.setBounds(46, 130, 347, 45);
		frame.getContentPane().add(lblQuote2);
		
		//Quote 3
		JLabel lblQuote3_1 = new JLabel("Spend less time thinking about medications ");
		lblQuote3_1.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblQuote3_1.setBounds(46, 168, 347, 45);
		frame.getContentPane().add(lblQuote3_1);
		
		//Quote 3 Second Part
		JLabel lblQuote3_2 = new JLabel("— and more time enjoying life.");
		lblQuote3_2.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblQuote3_2.setBounds(46, 197, 260, 16);
		frame.getContentPane().add(lblQuote3_2);
		
		//Continue button to take User to Home Screen
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close current Frame
				frame.dispose();
				//Run Home Screen Frame
				HomeScreen.main(null);
			}
		});
		btnContinue.setBounds(148, 225, 117, 29);
		frame.getContentPane().add(btnContinue);
		
		JLabel lblAuthor = new JLabel("— Nguyen Le");
		lblAuthor.setFont(new Font("Apple Chancery", Font.BOLD | Font.ITALIC, 16));
		lblAuthor.setBounds(283, 57, 117, 29);
		frame.getContentPane().add(lblAuthor);
		
		JLabel lblNewLabel = new JLabel("HOME MEDICATION TRACKER");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblNewLabel.setBounds(115, 20, 311, 45);
		frame.getContentPane().add(lblNewLabel);
		
				
		//Dark blue ribbon on top
		JPanel blueBackground = new JPanel();
		//background color of ribbon should be 0x00829b
		blueBackground.setBackground(new Color(0x00829b));
		blueBackground.setBounds(0, -1, 450, 93);
		frame.getContentPane().add(blueBackground);
		
		//Button to quit the program
		JButton btnQuitProgram = new JButton("Quit Program");
		btnQuitProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close Window
				frame.dispose();
			}
		});
		btnQuitProgram.setBounds(336, 237, 108, 29);
		frame.getContentPane().add(btnQuitProgram);
	}
}
