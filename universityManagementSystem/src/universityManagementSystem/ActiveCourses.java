package universityManagementSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ActiveCourses {
	String sub1,sub2,sub3,sub4,sub5,pause="true";
	public ActiveCourses()
	{
		this.sub1 = "Ecommerce";
		this.sub2 = "HCI";
		this.sub3 = "AI";
		this.sub4 = "SDA";
		this.sub5 = "CCN";
	}
	public String getSub1()
	{
		return sub1;
	}
	public void setSub1(String sub1)
	{
		this.sub1 = sub1;
	}
	public String getSub2()
	{
		return sub2;
	}
	public void setSub2(String sub2)
	{
		this.sub2 = sub2;
	}
	public String getSub3()
	{
		return sub3;
	}
	public void setSub3(String sub3)
	{
		this.sub3 = sub3;
	}
	public String getSub4()
	{
		return sub4;
	}
	public void setSub4(String sub4)
	{
		this.sub4 = sub4;
	}
	public String getSub5()
	{
		return sub5;
	}
	public void setSub5(String sub5)
	{
		this.sub5 = sub5;
	}
	synchronized void displayCourses()
	{
		Scanner sc = new Scanner(System.in);
		UniversityManagementSystem ums = new UniversityManagementSystem();
		char choice;
		System.out.println("Courses You're currently enrolled in are : ");
		System.out.println("\n\nSubjetc 1 : "+getSub1());
		System.out.println("\nSubjetc 2 : "+getSub2());
		System.out.println("\nSubjetc 3 : "+getSub3());
		System.out.println("\nSubjetc 4 : "+getSub4());
		System.out.println("\nSubjetc 5 : "+getSub5());
		
		System.out.println("Enter y if you want to make a change");
		choice = sc.next().charAt(0);
		if(choice=='y')
		{
			try
			{
				this.pause = "false";//in order user doesn't want to edit subjects.. thread 2 will be excecuted but nithing will hapen.
				wait();
			}catch(Exception e)
			{
			}
			System.out.println("Updated courses : ");
			System.out.println("\n\nSubjetc 1 : "+getSub1());
			System.out.println("\nSubjetc 2 : "+getSub2());
			System.out.println("\nSubjetc 3 : "+getSub3());
			System.out.println("\nSubjetc 4 : "+getSub4());
			System.out.println("\nSubjetc 5 : "+getSub5());
		}
	}
	synchronized void editingCourseNames()
	{
		if(pause=="false")
		{
			Scanner sc = new Scanner(System.in);
			int choice = 0;
			char ch='y';
			String sub;
			System.out.println("Enter name subject name with which you wwant to replace");
			sub = sc.nextLine();
			System.out.println("\nPress 1 if you want to edit subject # 1");
			System.out.println("Press 2 if you want to edit subject # 2");
			System.out.println("Press 3 if you want to edit subject # 3");
			System.out.println("Press 4 if you want to edit subject # 4");
			System.out.println("Press 5 if you want to edit subject # 5");
			while(ch=='y')
			{
				try 
				{    //to check user should enter an int, not any string/char.
					choice = sc.nextInt();
				} 
				catch (InputMismatchException e) 
				{
					System.out.println("Entered value is not an integer");
					sc.next(); // to clear the previous input data. other vise program will keep printing ^ data.
				}
				if(choice>=1&&choice<=5)
				{
					break;
				}
				else
					System.out.println("Enter a correct choice");
			}
			switch(choice)
			{
				case 1:
				{
					setSub1(sub);
					break;
				}
				case 2:
				{
					setSub2(sub);
					break;
				}
				case 3:
				{
					setSub3(sub);
					break;
				}
				case 4:
				{
					setSub4(sub);
					break;
				}
				case 5:
				{
					setSub5(sub);
					break;
				}
			}
			notify();
		}
	}
}

class Thread1 extends Thread
{
	ActiveCourses ac;
	Thread1(ActiveCourses ac)
	{
		this.ac=ac;
	}
	public void run()
	{
		ac.displayCourses();
	}
}
class Thread2 extends Thread
{
	ActiveCourses ac;
	Thread2(ActiveCourses ac)
	{
		this.ac=ac;
	}
	public void run()
	{
		ac.editingCourseNames();
	}
}