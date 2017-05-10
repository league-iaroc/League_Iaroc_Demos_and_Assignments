package demos;

import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class MoveAndStopDemo extends IRobotAdapter {
	Sonar sonar = new Sonar();
	
	public MoveAndStopDemo(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		MoveAndStopDemo rob = new MoveAndStopDemo(base);
		rob.setup();
		while(rob.loop()){}
		rob.shutDown();
		
	}

	
	
	private void setup() throws Exception {
		//driveDirect moves the robot
		//the first parameter is the left wheel speed
		//the second parameter is the right wheel speed
		driveDirect(-500, 500);
		
		//sleep(int) pauses the program for the indicated amount of time
		//time is in milliseconds (1000ms = 1s)
		sleep(2000);
		
		//robot must explicitly be told to stop or else it will go forever
		driveDirect(0, 0);
	}
	
	private boolean loop() throws Exception{
		
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