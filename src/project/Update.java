package project;

import java.util.ArrayList;

public class Update {
	ArrayList<Student> arry=new ArrayList<Student>();
	public int find(String str) 
	{		
	    for (int i = 0; i<arry.size(); i++)
	      if (arry.get(i).getStuID().equals(str))
						return i;//Is equal to the position in the arraylist
			return -1;
	}	
public void update(Student stu) {
    int flag=find(stu.getStuID());    //Find out if it exists
    arry.set(flag, stu);		   //Replace the student information in the flag
}
}
