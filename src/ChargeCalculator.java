import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class ChargeCalculator extends JFrame{
	
	
	private JPanel panel; //δημιουργια του πανελ
	private JButton button; //δημιουργια του κουμπιου
	private JTextField textField; //δημιουργια του πεδιου
	
	private Ship ship; //παιρναω το πλοιο
	
	public ChargeCalculator(Ship s) {
		ship=s; //κανω αντιστοιχιση το πλοιο
		
		panel = new JPanel(); //δημιουργια του πανελ
		button=new JButton("Calculate Charge");
		textField= new JTextField("Total charge for all containers");
		
//ΠΡΟΣΘΕΤΩ ΤΑ ΓΡΑΦΙΚΑ ΣΤΟ ΠΑΝΕΛ
		panel.add(button);
		panel.add(textField);
		
//ΒΑΖΩ ΤΟ ΠΑΝΕΛ ΠΑΝΩ ΣΤΟ ΠΑΡΑΘΥΡΟ	
		this.setContentPane(panel);
//ΓΙΑ ΤΟ ΚΟΥΜΠΙ		
		ButtonListener listener = new ButtonListener();
		button.addActionListener(listener);
		
//STANDARD- ΓΙΑ ΤΟ ΠΑΡΑΘΥΡΟ ΤΗΣ ΔΙΑΣΥΝΔΕΣΗΣ
		this.setVisible(true);
		this.setSize(400,400);
		this.setTitle("Carge Calculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
//ΕΣΩΤΕΡΙΚΗ ΚΛΑΣΗ ΓΙΑ ΤΗΝ ΥΛΟΠΟΙΗΣΗ ΤΟΥ ΚΟΥΜΠΙΟΥ :
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String sum= String.valueOf(ship.calcTotalCharge()); //μετατρέπω την τιμή σε string
			textField.setText(sum); //περνάω το string στο textField
		}
		
		
	}
}
