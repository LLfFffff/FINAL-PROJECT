package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Select {
	ArrayList<Student> arry=new ArrayList<Student>();
	Student stu=new Student();
	JTable table = null;
    DefaultTableModel model = null;
	//Display all student information
   public Select() {
	    JFrame jf = new JFrame("Displaying eligible students' information");
	    //jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    // Create Content Panel
	     JPanel panel = new JPanel();
	    //Header (column name)
	  	
	    Vector columnNames=createColumnNames();	    
	    //The string array f is used to record the conditions that need to be queried		       
		 String [] f = new String [7];
			try{
				FileReader f1 = new FileReader("temporary.txt");
				BufferedReader br=new BufferedReader(f1);				
				
				String t=null;
				while ((t= br.readLine())!= null)
					{
					  f=t.split("\\s+");			//Student information to query	 
					}
				
									
			  f1.close();
			  br.close();				  				  
				} catch (IOException e) {
							// TODO 自动生成的 catch 块
				e.printStackTrace();						
				}
							
			//Remember to clear, otherwise the next time the query has no filter, it will query the last condition
			 File file =new File("temporary.txt");
		        try {
		            if(!file.exists()) {
		                file.createNewFile();
		            }
		            FileWriter fileWriter =new FileWriter(file);
		            fileWriter.write("");
		            fileWriter.flush();
		            fileWriter.close();			    		            
		        } catch (IOException e) {
		            e.printStackTrace();			         
		        }		
		    
			//Determine which conditions need to be queried				
			ArrayList List = new ArrayList(); 
			for(int i=0;i<7;i++) {
				if(!f[i].equals("--"))     //Conditions that need to be met
					List.add(i); 							
			}
			//Input the information of students that meet the query conditions into the system				 
			for(int i=0;i<List.size();i++)
				System.out.println(List.get(i));
			try{
					FileReader f1 = new FileReader("student.txt");
					BufferedReader br=new BufferedReader(f1);
					String t=null;
					int flag=0;
			        int t1=0;
			        
					arry.clear();//	Clear the data from the original array list				
					while ((t= br.readLine())!= null)
						{
						  String [] s=t.split("\\s+");		
						 for(int i=0;i<List.size();i++)
						 {
							 t1=(int) List.get(i);
							 if(f[t1].equals(s[t1])) {
								 flag=flag+1;
							 }
						 }
						  if(flag==List.size()) {
							Student st=new Student(s[0],s[1],s[2],s[3],s[4],s[5],s[6]);//注意若该文件每行没有七个字符串，则会出错
							 arry.add(st);
						  }							  
						  flag=0;
						  
						}
					     f1.close();
					     br.close();						     						     
						} catch (IOException e) {
								// TODO 自动生成的 catch 块
						e.printStackTrace();				
					}	
	    	 				
			Vector data=createTableModelData();
			
			// Create a default table model
		    model = new DefaultTableModel(data, columnNames);
		   table = new JTable(model);
	       table.setPreferredScrollableViewportSize(new Dimension(400, 80));
	       JScrollPane tablePanel = new JScrollPane(table);  
			
			
	        //Sets the table content color
	        table.setForeground(Color.BLACK);                   // font color
	        table.setFont(new Font(null, Font.PLAIN, 14));      // Font style
	        table.setSelectionForeground(Color.DARK_GRAY);      // Select the following font color
	        table.setSelectionBackground(Color.LIGHT_GRAY);     // Select the back font background
	        table.setGridColor(Color.GRAY);                     //Grid color

	        // Set the header
	        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // Sets the font style for the header name
	        table.getTableHeader().setForeground(Color.RED);                // Sets the font color of the header name
	        table.getTableHeader().setResizingAllowed(false);               // The Settings do not allow you to manually change the column width
	        table.getTableHeader().setReorderingAllowed(false);             // Sets not to allow dragging to reorder columns

	        // Set the line height
	        table.setRowHeight(40);

	        // The first column width is set to 40
	        table.getColumnModel().getColumn(0).setPreferredWidth(40);
             
	        // Sets the viewport size of the scroll panel (rows larger than this size need to drag the scroll bar to see them)
	        table.setPreferredScrollableViewportSize(new Dimension(900, 320));
	        //sequencer       
	        RowSorter sorter = new TableRowSorter(model);
	        table.setRowSorter(sorter);
	        JScrollPane pane = new JScrollPane(table);      

	        // Place the table in the scroll panel (the header is automatically added to the top of the scroll panel)
	        JScrollPane scrollPane = new JScrollPane(table);

	        // Add a scroll panel to the content panel
	        panel.add(scrollPane);
	      
	        // Set the content panel to the window
	        jf.setContentPane(panel);
	        jf.pack();
	        jf.setSize(900, 600);
	        jf.add(scrollPane, BorderLayout.CENTER);
	        //Data is shown in the center
	        DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
			r.setHorizontalAlignment(JLabel.CENTER);   
			table.setDefaultRenderer(Object.class, r);
			
			jf.setLocationRelativeTo(null);
			int t=arry.size();
			 //int t=table.getRowCount();
		        if(t<=0){
		        	JOptionPane.showMessageDialog(null, "There are no eligible students in the system！！！");
		        	jf.setVisible(false);
		        }
		        else {
		        	jf.setVisible(true);
		        }
	    }
 private Vector createColumnNames() {
   Vector columnNames = new Vector();
   //columnNames.add("序号");
   columnNames.add("student number");
   columnNames.add("name");
   columnNames.add("sex");
   columnNames.add("age");
   columnNames.add("phonenumber");
   columnNames.add("email");
   columnNames.add("homeplace");

   return columnNames;
   }

 private Vector createTableModelData() {
	 Vector data = new Vector();    	     	
     for(int i=0;i<arry.size();i++) {       	 
    	 Vector<String> rowData=new Vector<>();	    	
    	 rowData.add(arry.get(i).getStuID());
    	 rowData.add(arry.get(i).getName());
    	 rowData.add(arry.get(i).getSex());
    	 rowData.add(arry.get(i).getAge());
    	 rowData.add(arry.get(i).getphone_number());
    	 rowData.add(arry.get(i).getEmail());
    	 rowData.add(arry.get(i).getHome_place());			    	 		    					  
		 data.add(rowData);
					  	    							   
	  }	   
     return data;
  }  
}
