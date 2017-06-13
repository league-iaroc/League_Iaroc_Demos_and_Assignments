package demos;

import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class InfraredSensorDemo extends IRobotAdapter {
	Sonar sonar = new Sonar();
	
	public InfraredSensorDemo(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		InfraredSensorDemo rob = new InfraredSensorDemo(base);
		rob.setup();
		while(rob.loop()){}
		rob.shutDown();
		
	}

	
	
	private void setup() throws Exception {
		//SETUP CODE GOES HERE!!!!!
	}
	

	private boolean loop() throws Exception{
		//LOOP CODE GOES HERE!!!!!
		readSensors(100);
		
		if(getInfraredByteLeft() > 0){
			driveDirect(100, 200);
			sleep(100);
		}else if(getInfraredByteRight() > 0){
			driveDirect(200, 100);
			sleep(100);
		}else{
			driveDirect(50, -50);
		}	
		
		if(isHomeBaseChargerAvailable()){
			driveDirect(0, 0);
			return false;
		}
		
		return true;
	}
	
	private void sleep(int amt){
		try {
			Thread.sleep(amt);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void shutDown() throws IOException {
		reset();
		stop();
		closeConnection();
	}
}
