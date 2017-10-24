package boot;

import java.io.IOException;

import controller.MySokobanController;
import controller.server.ClientHandler;
import controller.server.MyServer;
import controller.server.SokobanHandlerClient;
import model.MyModel;
import model.policy.MySokobanPolicy;
import view.NewCli;

/**
 * Eylon Ben David 3083607346
 * Niv Bayazi  203665468
*/

public class TestMain {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		SokobanHandlerClient ch=new SokobanHandlerClient();
		MyServer server=new MyServer(3323, ch);
		MyModel model=new MyModel();
		NewCli view=new NewCli();
		MySokobanController controller=new MySokobanController(model, view);
		
		model.addObserver(controller);
		view.addObserver(controller);
		ch.addObserver(controller);
		server.start();
		//server.stop();
		
		//view.start();
		
	}
}
