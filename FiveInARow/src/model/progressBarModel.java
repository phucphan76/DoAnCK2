package model;

public class progressBarModel {
	private int progress; // Current progress value (0-100)
	private final int maxValue; // Maximum progress value

	

	public progressBarModel() {
		maxValue=200;
	}

	public synchronized int getProgress() { // Thread-safe access to progress
	    return progress;
	}
	
	public void setProgress(int value) {
		this.progress = value;
	}

	public synchronized void updateProgress(int delta) { // Thread-safe progress update
	    progress = Math.min(progress + delta, maxValue); // Limit progress to max value
	    // Notify any observers about the progress change (optional)
	}
	
	public int getMaxValue() {
		return maxValue;
	}
}
