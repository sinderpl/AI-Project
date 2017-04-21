package ie.gmit.sw.ai.Sprites;

import javax.imageio.*;

import ie.gmit.sw.ai.FuzzyLogic.Engageable;
import ie.gmit.sw.ai.Traversators.Traversator;

import java.awt.image.*;

public abstract class Sprite implements Engageable{
	public Traversator traversator;
	private String name; //The name of this sprite
	private BufferedImage[] frames; //The set of image frames to animate
 	private int index = 0; //Initial starting index in array
	private char feature;
 	
	public Sprite(String name, String... images) throws Exception{
		this.name = name;
		this.index = 0; //Initialise the starting index to zero
		this.frames = new BufferedImage[images.length]; //Initialise the image frames
		
		for (int i = 0; i < images.length; i++){
			frames[i] = ImageIO.read(new java.io.File(images[i])); //Read in each image as a BufferedImage
		}
	}
	
	public Sprite() throws Exception{
	}
	
	public BufferedImage getNext(){ //Returns the next image frame
		int idx = index;
		if (index < frames.length - 1){
			index++;
		}else{
			index = 0; //Circle back to the start of the array
		}
		return frames[idx]; 
	}
	
	public String getName(){
		return this.name;
	}
	
}
