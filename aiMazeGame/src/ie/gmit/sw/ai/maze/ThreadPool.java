package ie.gmit.sw.ai.maze;
import java.util.concurrent.*;

public class ThreadPool{
	private static ExecutorService pool;
	
	public ThreadPool(int threads){
		pool = Executors.newFixedThreadPool(threads);
	}
	public ExecutorService getPool(){
		return pool;
	}
	
	public void createPool(int numberOfThreads){
		pool = Executors.newFixedThreadPool(numberOfThreads);
	}

}
