import java.net.*;
import java.io.*;
class Server1
{
	public static void main(String[] args) throws Exception
	{
		
			ServerSocket sk = new ServerSocket(6666);
			Socket s = sk.accept();
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());  
			DataInputStream din=new DataInputStream(s.getInputStream());  
		  
			String str1 = din.readUTF();
			try
		      	{
			
				dos.writeUTF(Double.toString(2*Double.parseDouble(str1)));
			
		        }catch(Exception e)
			{
			dos.writeUTF("Send a proper number.");
			}
	}
}