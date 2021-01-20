package project;

import java.util.ArrayList;

public class Delete {
	ArrayList<Student> arry=new ArrayList<Student>();
	public int find(String str) 
	{		
	    for (int i = 0; i<arry.size(); i++)
	      if (arry.get(i).getStuID().equals(str))
						return i;//Is equal to the position in the arraylist
			return -1;
	}	
	public boolean delete(String s)	//Add student memorization information
	{  
		int pos=find(s);
		if (pos==-1)
			return false;
		
		arry.remove(pos);    
		return true;
	}
	
}
