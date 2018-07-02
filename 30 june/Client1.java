import java.util.*;
import java.net.*;
import java.io.*;
class Client1
{
	public static void main(String[] args) throws Exception
	{
		Socket socket = new Socket("localhost",6666);
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());  
		DataInputStream din=new DataInputStream(socket.getInputStream());  
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
		String str1,str2;
		System.out.println("Enter the no:");
		str1 = br.readLine();
		dos.writeUTF(str1);
		str2 = din.readUTF();
		System.out.println(str2);
		
	}
	
}
