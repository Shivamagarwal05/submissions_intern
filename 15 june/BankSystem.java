import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter; 
class Td{
	static String getDateTime()
	{
		//System.out.println(new java.util.Date());
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
	void create_Account()
	{
                Random rand = new Random();
		long account_no = rand.nextInt(100000);
		System.out.println("Enter account holders name :");
                String account_name = input.nextLine();
		map.put(account_no , new Account(account_no , account_name));
                credit(account_no);
		System.out.println("Account created "+"\nAccount no : "+account_no +"\nAccount Holder's Name :"+ account_name+"\nAccount balance:"+ map.get(account_no).account_balance);
		
	}
	void credit(long account_no)
	{
		System.out.println("enter amount to be credited :");
		long credit = input.nextLong();
		if(credit<0)
			System.out.println("Transaction not allowed.");
		else
		{        
			System.out.println("********");
                	
			map.get(account_no).account_balance = map.get(account_no).account_balance + credit;
			map.get(account_no).type.add(Td.getDateTime());
			map.get(account_no).type.add("credited");
			map.get(account_no).trans.add(credit);
                         


		}
	}
        void debit(long account_no)
	{
		
		System.out.println("enter amount to be debited :");
		long debit = input.nextInt();
		if(map.get(account_no).account_balance  == 0 || debit > map.get(account_no).account_balance )
			System.out.println("Transaction not allowed.");
		else
		{

			map.get(account_no).account_balance = map.get(account_no).account_balance - debit;
			map.get(account_no).list.add(Td.getDateTime());
			map.get(account_no).type.add("debited");
                        map.get(account_no).trans.add(-1*debit);
		}

	}
  	boolean verify_Account(long ac_no)
	{
		return	(map.containsKey(ac_no));
	}
        void mini(long acno)
	{       long total = 0;
		Account acon = map.get(acno);
                System.out.println(" Account no. : "+acon.account_no+"\n Account holder's name :"+acon.account_name);
		System.out.println("\tCredit\t"+"Debit\t"+"TotalBalance");
		for(Long l:acon.trans)
		{
			total = total + l;
			if(l<0)
				System.out.println("\t----\t"+l+"\t"+total);
			else
				System.out.println("\t"+l+"\t---\t"+total);                     
		
		}
                 
                
	}	
		
}
class Account
{	
		
	String account_name ;
        long account_no;
	long account_balance;
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> type = new ArrayList<String>();
        ArrayList<Long> trans = new ArrayList<Long>();
	Account(long account ,String account_name)
	{
		this.account_no = account_no;
                this.account_name = account_name;
	}
}
class BankSystem
{	
	
	
	public static void main(String[] args)
	{
		int acno =0 , h;
		Bank b = new Bank();
		Scanner scan = new Scanner(System.in);
       		 do{
			System.out.println("choose operation performed on the accoount");
					System.out.println("1.Account opening ");
					System.out.println("2.credit ");
					System.out.println("3.debit");
					System.out.println("4.print mini statement");
					System.out.println("5.exit");
                           		int ch=scan.nextInt();
 					if(ch!=1 && ch!=5)
					
					  {	System.out.println("enter your Account number");
						acno=scan.nextInt();
					}
					
					switch(ch)
					{
						case 1:
							b.create_Account();
							break;
						case 2:
							if(b.verify_Account(acno))
						        	b.credit(acno);
							else
								System.out.println("Such Account doesn't exists....");
							break;
						case 3:
							if(b.verify_Account(acno))
								b.debit(acno);
							else
								System.out.println("Such Account doesn't exists....");
							break;
						case 4:
							if(b.verify_Account(acno))
								b.mini(acno);
							else
								System.out.println("Such Account doesn't exists....");
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
						
		
/*		
Java 8 Basic features
i)Lambda expressions
ii)Method references
iii)Functional interfaces
iv)Marker Interfaces
v)default method
vi)Streams, New date/Time API
Create a Bank with class varibale
i) Account (Map of type AccNo,Account) Map<AccNo,Account>
Methods :
i)Credit(AccNo);
ii)Debit(AccNo);

Create a class Account with class variable
i) AccHolderName
ii) AccNo
iii)AccountBalance
IV) Mini Statement (ArrayList of String : Will have value "Account Credited/Debited"+ "Transcation date")

Allow user to create Account , Debit, Credit and print Mini Statement.

In case of any confusion , please put you comments if didn't get reply in 2-3 hours then pls call

Attachments
Drag and drop or choose your files

TaskHide completed itemsDelete…
50%
Try to implement lambda expression and Stream API in your java program.
Go through with all above given topics in details.

Add an item…
Add Comment
SA

Write a comment…
Save
ActivityHide Details
AK*/
