
public class Refridgerator extends Container {

	private double power;

	public Refridgerator(String code, String destination,double p) {
		super(code, destination);
		power=p;

	}
	public double getCharge() {
		return 2000*power;
	}
	
	
}
