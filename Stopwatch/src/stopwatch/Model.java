package stopwatch;

import java.time.Duration;
import java.time.Instant;

public class Model implements Runnable {
	private boolean go;
	private int hours, minutes, seconds, miliSeconds, miliSecondsTmp;
	private String elapsedTime = "00:00,000";
	private Instant start_time, instant_time, stop_time, previous_lap_time;
	int i = 0; //Lap iterator
	
	public Model() {
		go = false;
	}
	
	@Override
	public void run() {
		start_time = Instant.now();
		previous_lap_time = start_time;
		while (go == true) {
			instant_time = Instant.now();
			miliSeconds = (Duration.between(start_time, instant_time).getNano() / 1000000);
			if(hours == 0) elapsedTime = String.format("%02d:%02d,%03d", minutes, seconds, miliSeconds);
			else elapsedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
			
			if (miliSeconds < miliSecondsTmp) {
				seconds++;
			}
			if (seconds >= 60) {
				minutes++;
				seconds = 0;
			}
			
			if (minutes >= 60) {
				hours++;
				minutes = 0;
			}
			if (hours >= 24) setZero();
			miliSecondsTmp = miliSeconds;
		}
		stop_time = Instant.now();
		miliSeconds = (Duration.between(start_time, stop_time).getNano() / 1000000);
		
	}
	
	void setZero() {
		miliSecondsTmp = 0;
		miliSeconds = 0;
		seconds = 0;
		minutes = 0;
		hours = 0;
	}
	
	public String lap() {
			i++;
			int lapMiliSeconds = (Duration.between(previous_lap_time, instant_time).getNano() / 1000000);
			int lapSeconds = (int) Duration.between(previous_lap_time, instant_time).getSeconds();
			int lapHours = lapSeconds / 3600;
			lapSeconds %= 3600;
			int lapMinutes = lapSeconds / 60;
			lapSeconds %= 60;
			previous_lap_time = Instant.now();
			if(lapHours == 0) return String.format("Lap %-20d %02d:%02d,%03d", i, lapMinutes, lapSeconds, lapMiliSeconds);
			else return String.format("Lap %-10d %d hour(s) %02d:%02d,%03d", i, lapMinutes, lapSeconds, lapMiliSeconds);
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

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}	
	
	
}
