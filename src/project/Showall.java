package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

public class Showall {
	JTable table = null;
    DefaultTableModel model = null;
	//Display all student information
   public Showall() {
	    JFrame jf = new JFrame("Display all student information");	   
	    // Create Content Panel
	     JPanel panel = new JPanel();
	    //Header (column name)
	   Vector columnNames=createColumnNames();
	   Vector data=createTableModelData();	   
	 // Create a default table model
	    model = new DefaultTableModel(data, columnNames);
	   table = new JTable(model);
       table.setPreferredScrollableViewportSize(new Dimension(400, 80));
       JScrollPane tablePanel = new JScrollPane(table);  				        
	        // Sets the table content color
	        table.setForeground(Color.BLACK);                   // Font color 
	        table.setFont(new Font(null, Font.PLAIN, 14));      // Font style
	        table.setSelectionForeground(Color.DARK_GRAY);      // Select the following font color
	        table.setSelectionBackground(Color.LIGHT_GRAY);     // Select the back font background
	        table.setGridColor(Color.GRAY);                     // Grid color

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

	        //Place the table in the scroll panel (the header is automatically added to the top of the scroll panel)
	        JScrollPane scrollPane = new JScrollPane(table);

	        //Add a scroll panel to the content panel
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
	        int t=table.getRowCount();
	        if(t<=0){
	        	JOptionPane.showMessageDialog(null, "Student information has not yet been entered into the system£¡£¡£¡");
	        	jf.setVisible(false);
	        }
	        else {
	        	jf.setVisible(true);
	        }	 
	    }
    
     private Vector createColumnNames() {
       Vector columnNames = new Vector();
      
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
         String t=null;
			try{
				FileReader f1 = new FileReader("student.txt");
				BufferedReader br=new BufferedReader(f1);				
				
				int i=0;
				while ((t= br.readLine())!= null)
					{
					  String [] s=t.split("\\s+");		//Split an array of strings by Spaces				  
					  Vector rowData = new Vector();
				
					 			  
					  rowData.add(s[0]);					  
					  rowData.add(s[1]);
					  rowData.add(s[2]);
					  rowData.add(s[3]);
					  rowData.add(s[4]);			  
					  rowData.add(s[5]);   
					  rowData.add(s[6]);				  
					  data.add(rowData);					  
					 			  
					  i++;					   
					}
				     f1.close();
				     br.close();			     
				     
					} catch (IOException e) {
							
					e.printStackTrace();
					
				}	  
        return data;
     }   
}
