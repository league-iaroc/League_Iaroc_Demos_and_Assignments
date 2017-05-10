package demos;

import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class DistanceSensorDemo extends IRobotAdapter {
	Sonar sonar = new Sonar();
	
	public DistanceSensorDemo(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		DistanceSensorDemo rob = new DistanceSensorDemo(base);
		rob.setup();
		while(rob.loop()){}
		rob.shutDown();
		
	}

	
	
	private void setup() throws Exception {
		
	}
	
	//int to keep track of the total distance the robot has traveled 
	int distanceDriven = 0;
	private boolean loop() throws Exception{
		
		readSensors(100);//<--gotta read them sensors
		
		driveDirect(200, 200);
		
		//getDistance will return the distance traveled in millimeters since the last
		//time readSensors has been called
		distanceDriven += getDistance();
		
		if(distanceDriven >= 1000){
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