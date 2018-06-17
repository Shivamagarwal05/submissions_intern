import java.util.*;
import java.io.*;
class BStreams
{
	public static void main(String args[]) throws IOException
	{
		FileOutputStream f = new FileOutputStream("shivam.txt",true);
		BufferedOutputStream bw = new BufferedOutputStream(f);
               
		String s ="i am here\n";
		byte c[]=s.getBytes();
		bw.write(c,0,c.length);
		
		bw.close();
		FileInputStream f1 = new FileInputStream("shivam.txt");
		BufferedInputStream br = new BufferedInputStream(f1);
		br.read(c,0,4);
                System.out.println((char)c[0]);
		br.close();
		
		
	}
}