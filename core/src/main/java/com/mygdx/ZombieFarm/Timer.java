package com.mygdx.ZombieFarm;

public class Timer {
	boolean done;
	int length;
	int elapsedTime;

	public Timer(int length) {
		this.length = length;
		done = false;
		elapsedTime = 0;
	}

	public void startTimer() {
		elapsedTime = (int) System.currentTimeMillis() / 1000;
		System.out.println();

	}
}
