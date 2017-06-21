package stopwatch;

import java.time.Duration;
import java.time.Instant;

import javax.swing.JList;

public class Model implements Runnable {
	private boolean go;
	private int hours, minutes, seconds, miliSeconds, miliSecondsTmp;
	private String elapsedTime = "00:00,000";
	private Instant start_time, instant_time, stop_time;
	private JList<String> laps;
	
	public Model() {
		go = false;
	}
	
	@Override
	public void run() {
		start_time = Instant.now();
		while (go == true) {
			instant_time = Instant.now();
			miliSeconds = (Duration.between(start_time, instant_time).getNano() / 1000000);
			if(hours == 0) elapsedTime = String.format("%02d:%02d,%03d", minutes, seconds, miliSeconds);
			else elapsedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
			
			if (miliSeconds < miliSecondsTmp) {
				seconds++;
			}
			if (seconds >= 2) {
				minutes++;
				seconds = 0;
			}
			
			if (minutes >= 2) {
				hours++;
				minutes = 0;
			}
			if (hours >= 2) setZero();
			miliSecondsTmp = miliSeconds;
		}
		stop_time = Instant.now();
		miliSeconds = (Duration.between(start_time, stop_time).getNano() / 1000000);
		
	}
	
	void setZero() {
		miliSeconds = 0;
		seconds = 0;
		minutes = 0;
		hours = 0;
	}

	public boolean isGo() {
		return go;
	}

	public void setGo(boolean go) {
		this.go = go;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getMiliSeconds() {
		return miliSeconds;
	}

	public void setMiliSeconds(int miliSeconds) {
		this.miliSeconds = miliSeconds;
	}	

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public String getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}	
	
	
}
