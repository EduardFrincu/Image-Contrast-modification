import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Consumer extends ThreadClass {
	private Buffer myBuffer;
	public long endTime3;// folosit la calculul timpului pentru etapa de procesare
	public long endTime4;// folosit la calculul timpului pentru etapa de scriere a imaginii
	
	public Consumer(Buffer b){
		super(true);
		myBuffer = b;
	}
	public void run(){
		super.run();
		int width = Main.myImage.getWidth();
		int height = Main.myImage.getHeight();
		BufferedImage aux = new BufferedImage (width, height, BufferedImage.TYPE_3BYTE_BGR);
		
		Color picture[][] = new Color [height][width]; //matricea cu pixeli ce va fi creata dupa ce se preia toata informatia din buffer,
													   //inainte de a se incepe procesarea
		
		for(int i = 0; i< height;i++)
			for(int j = 0; j< width; j++){
				picture[i][j] = myBuffer.get(i, j); //se preia informatia din buffer
			//Consumer intra in Not Runnable la fiecare sfert de informatie consumata	
				if((i == height/4-1) && (j==width-1)){
					System.out.println("Consumer a primit primul sfert din imagine. Ultimul element este "+ picture[i][j] +" si are indecsii: i = " + i +" si j = "+ j +"\n");
					try{
						sleep(1000);
					}
					catch (InterruptedException e) { }
				}
				else if((i == height/2-1) && (j==width-1)){
					System.out.println("Consumer a primit al doilea sfert din imagine. Ultimul element este "+ picture[i][j] +" si are indecsii: i = " + i +" si j = "+ j+"\n");
					try{
						sleep(1000);
					}
					catch (InterruptedException e) { }
				}
				else if((i == 3*height/4-1) && (j==width-1)){
					System.out.println("Consumer a primit al treilea sfert din imagine. Ultimul element este "+ picture[i][j] +" si are indecsii: i = " + i +" si j = "+ j+"\n");
					try{
						sleep(1000);
					}
					catch (InterruptedException e) { }
				}
				else if((i == height-1) && (j==width-1)){
					System.out.println("Consumer a primit ultimul sfert din imagine. Ultimul element este "+ picture[i][j] +" si are indecsii: i = " + i +" si j = "+ j+"\n");
					try{
						sleep(1000);
					}
					catch (InterruptedException e) { }
				}	
			}
		
		for(int i = 0; i < height;i++)
			for(int j = 0; j< width; j++)
			{
				double factor = (259 * (Main.contrast + 255)) / (255 * (259 - Main.contrast)); //factor de corectie
								
				int red  = (int)(factor * (picture[i][j].getRed()   - 128) + 128); 
				int green = (int)(factor * (picture[i][j].getGreen() - 128) + 128);
				int blue  = (int)(factor * (picture[i][j].getBlue()  - 128) + 128);
				
				if(red > 255) {  //fiecare componenta trebuie sa fie in intervalul [0:255]
	                red = 255;
	            } else if(red < 0) {
	                red = 0;
	            }
	            if(green > 255) {
	                green = 255;
	            } else if(green < 0) {
	                green = 0;
	            }
	            if(blue > 255) {
	                blue = 255;
	            } else if(blue < 0) {
	                blue = 0;
	            }
	            
	            aux.setRGB(j, i, new Color(red,green,blue).getRGB());	  //crearea imaginii rezultate          
	            endTime3 = System.currentTimeMillis();
	            
	            
			}
		File f = null; //scrierea in fisier a imaginii rezultate
		try {
	    	f = new File(Main.destinationPath);
	        ImageIO.write(aux,"bmp",f);
	        
	    } catch (IOException e) {
	    	System.out.println(e);
	    }
		endTime4 = System.currentTimeMillis();

		System.out.println("Die Consumer");
		
		System.out.println("\nProcesarea imaginii a durat: "+ (endTime3 - Main.startTime3)+ " milisecunde.\n");
		System.out.println("\nScrierea imaginii a durat: "+ (endTime4 - endTime3)+ " milisecunde.\n");
		if(f!=null)
			System.out.println("Imaginea a fost modificata cu succes! Aceasta se gaseste la locatia: " + Main.destinationPath);
		
	}
	
}
