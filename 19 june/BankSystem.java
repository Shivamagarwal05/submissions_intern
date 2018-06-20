import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter; 
import java.util.regex.*;  
import java.io.*;
import java.nio.*;
class Td{
	static String getDateTime()
	{
		
		LocalDateTime date = LocalDateTime.now();
                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String str = df.format(date);
	
		return str;
	}
}

class Bank
{
       
	Scanner input = new Scanner(System.in);
	TreeMap<Long,Account> map = new TreeMap<Long,Account>();	
	
	
	void credit(long account_nos)
	{
		System.out.println("enter amount to be credited :");
		long credit = input.nextLong();
		if(credit<0)
			System.out.println("Transaction not allowed.");
		else
		{        
			System.out.println("********");
                	
			map.get(account_nos).account_balance = map.get(account_nos).account_balance + credit;
			
			
			map.get(account_nos).trans.add("\t"+Td.getDateTime()+"\t\t"+credit+"\t----\t"+map.get(account_nos).account_balance);
                      


		}
	}
        void debit(long account_no)
	{
		
		System.out.println("enter amount to be debited :");
		long debit = input.nextLong();
		if(map.get(account_no).account_balance  == 0 || debit > map.get(account_no).account_balance )
			System.out.println("Transaction not allowed.");
		else
		{

			map.get(account_no).account_balance = map.get(account_no).account_balance - debit;
			
			map.get(account_no).trans.add("\t"+Td.getDateTime()+"\t\t----\t"+(-1*debit)+"\t"+map.get(account_no).account_balance);
			
			
                     
		}

	}
  	boolean verify_Account(long ac_no,String pin)
	{
			
		return (map.containsKey(ac_no) && (map.get(ac_no).atm_pin).equals(pin));
				
	}
	
        void passbook(long acno)
	{      
		Account acon = map.get(acno);
                System.out.println(" Account no. : "+acon.account_no+"\n Account holder's name :"+acon.account_name+"\nbalance:"+ acon.account_balance);
		System.out.println("\tDate/Time\t"+"\t\tCredit\t"+"Debit\t"+"TotalBalance");
		
		for(String l:acon.trans)
		{
			System.out.println(l);
		}
                 
                
	}	
        void pinManagement(long acno)
	{
		int i,j;
		
               
		System.out.println("Enter your new pin:");
                
		String str = input.nextLine();
		String confin;
		if(Pattern.compile("\\d{4}").matcher(str).matches())
		{
			System.out.println("Confirm your new pin:");

			confin = input.nextLine();
			if(str.equals(confin))
			{
			 		map.get(acno).atm_pin =str;
					System.out.println("pin set .");
			}
			else
      			System.out.println("Pin not confirmed..Try Again");	
		}
                else
			System.out.println("Enter a valid pin :(4 digit)");
		}
		
	
}
class FileHandler
{
		
		static void addAccount(Bank b) throws IOException
		{
			Scanner ins = new Scanner(System.in);
			Scanner scan = new Scanner(System.in);
			long account_no;
			String account_name;
			String pin;
			

			File direc = new File("files");
                        if(!direc.exists())
			{
				direc.mkdir();
			}
			System.out.println("Enter account no:");
			account_no = ins.nextLong();
			ins.nextLine();
			System.out.println("Enter account holder name:");
			account_name = scan.nextLine();
			System.out.println("Enter account balance:");

			long acb = ins.nextLong();
			ins.nextLine();
			File fnew = new File(direc,account_no +"_"+ account_name+".txt");
			fnew.createNewFile();
			FileWriter fw = new FileWriter(fnew); 
			BufferedWriter br = new BufferedWriter(fw);
			System.out.println("enter pin for the account");
			  pin= scan.nextLine();
			br.write("Account pin-"+pin);
                        br.newLine();
			br.write("Account balance-"+Long.toString(acb));
			 br.newLine();
			br.write("\tDate/Time\t"+"\t\tCredit\t"+"Debit\t"+"TotalBalance");
			 br.newLine();
			br.write("\t"+Td.getDateTime()+"\t\t"+acb+"\t---\t"+acb);
			System.out.println("Account Added");
			br.close();
		}
	static void open(Bank b) throws IOException
	{
		File direc = new File("files");
		String[] account_list = direc.list();
		
		for(String str3:account_list)
		{
			BufferedReader br = new BufferedReader(new FileReader(".\\files\\"+str3));
			String[] temp = br.readLine().split("-");
			
			long acbal = Long.parseLong((br.readLine().split("-"))[1]);
			String[] splitted = str3.replace(".","_").split("_");
			b.map.put(Long.parseLong(splitted[0]),new Account(Long.parseLong(splitted[0]),splitted[1],temp[1],acbal));
			Account fac = b.map.get(Long.parseLong(splitted[0]));	
			br.readLine();
			String tra = br.readLine();
				
			while(tra!=null)
			{
				
				fac.trans.add(tra);
				tra = br.readLine();
			}

			br.close();
		}
	}
	static void close(Bank b) throws IOException
	{
		File direc = new File("files");
		String[] account_list = direc.list();
		
		for(String str3:account_list)
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter(".\\files\\"+str3));
			
			
			String[] splitted = str3.replace(".","_").split("_");
				
