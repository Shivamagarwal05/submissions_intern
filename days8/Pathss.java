import java.util.*;
import java.io.*;
import java.nio.file.*;
import static java.nio.file.StandardCopyOption.*;
class Pathss
{
	public static void main(String[] args) throws Exception
	{
		Path p = Paths.get("shivam.txt");
                System.out.println("File Name:"+p.getFileName());
		
		Path p1 = Paths.get("shivam1");
                Files.createDirectory(p1);
                Path p2 =  p1.resolve("shivam2.txt");
                 System.out.println(p2.toAbsolutePath());
                 Files.createFile(p2.toAbsolutePath());
		 String s="hello how are you";
		 byte b[]=s.getBytes();
                 Files.write(p2,b);
               //  Files.copy(p2,p,REPLACE_EXISTING);
		 Files.move(p2,p,REPLACE_EXISTING);
                 System.out.println(Files.readAttributes(p,"*"));
                 FileReader fr = new FileReader(p.toString());
                 BufferedReader br = new BufferedReader(fr);
                 String str;
                 while((str = br.readLine())!=null)
                 {
		     System.out.println(str);
                  }
                  br.close();
	}
}
               