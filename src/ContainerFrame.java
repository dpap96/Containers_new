import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContainerFrame extends JFrame {
	private JPanel panel= new JPanel(); //γενικο πανελ
	private JPanel fieldPanel= new JPanel();
	private JTextField codefield,destfield,weightfield,powerfield;
	private JButton createBulkButton,createRefrButton;
	
	private JList<String> shipList; // Η ΛΙΣΤΑ ( ΓΙΑ ΝΑ ΠΕΡΑΣΟΥΜΕ ΤΗΝ ArrayList)
	private ArrayList<Ship> someShips;
	
	
//Κατασκευαστής
	public ContainerFrame(ArrayList<Ship > s) {
		someShips=s;
	//ΤΟ ΜΕΓΑΛΟ ΠΑΝΕΛ ΘΑ ΕΧΕΙ ΔΙΑΤΑΞΗ (Β-Ν-Α-Δ)
		panel.setLayout(new BorderLayout());//το κεντρικο πανελ θα ακολουθει αυτη την διαταξη
		
	//ΤΟ ΜΙΚΡΟ ΠΑΝΕΛ ΘΑ ΕΧΕΙ ΔΙΑΤΑΞΗ (ΓΡΑΜΜΕΣ+ΣΤΗΛΕΣ)
		fieldPanel.setLayout(new GridLayout(3,2)); //το μικρο πανελ θα εχει διαταξη grid με 3 γραμμές+2 στήλες

//Φτιαχνω τα γραφικα συστατικα:	
		codefield= new JTextField("Insert code");
		destfield = new JTextField("Inser t destination");
		weightfield= new JTextField("Insert Weight");
		powerfield = new JTextField("Insert power");
		createBulkButton = new JButton("Create Bulk Container");
		createRefrButton= new JButton("Create Refridgerator Container");
		
//ΓΙΑ ΤΟΝ ΧΕΙΡΙΣΜΟ ΤΗΣ ΛΙΣΤΑΣ! ( ArrayList)		
		shipList= new JList(); 
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(Ship sh: someShips)
			model.addElement(sh.getName());
		shipList.setModel(model); //προσθετω την λίστα στο μοντέλο
		
		
		
//Προσθετω τα γραφικα συστατικά στο fieldPanel:
		fieldPanel.add(codefield);
		fieldPanel.add(destfield);
		fieldPanel.add(weightfield);
		fieldPanel.add(powerfield);
		fieldPanel.add(createBulkButton);
		fieldPanel.add(createRefrButton);
	
		
//ΠΡΟΣΘΕΤΟΥΜΕ ΤΟ ΜΙΚΡΟ ΠΑΝΕΛ ΣΤΟ ΜΕΓΑΛΟ ΠΑΝΕΛ....ΣΤΟ ΚΕΝΤΡΟ
		panel.add(fieldPanel,BorderLayout.CENTER);
//ΠΡΟΣΘΕΤΩ ΤΗΝ ΛΙΣΤΑ ΣΤΟ ΜΕΓΑΛΟ ΠΑΝΕΛ....ΣΤΟ ΝΟΤΟ
		panel.add(shipList,BorderLayout.SOUTH);
		
//ΒΑΖΩ ΤΟ ΣΥΝΟΛΙΚΟ ΠΑΝΕΛ ΣΤΟ ΠΑΡΑΘΥΡΟ
		this.setContentPane(panel);
		ButtonListener listener= new ButtonListener();
		createBulkButton.addActionListener(listener);
		createRefrButton.addActionListener(listener);
		
		this.setVisible(true);
		this.setSize(400,400);
		this.setTitle("Create Containers");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
//ΓΙΑ ΤΟ ΠΛΗΚΤΡΟ CreateBulk !!!
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String code= codefield.getText(); //παιρνω τα πεδία που πληκτρολογει ο χρηστης
			String dest =destfield.getText();
			String x= shipList.getSelectedValue();
			if(e.getSource().equals(createBulkButton)) { //ΕΑΝ ΤΟ ΚΟΥΜΠΙ ΠΟΥ ΠΑΤΗΘΗΚΕ ΕΙΝΑΙ ΤΟ createBulkButton
			
			String weightText= weightfield.getText();
			double weight = Double.parseDouble(weightText); //μετατροπη του String σε double
			
			Container bulk = new Bulk(code,dest,weight); //φτιαξαμε container
			
		//ΓΙΑ ΝΑ ΠΑΡΩ ΤΗΝ ΕΠΙΛΟΓΗ ΤΟΥ ΧΡΗΣΤΗ ΑΠΟ ΤΗΝ ΛΙΣΤΑ:
			
			
			for(Ship ship: someShips) {
				if(ship.getName().equals(x)){
					ship.addContainer(bulk); //ΠΡΟΣΘΕΤΩ ΤΟ ΠΛΟΙΟ ΣΤΗΝ ΛΙΣΤΑ
					System.out.println("New Bulk created "+ ship.getName());
					System.out.println("Total charge for:"+ ship.getName() +" ,is "+ ship.calcTotalCharge());
					}
				}
			}else{
						String powerText=powerfield.getText();
						double power= Double.parseDouble(powerText);
						
						Container refr= new Refridgerator(code,dest,power);
						for(Ship selectedship: someShips) {
							if(selectedship.getName().equals(x)) {
								selectedship.addContainer(refr);
								System.out.println("New Refridgerator created! " + selectedship.getName());
							}		
						}
					}
				
				
		}
	}
}
