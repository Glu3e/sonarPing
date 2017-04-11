
public class roomScanner {

	
	public static void main(String[] args) {
		
		SonarPingModel x = new SonarPingModel("COM11");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		x.writeNumber("1");

	}

}
