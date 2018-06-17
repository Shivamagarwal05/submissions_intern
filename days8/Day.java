import java.nio.file.*;
import static java.nio.file.StandardCopyOption.*;
import java.util.*;
import java.io.*;
class Day
{	
        public static void main(String args[]) throws Exception 
	{  
		
		Path p = Paths.get("shivam.txt");
		FileWriter fw = new FileWriter("shvam.txt",true);
                BufferedWriter writer = new   BufferedWriter(fw);
                writer.write("shivam agarwal....i love playing football..");
                writer.newLine();
		writer.write("100003087646575756");
		writer.newLine();
                writer.close();
                
               
               
		System.out.println(Files.getAttribute(p,"lastModifiedTime"));
                FileReader f1 = new FileReader("shvam.txt");
                BufferedReader reader = new BufferedReader(f1);
                String str =  reader.readLine();
                while(str != null)
		{
			 System.out.println(str); 
		         str =  reader.readLine();
		}
                
                reader.close();

                   
	}
}            		