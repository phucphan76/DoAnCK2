package controller;
public class test {

	public static void main(String[] args) throws InterruptedException {
		while(true) {
			int a = Thread.activeCount();
			System.out.println(a);
			Thread.sleep(3000);
		}
		
	}

}