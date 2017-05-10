package org.jointheleague.ecolban.cleverrobot;
/*********************************************************************************************
 * Vic's ultrasonic sensor running with Erik's Clever Robot for Pi
 * version 0.9, 170227
 **********************************************************************************************/
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinInput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Sonar
{
    private long startTime;
    private long stopTime;
    final GpioController gpio = GpioFactory.getInstance();
    private GpioPinDigitalOutput rightStrobe = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02);// Strobe...pin
    // 13...right
    private GpioPinInput rightEcho = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05); // Echo...pin
    // 18...right
    private GpioPinDigitalOutput leftStrobe = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);// Strobe...pin
    // 12...left
    private GpioPinInput leftEcho = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04); // Echo...pin
    // 16...left
    private GpioPinDigitalOutput centerStrobe = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);// Strobe...pin
    // 11...center
    private GpioPinInput centerEcho = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03); // Echo...pin
    // 15...center
    private int rightFilteredDistance;
    private int leftFilteredDistance;
    private int centerFilteredDistance;

    public Sonar()
    {
	rightEcho.addListener(new GpioPinListenerDigital()
	    {
		    @Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event)
		{
		    if (event.getState() == PinState.HIGH)
			{
			    startTime = System.nanoTime();
			}
		    if (event.getState() == PinState.LOW)
			{
			    stopTime = System.nanoTime();
			}
		    int distance = (int) ((((stopTime - startTime) / 1e3) / 2) / 29.1); // cm
		    if (distance > 0 && distance < 1000)
			{
			    rightFilteredDistance = distance;
			}
		    if (distance > 1000)
			{
			    return;
			}
		}
	    });
	leftEcho.addListener(new GpioPinListenerDigital()
	    {
		    @Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event)
		{
		    if (event.getState() == PinState.HIGH)
			{
			    startTime = System.nanoTime();
			}
		    if (event.getState() == PinState.LOW)
			{
			    stopTime = System.nanoTime();
			}
		    int distance = (int) ((((stopTime - startTime) / 1e3) / 2) / 29.1); // cm
		    if (distance > 0 && distance < 1000)
			{
			    leftFilteredDistance = distance;
			}
		    if (distance > 1000)
			{
			    return;
			}
		}
	    });
	centerEcho.addListener(new GpioPinListenerDigital()
	    {
		    @Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event)
		{
		    if (event.getState() == PinState.HIGH)
			{
			    startTime = System.nanoTime();
			}
		    if (event.getState() == PinState.LOW)
			{
			    stopTime = System.nanoTime();
			}
		    int distance = (int) ((((stopTime - startTime) / 1e3) / 2) / 29.1); // cm
		    if (distance > 0 && distance < 1000)
			{
			    centerFilteredDistance = distance;
			}
		    if (distance > 1000)
			{
			    return;
			}
		}
	    });
    }

    public int readSonar(String position) throws InterruptedException
    {
	if (position.equals("right"))
	    {
		rightStrobe.low();
		rightStrobe.high();
		rightStrobe.low();
		return rightFilteredDistance;
	    }
	if (position.equals("left"))
	    {
		leftStrobe.low();
		leftStrobe.high();
		leftStrobe.low();
		return leftFilteredDistance;
	    }
	if (position.equals("center"))
	    {
		centerStrobe.low();
		centerStrobe.high();
		centerStrobe.low();
		return centerFilteredDistance;
	    }
	return -1;
    }
}