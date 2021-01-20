package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Function {
	ArrayList<Student> arry=new ArrayList<Student>();  			
	public void Function()           //Read the file and store the student information in the file in the array list
	{
		this.readfile();
	}
	//Search student information according to student number, find returned student number, cannot find return -1
	public int find(String str) 
	{		
	    for (int i = 0; i<arry.size(); i++)
	      if (arry.get(i).getStuID().equals(str))
						return i;//Is equal to the position in the arraylist
			return -1;
	}	
	
	public boolean readfile() {					 
		String t=null;
		try{
			FileReader f1 = new FileReader("student.txt");
			BufferedReader br=new BufferedReader(f1);				
			arry.clear();    //	Clear the data from the original array list				
			while ((t= br.readLine())!= null)
				{
				  String [] s=t.split("\\s+");				 
				  Student st=new Student(s[0],s[1],s[2],s[3],s[4],s[5],s[6]);//Note that if the file does not have seven strings per line, an error occurs
				  arry.add(st);
				  System.out.println("readfile£º");
				  System.out.println(s[0]);
				}
			     f1.close();
			     br.close();				     
			     return true;
				} catch (IOException e) {
						
				e.printStackTrace();
				return false;
			}	
	     }	
	public boolean writefile() {
		FileWriter fw=null;
		BufferedWriter out=null;
		try {
			 fw = new FileWriter("student.txt");    
			 out = new BufferedWriter(fw);
			
				for(int i=0;i<arry.size();i++){
					String s=arry.get(i).fileString();
					System.out.println("Arraylist data£º");
					System.out.println(arry.get(i).fileString());
			    	out.write(s);
			    	out.newLine();
			    }
				out.close();
				fw.close();
				return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
   }
}
