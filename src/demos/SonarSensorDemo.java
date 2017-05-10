package demos;

/*********************************************************************************************
 * Vic's ultrasonic sensor running with Erik's Clever Robot for Pi
 * version 0.9, 170227
 **********************************************************************************************/
import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class SonarSensorDemo extends IRobotAdapter {
	Sonar sonar = new Sonar();
	
	public SonarSensorDemo(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		SonarSensorDemo rob = new SonarSensorDemo(base);
		rob.setup();
		while(rob.loop()){}
		rob.shutDown();
		
	}

	private void setup() throws Exception {
		
	}
	
	private boolean loop() throws Exception{
		//sonar.readSonar(String) returns the distance read from the sonar sensor
		//"left", "right", and "center" are used to read that respective sensor.
		
		System.out.println("LEFT SONAR: " + sonar.readSonar("left"));
		sleep(200);//<--the sensors work better with a short sleep to allow time for them to get feedback
		System.out.println("RIGHT SONAR: " + sonar.readSonar("right"));
		sleep(200);
		System.out.println("CENTER SONAR: " + sonar.readSonar("center"));
		sleep(1000);
		
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