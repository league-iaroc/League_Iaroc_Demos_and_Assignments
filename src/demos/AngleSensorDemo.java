package demos;

import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class AngleSensorDemo extends IRobotAdapter {
	Sonar sonar = new Sonar();
	
	public AngleSensorDemo(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		AngleSensorDemo rob = new AngleSensorDemo(base);
		rob.setup();
		while(rob.loop()){}
		rob.shutDown();
		
	}

	
	
	private void setup() throws Exception {
		
	}
	
	//int to keep track of the total distance the robot has traveled 
	int totalAngle = 0;
	private boolean loop() throws Exception{
		
		readSensors(100);//<--gotta read them sensors
		
		driveDirect(-200, 200);
		
		//getAngle() will return the angle the robot has turned since the
		//last call to getAngle().
		//Can you figure out the unit of measurement?
		totalAngle += getAngle();
		
		if(totalAngle >= 1000){
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