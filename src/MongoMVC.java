import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MongoMVC {
	static MongoView theView;
	static MongoModel theModel;
	static MongoControl theControl;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		theView = new MongoView();
		theModel = new MongoModel();
		theControl = new MongoControl(theView, theModel);
		
		theView.setVisible(true);
	}
}
