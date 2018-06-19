import java.util.*;
import java.util.function.*;
class Person
{
	int pid;
	String name;
	String email;
	int age;
        

        Person(int pid,String name,String email,int age)
	{
		this.pid =pid;
		this.name = name;
		this.email = email;
		this.age = age;
        }
        
	void printPerson()
	{
		System.out.println("person id :"+pid+"\nname:"+name+"\nemail"+email+"\nage"+age);
         }
}
/*interface Checkperson
{	
	Boolean Test(Person p);
}
class Checkpersoneligible implements Checkperson{
	public Boolean Test(Person p)
	{
		return (p.age>22 && p.age<40);
	}
}
*/

	
class Cos
{
	public static void main(String[] args)
	{
	 	ArrayList<Person> list = new ArrayList<Person>();
         	list.add(new Person(123,"shivam","shivamagarwal@gmail.com",20));
	 	list.add(new Person(124,"devansh","devanshagarwal@gmail.com",21));
	 	list.add(new Person(125,"pihu","pihuagarwal@gmail.com",22));
	 	list.add(new Person(126,"Emma","emmaagarwal@gmail.com",23));
         	//Checkperson select = p->(p.age>22 && p.age<40);
			
		
		list.stream().filter(p->(p.age>22 && p.age<40)).forEach(p->p.printPerson());
		
			
		
	 	
	}
         static void checkeligible(Collection<Person> clist,Predicate<Person> select) //using lambda expressions and streams
	{
		
		for(Person p: clist)
		{
			if(select.test(p))
				p.printPerson();
		}
	}
 }       