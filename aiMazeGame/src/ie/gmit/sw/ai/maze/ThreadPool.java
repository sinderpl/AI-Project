package ie.gmit.sw.ai.maze;
import java.util.concurrent.*;

public class ThreadPool{
	private static ExecutorService pool;
	
	public ExecutorService getPool(){
		return pool;
	}
	
	public void createPool(int numberOfThreads){
		pool = Executors.newFixedThreadPool(100);
	}

}
