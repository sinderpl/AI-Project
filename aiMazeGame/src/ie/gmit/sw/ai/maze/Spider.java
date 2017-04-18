package ie.gmit.sw.ai.maze;
import java.util.concurrent.*;

//A spider class, it extends Node so that it can be applied to the Maze array.
public class Spider extends Node{
	private ExecutorService pool;
	public Spider(int row, int col, int nodeType, Object lock, ThreadPool pool, Node[][] maze) {
		super(row, col, nodeType);
		this.pool = pool.getPool();
		
		pool.submit(() ->{
			try{
				
			}catch (Exception e) {
				System.out.println(e);
			}
		});
				
				
	}
	
}
