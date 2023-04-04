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
	private JPanel panel= new JPanel(); //������ �����
	private JPanel fieldPanel= new JPanel();
	private JTextField codefield,destfield,weightfield,powerfield;
	private JButton createBulkButton,createRefrButton;
	
	private JList<String> shipList; // � ����� ( ��� �� ��������� ��� ArrayList)
	private ArrayList<Ship> someShips;
	
	
//�������������
	public ContainerFrame(ArrayList<Ship > s) {
		someShips=s;
	//�� ������ ����� �� ���� ������� (�-�-�-�)
		panel.setLayout(new BorderLayout());//�� �������� ����� �� ��������� ���� ��� �������
		
	//�� ����� ����� �� ���� ������� (�������+������)
		fieldPanel.setLayout(new GridLayout(3,2)); //�� ����� ����� �� ���� ������� grid �� 3 �������+2 ������

//������� �� ������� ���������:	
		codefield= new JTextField("Insert code");
		destfield = new JTextField("Inser t destination");
		weightfield= new JTextField("Insert Weight");
		powerfield = new JTextField("Insert power");
		createBulkButton = new JButton("Create Bulk Container");
		createRefrButton= new JButton("Create Refridgerator Container");
		
//��� ��� �������� ��� ������! ( ArrayList)		
		shipList= new JList(); 
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(Ship sh: someShips)
			model.addElement(sh.getName());
		shipList.setModel(model); //�������� ��� ����� ��� �������
		
		
		
//�������� �� ������� ��������� ��� fieldPanel:
		fieldPanel.add(codefield);
		fieldPanel.add(destfield);
		fieldPanel.add(weightfield);
		fieldPanel.add(powerfield);
		fieldPanel.add(createBulkButton);
		fieldPanel.add(createRefrButton);
	
		
//����������� �� ����� ����� ��� ������ �����....��� ������
		panel.add(fieldPanel,BorderLayout.CENTER);
//�������� ��� ����� ��� ������ �����....��� ����
		panel.add(shipList,BorderLayout.SOUTH);
		
//���� �� �������� ����� ��� ��������
		this.setContentPane(panel);
		ButtonListener listener= new ButtonListener();
		createBulkButton.addActionListener(listener);
		createRefrButton.addActionListener(listener);
		
		this.setVisible(true);
		this.setSize(400,400);
		this.setTitle("Create Containers");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
//��� �� ������� CreateBulk !!!
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String code= codefield.getText(); //������ �� ����� ��� ������������ � �������
			String dest =destfield.getText();
			String x= shipList.getSelectedValue();
			if(e.getSource().equals(createBulkButton)) { //��� �� ������ ��� �������� ����� �� createBulkButton
			
			String weightText= weightfield.getText();
			double weight = Double.parseDouble(weightText); //��������� ��� String �� double
			
			Container bulk = new Bulk(code,dest,weight); //�������� container
			
		//��� �� ���� ��� ������� ��� ������ ��� ��� �����:
			
			
			for(Ship ship: someShips) {
				if(ship.getName().equals(x)){
					ship.addContainer(bulk); //�������� �� ����� ���� �����
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
