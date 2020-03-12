package livraison;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.PropertyResourceBundle;

public class MyProperty {
	
	public static Enumeration<String> getProperty(String urlFichier) throws FileNotFoundException, IOException {
		String key;
		//File file=new File("fichier.properties");
		//BufferedReader reader = new BufferedReader(new FileReader(file));

		PropertyResourceBundle rb=new PropertyResourceBundle(new FileInputStream(urlFichier));
		Enumeration<String> curs=(Enumeration<String>) rb.getKeys();	   	
		
//		while(curs.hasMoreElements()) {
//			key=curs.nextElement();
//			System.out.println(key+"="+rb.getString(key));					
//		}
		
		return curs;
	}

}
