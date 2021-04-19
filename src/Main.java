import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Main extends PathOperations{
	public static BufferedImage myImage; //poza originala
	static double contrast = 1.0;
	static String sourcePath = null; //calea sursei
	static String destinationPath = null; //calea destinatiei
	static long startTime3; //timpul de incepere a etapei 3
	
	public static void readImage(){ // citirea imaginii pe baza caii sursei
		File f = null;
		try {
	    	f = new File(sourcePath);
	        myImage = ImageIO.read(f);
	        
	    } catch (IOException e) {
	    	System.out.println(e);
	    }
		
	}
	//polimorfism
	public void NrBiti(){
		System.out.println("Numarul de biti este: " + myImage.getHeight()*myImage.getWidth());
	}
	public void NrBiti(int height, int width){
		System.out.println("Nr. de biti este: " + height * width);
		
	}
		//functie cu varargs ce poate fi folosita pentru specificarea caii sursei sau destinatiei
	//first_str reprezinta partitia (C sau D de exemplu)
	//final_str reprezinta numele imaginii cu tot cu extensie
	//restul parametrilor, reprezinta fisierele de la partitie la imagine

	public String Path(String first_str, String final_str, String ...str){
        String result = new String();
        result = result + first_str +":/";
    
        for(String i:str)
            result = result+ i + "/";
            
            result = result + final_str;
        return result;
    }
	
	public void readContrast(){
		double value;
		Scanner myInput = new Scanner( System.in );
		do{
			System.out.print( "Introduceti valoarea pentru contrast: " );
		    value = myInput.nextDouble();
			
		}
		while(value < -255 || value > 255); //nu putem introduce o valoare din afara intervalului
		
		contrast = value;
	}
	

	public static void main(String[] args) throws IOException {
		
		//citire valoare contrast de la tastatura
		Main aux = new Main();
		aux.readContrast();
		
		
		
		
		
		//etapa1
		
		long startTime1 = System.currentTimeMillis(); //timpul curent
		
		if(args.length == 0 ){
			sourcePath = aux.srcRead(); //functie din PathOperations
			destinationPath = aux.dstRead(); //functie implementata in PathOperations
		}
		else{ //citire sursa si destinatie din linia de comanda
			 sourcePath = args[0]; //sursa
			 destinationPath = args[1]; //destinatia
		}
		
		
		long endTime1 = System.currentTimeMillis();
		System.out.println("\nCitirea sursei si a destinatiei a durat: "+ (endTime1 - startTime1)+ " milisecunde.\n");
		//diferenta dintre cele doua va da timpul petrecut la etapa 1. Analog si la celelalte etape
		
		//PROBA FUNCTIE VARARGS
		String a = aux.Path("D", "car.bmp", "poze");
		System.out.println(a);
		
		
		
		
		//etapa2
		long startTime2 = System.currentTimeMillis();
		readImage(); //functie statica, nu necesita obiect
		long endTime2 = System.currentTimeMillis();
		System.out.println("\nCitirea imaginii a durat: "+ (endTime2 - startTime2)+ " milisecunde.\n");
		
		
		//etapa3+4
		
		startTime3 = System.currentTimeMillis();
		Buffer buff = new Buffer();
		Producer p1 = new Producer(buff);
		Consumer c1 = new Consumer(buff);
		p1.start(); //porneste producer
		c1.start(); //porneste consumer
		
		//timpii pentru etapele 3 si 4 sunt calculati in threadul consumer
		
		
	}


	
	
	
}
