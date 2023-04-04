import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Ship > ships= new ArrayList<Ship>();
		Ship ship1 = new Ship("Copacabana",3);
		Ship ship2= new Ship("Hercules",3);
		Ship ship3= new Ship("Zeus",3);
		Ship ship4= new Ship("Tinos",3);
		
		ships.add(ship1);
		ships.add(ship2);
		ships.add(ship3);
		ships.add(ship4);
//		System.out.println("Ship's total charge: "+ ship.calcTotalCharge());
				
		new ContainerFrame(ships);
		

	}

}
