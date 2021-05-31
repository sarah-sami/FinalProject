package universityManagementSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

public class UniversityManagementSystem {

	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(System.in);
		File file;
		ActiveCourses ac = new ActiveCourses();
		Thread1 t1;
		Thread2 t2;
		FailedSubjects fs = new FailedSubjects();
		String rollno,password,data="";
		String[] ff = new String[3]; // to  store file data
        String[] s ; // to store temporary data
		int i;
		char ch = 'y';
		int q=0,choice = 0; // to store position of rollno
		
		System.out.println("Enter Your Roll Number.");
        while(ch=='y')
        {
        
        	rollno = sc.nextLine();
        	file = new File("username.txt");
        	try (Scanner InputFile = new Scanner(file)) 
        	{ // to start reading the file.
        		for (i = 0; i <3; i++)
        		{
        			ff[i] = InputFile.nextLine();//to read file line by line
        			s = ff[i].split(",");//split every line.
        			if(s[0].equals(""+rollno))
        			{//to chech if rolll occurs at that position.
        				data = ff[i];//to store information of that student.
        			}
        		}
        		if(data=="")
        		{
        			System.out.println("Enter a valid roll no");
        		}
        		else
        		{
        			InputFile.close();
        			break;
        		}
        	}
        }
        s = data.split(",");//split data to manupulate score of test 1
        System.out.println("Enter Your Pin.");
        while(ch =='y')
        {    
        	password = sc.nextLine();
        	if(s[1].equals(password))
        	{
        		System.out.println("Wellcome to the System.");
        		ch =1;
            }
        	else
        	{
        		System.out.println("Wrong Pin.");
        		System.out.println("Enter correct pin.");
        	}
        }
        
        System.out.println("Press 1 if you want to see your active courses.");
        System.out.println("Press 2 if you want to see your Fialed subjects.");
        System.out.println("Press 3 if you want to exit.");
        ch='y';
        while(ch=='y')
        {
        	try 
            {    //to check user should enter an int, not any string/char.
        		choice = sc.nextInt();
            } catch (InputMismatchException e) 
            {
                System.out.println("Entered value is not an integer");
                sc.next(); // to clear the previous input data. other vise program will keep printing ^ data.
            }
        	if(choice>=1&&choice<=3)
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
        	t1 = new Thread1(ac);
        	t2 = new Thread2(ac);
        	t1.start();
        	t2.start();
        	break;
        }
        case 2:
        {
        	fs.displayFailedSubjects();
        	break;
        }
        case 3:
        	break;
        }
	}
}
