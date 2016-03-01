
public class StartingPoint {
	public static void main(String [] args){
		SonarPingModel model = new SonarPingModel("COM3");
		SonarPingView view = new SonarPingView();
		SonarPingController controller = new SonarPingController(model, view);
	}
}
