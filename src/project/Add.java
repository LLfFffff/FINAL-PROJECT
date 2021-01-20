package project;

import java.util.ArrayList;

public class Add {
	ArrayList<Student> arry=new ArrayList<Student>();
	public int find(String str) 
	{		
	    for (int i = 0; i<arry.size(); i++)
	      if (arry.get(i).getStuID().equals(str))
						return i;//Is equal to the position in the arraylist
			return -1;
	}	
	public boolean add(Student stu)
    {		
	System.out.println();	
	System.out.println("Student to add");	
	System.out.println(stu.fileString());
	System.out.println();
	if (find(stu.getStuID())!=-1)
		return false;		
	arry.add(stu); 
	return true;			
}
}
