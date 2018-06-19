import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter; 
import java.util.regex.*;  
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
			map.get(account_nos).datetime.add(Td.getDateTime());
			
			map.get(account_nos).trans.add(credit);
                      


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
			map.get(account_no).datetime.add(Td.getDateTime());
			
			map.get(account_no).trans.add(-1*debit);
                     
		}

	}
  	boolean verify_Account(long ac_no,String pin)
	{
			
		return (map.containsKey(ac_no) && (map.get(ac_no).atm_pin).equals(pin));
				
	}
	
        void mini(long acno)
	{       long total = 0;
		Account acon = map.get(acno);
                System.out.println(" Account no. : "+acon.account_no+"\n Account holder's name :"+acon.account_name+"\nbalance:"+ acon.account_balance);
		System.out.println("\tDate/Time\t"+"\t\tCredit\t"+"Debit\t"+"TotalBalance");
		Object [] dt = (acon.datetime.toArray());
                int i =0;
		for(Long l:acon.trans)
		{
			total = total + l;
			if(l<0)
				System.out.println("\t"+dt[i]+"\t\t----\t"+l+"\t"+total);
			else
				System.out.println("\t"+dt[i]+"\t\t"+l+"\t---\t"+total);                     
			i++;
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
class Account
{	
		
	String account_name ;
        long account_no;
	long account_balance;
	String atm_pin;
	ArrayList<String>datetime = new ArrayList<String>();
	ArrayList<Long> trans = new ArrayList<Long>();
        
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
	public static void main(String[] args)
	{
		
	      Bank b = new Bank();
	
		long acno =0 , h;
			System.out.println("\n\n\n\t\t\t-------------------------Welcome to ATM ------------------------------\n\n");
		
		b.map.put(new Long(10000) , new Account(10000 ,"shivam","1234",500L));
		b.map.get(10000L).trans.add(new Long(500));
		b.map.get(10000L).datetime.add(Td.getDateTime());
			
		b.map.put(10001L , new Account(10001 ,"Devansh","1235",600L));
		b.map.get(10001L).trans.add(new Long(600));
		b.map.get(10001L).datetime.add(Td.getDateTime());
			
		
		b.map.put(1002L, new Account(1002,"pihu","1236",700L));
		
		b.map.get(1002L).trans.add(new Long(700));
		b.map.get(1002L).datetime.add(Td.getDateTime());
			
		b.map.put(10003L , new Account(10003 ,"tom","1237",800L));
		b.map.get(10003L).trans.add(new Long(800));
		b.map.get(10003L).datetime.add(Td.getDateTime());
			
		System.out.println(b.map);
	
		String pin = null;
		Scanner scan = new Scanner(System.in);
		Scanner scan1 = new Scanner(System.in);
       		 do{
			System.out.println("choose operation performed on the accoount");
					
					System.out.println("1.credit ");
					System.out.println("2.debit");
					System.out.println("3.print  passbook");
					System.out.println("4.Pin Management");
					System.out.println("5.Exit");
                           		int ch=scan.nextInt();
 					
					if(ch!=5)
					{
					System.out.println("enter your Account number");
					acno=scan.nextInt();
					System.out.println("Enter account pin:");
				        pin = scan1.nextLine();
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
								b.mini(acno);
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
							System.exit(0);	

						default:
							break;
					}
					
					
			System.out.println("\n\tPress 1 to go back to Previous menu\n");
			h=scan.nextInt();
         	}while(h==1);
	}
}
						
		
