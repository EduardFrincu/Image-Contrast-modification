import java.awt.Color;


public class Producer extends ThreadClass {
	
	private Buffer myBuffer;
	
	public Producer(Buffer b){
		super(false);
		myBuffer = b;
	}
	
	public void run(){
		super.run();
		
		int width = Main.myImage.getWidth();
		int height = Main.myImage.getHeight();
		
		for(int i = 0; i< height;i++)
			for(int j = 0; j< width; j++)
			{
				Color c = new Color (Main.myImage.getRGB(j, i)); //pentru fiecare pixel din matrice se creaza o varaibila Color (se sparge pixelul dupa culorile componente)
																 //la afisarea unui element de top Color, se va afisa r=[...], g=[...], b = [...];
				myBuffer.put(c,i,j);			//se pune in buffer obiectul creat	
				if((i == height/4-1) && (j==width-1)){
					System.out.println("A fost citit primul sfert din imagine. Ultimul element are indecsii: i = " + i +" si j = "+ j);
					try{
						sleep(1000);
					}
					catch (InterruptedException e) { }
				}
				else if((i == height/2-1) && (j==width-1)){
					System.out.println("A fost citit al doilea sfert din imagine. Ultimul element are indecsii: i = " + i +" si j = "+ j);
					try{
						sleep(1000);
					}
					catch (InterruptedException e) { }
				}
				else if((i == 3*height/4-1) && (j==width-1)){
					System.out.println("A fost citit al treilea sfert din imagine. Ultimul element are indecsii: i = " + i +" si j = "+ j);
					try{
						sleep(1000);
					}
					catch (InterruptedException e) { }
				}
				else if((i == height-1) && (j==width-1)){
					System.out.println("A fost citit ultimul sfert din imagine. Ultimul element are indecsii: i = " + i +" si j = "+ j);
					try{
						sleep(1000);
					}
					catch (InterruptedException e) { }
				}
		}
		System.out.println("Die Producer");
	}
}
