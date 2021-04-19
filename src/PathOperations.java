import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PathOperations implements Operations {


	@Override
	public String dstRead() {
		String auxPath = null;
		BufferedReader dst = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Introduceti destinatia: ");
    	try {
			auxPath = dst.readLine();
		} catch (IOException e) {
			System.out.println(e);
		}
    	return auxPath;
    }

	@Override
	public String srcRead() {
		String auxPath = null;
		BufferedReader src = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Introduceti sursa: ");
		try {
    		auxPath = src.readLine();
		} catch (IOException e) {
			System.out.println(e);
		}
		return auxPath;
	}		
}
