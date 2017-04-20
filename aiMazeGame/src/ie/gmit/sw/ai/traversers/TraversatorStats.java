package ie.gmit.sw.ai.traversers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ie.gmit.sw.ai.maze.Nodes.Node;

public class TraversatorStats {
	private static List<Node> paths = new ArrayList<Node>();
	public static void printStats(Node node, long time, int visitCount){
		double depth = 0;
		paths.clear();
		
		paths.add(node);
		while (node != null){			
			node = node.getParent();
			if (node != null) node.setColor(Color.YELLOW);
			depth++;			

		}
        System.out.println("Visited " + visitCount + " nodes in " + time + "ms.");
        System.out.println("Found goal at a depth of " + String.format("%.0f", depth));
        System.out.println("EBF = B* = k^(1/d) = " + String.format("%.2f", Math.pow((double) visitCount, (1.00d / depth))));           
		
	}
	
	public static List<Node> getPaths() {
		Collections.reverse(paths);
		return paths;
	}
}