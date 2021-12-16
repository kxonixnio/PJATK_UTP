package zad1;

import java.util.concurrent.atomic.AtomicInteger;

public class StringTask implements Runnable {

	public Thread thread;
	private volatile State state;
	private volatile String text;
	private volatile String resultText;
	private final AtomicInteger operationsCounter = new AtomicInteger(0);

	public StringTask(String string, int i) {
		state = State.CREATED;
		text = string;
		resultText = "";
		operationsCounter.set(i);
	}

	public Thread getThread() {
		return thread;
	}

	public String getResult() {
		return resultText;
	}

	public State getState() {
		return state;
	}

	public void start() {
		thread = new Thread(this);
		state = State.RUNNING;
		thread.start();
	}

	public void abort() {
		state = State.ABORTED;
		thread.interrupt();
	}

	public boolean isDone() {
		return state == State.READY || state == State.ABORTED;
	}

	@Override
	public void run() {
		stringConcentation();
	}

	private void stringConcentation() {
		while (operationsCounter.get() != 0 && state == State.RUNNING && !thread.isInterrupted()) {
			resultText += text;
			operationsCounter.decrementAndGet();
		}
		if (operationsCounter.get() == 0) {
			state = State.READY;
		} else {
			state = State.ABORTED;
		}
	}
}


//https://stackoverflow.com/questions/4818699/practical-uses-for-atomicinteger