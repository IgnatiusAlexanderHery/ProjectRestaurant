package singleton;

import java.util.Random;

public class IdGenerator {
	private static IdGenerator instance = null;
	private Random random;
	
	private IdGenerator() {
		random = new Random();
	}
	
	public static IdGenerator getInstance() {
		if(instance==null) {
			instance = new IdGenerator();
		}
		
		return instance;
	}
	
	public int generateNum() {
		
		return random.nextInt(10000);
	}
}