			Account a = b.map.get(Long.parseLong(splitted[0]));
			bw.write("Account pin-"+a.atm_pin);
			bw.newLine();
			bw.write("Account balance-"+Long.toString(a.account_balance));
			bw.newLine();
			bw.write("\tDate/Time\t"+"\t\tCredit\t"+"Debit\t"+"TotalBalance");
			bw.newLine();
		
			for(String temp:a.trans)
			{
				bw.write(temp);
				bw.newLine();
			}

			bw.close();
		}
		
	}	
	 static void deleteAccount(long acno,Bank b)
	{
		Account an = b.map.get(acno);
		  File f = new File(".\\files\\"+an.account_no+"_"+an.account_name+".txt");
		f.delete();
		System.out.println("Account deleted.");
	}
}
			
class Account
{	
		
	String account_name ;
        long account_no;
	long account_balance;
	String atm_pin;
	
	ArrayList<String> trans = new ArrayList<String>();
        
	Account(long account ,String account_name, String pin,long account_balance)
	{
		this.account_no = account;
                this.account_name = account_name;
		this.atm_pin = pin;
		this.account_balance=account_balance;
	}
}
class BankSystem
{	
	public static void main(String[] args) throws IOException
	{
		
	      Bank b = new Bank();
	      File dirac = new File("files");
		if(dirac.list().length==0)
		 {
			System.out.println("you have to add account in file");
			FileHandler.addAccount(b);
		}
		
	
		long acno =0 , h;
			System.out.println("\n\n\n\t\t\t-------------------------Welcome to ATM ------------------------------\n\n");
		
		FileHandler.open(b);
	
		String pin = null;
		Scanner scan3 = new Scanner(System.in);
		Scanner scan4 = new Scanner(System.in);
       		 do{
			System.out.println("choose operation performed on the accoount");
					
					System.out.println("1.credit ");
					System.out.println("2.debit");
					System.out.println("3.print  passbook");
					System.out.println("4.Pin Management");
					System.out.println("5.Add account");
					System.out.println("6.Delete Account.");
					System.out.println("7.Exit");
                           		int ch=scan3.nextInt();
 					
					if(ch==1 || ch==2 || ch==3 || ch==4 || ch==6)
					{
					System.out.println("enter your Account number");
					acno=scan3.nextInt();
					System.out.println("Enter account pin:");
				        pin = scan4.nextLine();
					}
					
					
					
					switch(ch)
					{
						
						case 1:
							if((b.verify_Account(acno,pin)))
								b.credit(acno);
							else
								 System.out.println("Such Account doesn't exists...or wrong pin");
							break;
						case 2:
							if((b.verify_Account(acno,pin)))
								 b.debit(acno);
							else
								 System.out.println("Such Account doesn't exists...or wrong pin");
						        	
	                                              
							break;
						case 3:
							 if((b.verify_Account(acno,pin)))
								b.passbook(acno);
							else
								 System.out.println("Such Account doesn't exists...or wrong pin");
								
							
							break;
						case 4:

								if(b.verify_Account(acno,pin))
								   b.pinManagement(acno);
							        else
								 System.out.println("Such Account doesn't exists...or wrong pin");
								
						
							         break;
						case 5:
							FileHandler.addAccount(b);
							b.map.clear();
							FileHandler.open(b);	
							break;
						
						case 6: 
							if(b.verify_Account(acno,pin))
							{
								FileHandler.deleteAccount(acno,b);
								b.map.clear();
								FileHandler.open(b);
							}
							else
								 System.out.println("Such Account doesn't exists...or wrong pin");
								
							break;
						case 7:
							FileHandler.close(b);
							System.exit(0);

						default:
							break;
					}
					
					
			System.out.println("\n\tPress 1 to go back to Previous menu\n");
			h=scan3.nextInt();
         	}while(h==1);
		FileHandler.close(b);
	}
}
						
		
