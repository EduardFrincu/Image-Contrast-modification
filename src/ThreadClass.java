
public abstract class ThreadClass extends Thread{ //clasa abstracta folosita la crearea Producer si a Consumer
	private boolean flag;						  //dar si la reutilizarea de cod
	
	public void setFlag (boolean flag){
		this.flag = flag;
	}
	public boolean getFlag(){
		return flag;
	}
	
	public ThreadClass(boolean flag){
		this.setFlag(flag);
		
		if(flag)
			setName("Consumer");//metoda existenta in clasa Thread.
		else
			setName("Producer");
		System.out.println("Constructor " + getName());
	}
	
	@Override
	public void start(){ //se suprascrie metoda start() din clasa Thread
		System.out.println("Starting " + getName());
		super.start();
	}
	public void run(){
		System.out.println("Running " + getName());
	}	
	
	
}



