import java.io.*;
import java.util.*;
import java.nio.file.*;
public class Fileio{
    public static void main(String[] args) throws IOException
    {
       
         PrintWriter writer = new PrintWriter(System.out);
          writer.println("Hello..I am shivam");
          writer.println("I love playing..");
          writer.println("demo");
          writer.append(" starts\n");
          writer.print(100.15+"\n");
          writer.println("Creating a text file....named shivam:");
          
          Path p = Paths.get("shivam.txt");
          Files.createFile(p);
          writer.println("file created ...  file name :"+p.getFileName());
          writer.flush();
          writer.close();
          
     }
}
    
         
         
          

          
    