package demos;

import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class BumpSensorDemo extends IRobotAdapter {
	Sonar sonar = new Sonar();
	
	public BumpSensorDemo(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		BumpSensorDemo rob = new BumpSensorDemo(base);
		rob.setup();
		while(rob.loop()){}
		rob.shutDown();
	}

	private void setup() throws Exception {

	}
	
	private boolean loop() throws Exception{
		readSensors(100); //<--This reads all of the robot's sensors
		//this must be called at the top of loop if you are using the sensors
		
		driveDirect(200, 200);
		
		//isBumpRight() and isBumpLeft() return true if they have been bumped
		//since the last call to readSensors
		//there is not an isBumpCenter() method so you have to check left and right
		if(isBumpLeft() || isBumpRight()){
			driveDirect(-100, -100);
			sleep(500);
			driveDirect(-500, 500);
			sleep(3000);
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