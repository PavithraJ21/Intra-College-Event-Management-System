import java.util.*;
import java.awt.Color.*;
import java.util.List;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.event.*;
import java.awt.Graphics;
import java.text.SimpleDateFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class HomePage extends JFrame{
		JPanel p1,p2,p3;
		JButton b1,b3;
		JLabel l1,l;
		ImageIcon icon;
		public HomePage(){
			setVisible(true);
			setSize(800,800);
			setResizable(true);
			l1=new JLabel("EVENT REGISTRATION",JLabel.CENTER);
			l1.setFont(new Font("Serif",Font.BOLD,40));
			buildComponents();
			designComponents();
			b3.addActionListener(
			    new ActionListener(){
					public void actionPerformed(ActionEvent ae)
					{
						StudentLogin l4=new StudentLogin();
					}
				});
			b1.addActionListener(
			    new ActionListener(){
			    	public void actionPerformed(ActionEvent ae) {
			    		LoginForm2 l2=new LoginForm2();
			    	}
			    }
			);
		}
	public void buildComponents(){
		p1=new JPanel();
		p1.setBackground(Color.pink);
		p3=new JPanel();
		p3.setBackground(Color.pink);
		p2=new JPanel(new FlowLayout());
		p2.setBackground(Color.pink);
		icon=new ImageIcon(this.getClass().getResource("/event2.jpg"));
		l=new JLabel(icon);
		b1=new JButton("ADMIN");
		b3=new JButton("STUDENT");
	}
	public void designComponents(){
		p1.add(l1);
		p3.add(l);
		p2.add(b1);
		p2.add(b3);
	    add(p1,BorderLayout.NORTH);
		add(p3,BorderLayout.CENTER);
		add(p2,BorderLayout.SOUTH);
		
		
	}
	
}

class DeleteEvent extends JFrame{
	JLabel l1;
	JTextField f1;
	JButton b1,b2;
	public DeleteEvent() {
		setVisible(true);
		setSize(200,200);
		setResizable(false);
		setTitle("Event Deletion Form");
		l1=new JLabel("Enter the event Name :");
		f1=new JTextField(10);
		b1=new JButton("SUBMIT");
		b2=new JButton("CANCEL");
		JPanel p=new JPanel(new GridLayout(2,2));
		p.add(l1);
		p.add(f1);
		p.add(b1);
		p.add(b2);
		add(p);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				setVisible(false);
			}
		});
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String event=f1.getText();
				DBHandler d2=new DBHandler();
				int x=d2.deleteEvent(event);
				if (x>0)
					JOptionPane.showMessageDialog(null,"Removed Successfully");
			    else
			    	JOptionPane.showMessageDialog(null," Unsuccessfull.");
				}
					
			}
		);
	}
}
class ViewForm extends JFrame {
	JTable j1;
	
	public ViewForm() {
	setVisible(true);
	setSize(500,500);
	setTitle("Participants Record");
	setResizable(true);
	setBackground(Color.pink);
	String[][] events;
	DBHandler d1=new DBHandler();
	events=d1.viewEvent();
	String[] cName= {"Participants","Event"}; 
	j1=new JTable(events,cName);
	JScrollPane sp=new JScrollPane(j1);
	add(sp);
	}
}
class EventRegistrationForm extends JFrame{
	JLabel t1;
	JLabel name;
	JLabel date;
	JLabel StartTime;
	JLabel EndTime;
	JLabel faculty,venue;
	JTextField f1;
	JTextField f2;
	JTextField f3;
	JTextField f4;
	JTextField f5;
	JTextField f6;
	JPanel p;
	JPanel p1;
	JButton b1;
	JButton b2;
	public EventRegistrationForm() {
		setVisible(true);
		setSize(300,300);
		setResizable(true);
		setTitle("Event Registration Form");
		buildComponents();
		designComponents();
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				setVisible(false);
			}
		});
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				try {
			    String name=f1.getText();
			    String date=f2.getText();
			    Date date1=new Date();
			    date1=new SimpleDateFormat("YYYY-MM-DD").parse(date);
			    System.out.println(date1);
			    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
			    String mysqlDate=formatter.format(date1);
			    System.out.println(mysqlDate);
			    String sTime=f3.getText();
			    String eTime=f4.getText();
			    String fac=f5.getText();
			    String venue=f6.getText();
			    System.out.println("Registering the event.");
			    DBHandler d1=new DBHandler();
			    int x=d1.insertEvent(name,mysqlDate,sTime,eTime,fac,venue);
			    if(x>0)
			    {
			    	JOptionPane.showMessageDialog(null,"Registerd Successfully");
			        setVisible(false);
			    }
			    else {
			    	JOptionPane.showMessageDialog(null,"Registered Unsuccessfull.");
			    	f2.setText(null);
			    	f3.setText(null);
			    	f4.setText(null);
			    	f5.setText(null);
			    	f6.setText(null);
			    }}
				catch(Exception ex) {
					System.out.println(ex);}
			}
		});
	}
	public void buildComponents() {
		p1=new JPanel();
		b1=new JButton("SUBMIT");
		b2=new JButton("CANCEL");
		p=new JPanel(new GridLayout(6,6));
	        t1=new JLabel("Kindly Enter the information of the Event",JLabel.CENTER);
	        name=new JLabel("NAME");
		date=new JLabel("DATE");
		StartTime=new JLabel("STARTING TIME");
		EndTime=new JLabel("ENDING TIME");
		faculty=new JLabel("FACULTY IN CHARGE");
		venue=new JLabel("VENUE");
		f1=new JTextField(10);
		f2=new JTextField(10);
		f3=new JTextField(10);
		f4=new JTextField(10);
		f5=new JTextField(10);
		f6=new JTextField(10);
	}
	public void designComponents() {
		p.add(name);
		p.add(f1);
		p.add(date);
		p.add(f2);
		p.add(StartTime);
		p.add(f3);
		p.add(EndTime);
		p.add(f4);
		p.add(faculty);
		p.add(f5);
		p.add(venue);
		p.add(f6);
		p1.add(b1);
		p1.add(b2);
		add(t1,BorderLayout.NORTH);
		add(p,BorderLayout.CENTER);
		add(p1,BorderLayout.SOUTH);
	}
}
class StudentRegistration extends JFrame{
	JLabel l1,t1,l2,l3,l5,l4,l6;
	JComboBox f3,f4,f5;
	JTextField f1,f2;
	JPasswordField f6;
	JButton b1,b2;
	JPanel p1,p;
	public StudentRegistration() {
		setBackground(Color.DARK_GRAY);
		setVisible(true);
		setSize(300,300);
		setResizable(true);
		setTitle("Student Registration form");
		buildComponents();
		designComponents();
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				setVisible(false);
			}
		});
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				String s1=f2.getText();
				String s2=f1.getText();
				String s3=(String)f3.getSelectedItem();
				int s4=Integer.parseInt((String)f4.getSelectedItem());
				String s5=(String)f5.getSelectedItem();
				String s6=String.valueOf(f6.getPassword());
				DBHandler d1=new DBHandler();
				int x=d1.insertStudent(s1,s2,s3,s4,s5,s6);
				if(x>0)
				{
			    	JOptionPane.showMessageDialog(null,"Registerd Successfully");
				    setVisible(false);
				    StudentLogin sl2=new StudentLogin();
				}
				else
			    	JOptionPane.showMessageDialog(null,"Registered Unsuccessfull.");
			
		}
		});
	}
	public void buildComponents() {
		p1=new JPanel();
		b1=new JButton("SUBMIT");
		b2=new JButton("CANCEL");
		p=new JPanel(new GridLayout(6,6));
	    t1=new JLabel("Kindly Enter your information ",JLabel.CENTER);
	    l1=new JLabel("NAME");
		l2=new JLabel("ID");
		l3=new JLabel("DEPT");
		l4=new JLabel("YEAR");
		l5=new JLabel("SECTION");
		f3=new JComboBox();
		f4=new JComboBox();
		f5=new JComboBox();
		l6=new JLabel("Enter the new password");
		f1=new JTextField(10);
		f2=new JTextField(10);
		
		f6=new JPasswordField(10);
		p.setBackground(Color.LIGHT_GRAY);
	}
	public void designComponents() {
		//f3.setPreferredSize(new Diminension(6,8));
		f3.addItem("");
		f3.addItem("ECE");
		f3.addItem("CSE");
		f3.addItem("MECH");
		f3.addItem("CIVIL");
		f3.addItem("EEE");
		f3.addItem("");
		f4.addItem("1");
		f4.addItem("2");
		f4.addItem("3");
		f4.addItem("4");
		f3.addItem("");
		f5.addItem("A");
		f5.addItem("B");
		f5.addItem("C");
		p1.setBackground(Color.LIGHT_GRAY);
		t1.setBackground(Color.LIGHT_GRAY);
		p.add(l1);
		p.add(f1);
		p.add(l2);
		p.add(f2);
		p.add(l3);
		p.add(f3);
		p.add(l4);
		p.add(f4);
		p.add(l5);
		p.add(f5);
		p.add(l6);
		p.add(f6);
		p1.add(b1);
		p1.add(b2);
		add(t1,BorderLayout.NORTH);
		add(p,BorderLayout.CENTER);
		add(p1,BorderLayout.SOUTH);
	}

	
}
class LoginForm2 extends JFrame{
	JButton close,b1,b2,b3;
	JPanel panel;
	JLabel label1,label2,l;
	JTextField text1;
	JPasswordField pwd;
	//JButton b1,b2,b3;
	//JPanel p;
	public LoginForm2() {
		setVisible(true);
		setSize(400,400);
		setResizable(true);
		setTitle("ADMIN LOGIN FORM");
		buildComponents();
		designComponents();
		b1.addActionListener(
			    new ActionListener(){
			    	public void actionPerformed(ActionEvent ae) {
			    		String pass=(String)pwd.getText();
			    		String u1=(String)text1.getText();
			    		System.out.println(u1);
			    		System.out.println(pass);
			    		if(u1.equals("Login")&&pass.equals("Login")) {
			    		EventRegistrationForm l1=new EventRegistrationForm();
			    		}
			    		else
			    		{
			    			JOptionPane.showMessageDialog(null,"INVALID USERNAME AND PASSWORD.");
					        pwd.setText(null);}
			    	}
			    }
			);
		b2.addActionListener(
			    new ActionListener(){
			    	public void actionPerformed(ActionEvent ae) {
			    		String pass=(String)pwd.getText();
			    		String u1=(String)text1.getText();
			    		System.out.println(u1);
			    		System.out.println(pass);
			    		if(u1.equals("Login")&&pass.equals("Login")) {
			    			DeleteEvent l2=new DeleteEvent();
			    		}
			    		else {
			    		JOptionPane.showMessageDialog(null,"INVALID USERNAME AND PASSWORD.");
					    pwd.setText(null);}	
			    	}
			    	}
			    
			);
		b3.addActionListener(
			    new ActionListener(){
			    	public void actionPerformed(ActionEvent ae) {
			    		String pass=(String)pwd.getText();
			    		String u1=(String)text1.getText();
			    		System.out.println(u1);
			    		System.out.println(pass);
			    		if(u1.equals("Login")&&pass.equals("Login")) {
			    			ViewForm l2=new ViewForm();
			    		}
			    		else {
			    		JOptionPane.showMessageDialog(null,"INVALID USERNAME AND PASSWORD.");
					    pwd.setText(null);}	
			    	}
			    	}
			    
			);
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				setVisible(false);
			}
		});
	}
	
		
		public void buildComponents(){
			b1=new JButton("To Register the Event");
			b2=new JButton("To delete the Event");
			b3=new JButton("To View The Participants");
			panel=new JPanel();
			label1=new JLabel("USERNAME: ");
			text1=new JTextField();
			//l=new JLabel("Forget Password");
			//res=new JButton("CLICK HERE");
			label2=new JLabel("PASSWORD: ");
			pwd=new JPasswordField();
			//submit=new JButton("SUBMIT");
			close=new JButton("CANCEL");
		}
		public void designComponents(){
			panel=new JPanel(new GridLayout(4,2,20,20));
			panel.add(label1);
			panel.add(text1);
			panel.add(label2);
			panel.add(pwd);
			panel.add(b1);
			panel.add(b2);
			panel.add(b3);
			//panel.add(submit);
			panel.add(close);
			//panel.add(l);
			//panel.add(res);
			add(panel,BorderLayout.CENTER);
			
		}
}
class StudentLogin extends JFrame{
	JButton submit,close,res;
	JPanel panel;
	JLabel label1,label2,l;
	JTextField text1;
	JPasswordField pwd;
	public StudentLogin(){
		setVisible(true);
		setSize(300,300);
		setTitle("STUDENT LOGIN FORM");
		setResizable(false);
		buildComponents();
		designComponents();
		
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				String id=text1.getText();
				String pass=pwd.getText();
				DBHandler d1=new DBHandler();
			    int x=d1.verifyPassword(id,pass);
				if(x>0)
				{
			    	//JOptionPane.showMessageDialog(null,"Registerd Successfully");
				    setVisible(false);
				    EventRes sl2=new EventRes();
				}
				else {
			    	JOptionPane.showMessageDialog(null,"INVALID USERNAME AND PASSWORD.");
				    pwd.setText(null);}
			    //setVisible(false);
		}
		});
			
		close.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					setVisible(false);
				}
			});
		res.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				StudentRegistration sr=new StudentRegistration();
			}
		});
	}

		public void buildComponents(){
			panel=new JPanel();
			label1=new JLabel("USERNAME: ");
			text1=new JTextField();
			l=new JLabel("Forget Password");
			l.setForeground(Color.RED);
			res=new JButton("CLICK HERE");
			label2=new JLabel("PASSWORD: ");
			pwd=new JPasswordField();
			submit=new JButton("SUBMIT");
			submit.setBackground(Color.GREEN);
			close=new JButton("CANCEL");
			//close.setBackground(Color.RED);
		}
		public void designComponents(){
			panel=new JPanel(new GridLayout(4,2,20,20));
			panel.add(label1);
			panel.add(text1);
			panel.add(label2);
			panel.add(pwd);
			panel.add(submit);
			panel.add(close);
			panel.add(l);
			panel.add(res);
			add(panel,BorderLayout.CENTER);
			
		}
}
class EventRes extends JFrame{
	JLabel l1,l2;
	JPanel p1=new JPanel();
	JTextField t1;
	JTable j1;
	JButton b1,b2;
	public EventRes() {
		setVisible(true);
		setSize(500,500);
		setLayout(new GridLayout(3,2,20,20));
		setResizable(true);
		//setPreferredSize(new Dimension(4*100,4*100));
		buildComponents();
		designComponents();
		DBHandler d1=new DBHandler();
		String[][] events;
		events=d1.displayEvent();
		 String[] cName= {"Event","Date","Starting Time","Ending time","Faculty Incharge","Venue"}; 
	
		j1=new JTable(events,cName);
		JScrollPane sp=new JScrollPane(j1);
		p1.add(sp);
		String event;
		String eve=new String();
		j1.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent me) {
	        	//table table = (JTable)e.getsource();
				int row = j1.getSelectedRow();
				int column = j1.getSelectedColumn();
			    String eve =(String)j1.getValueAt(row, column);
				System.out.println(eve);
				//event=eve;
				b1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae)
					{
			            
						System.out.println(eve);
						String user=(String)t1.getText();
						//String eve=(String)t2.getText();
						DBHandler d1=new DBHandler();
						int x=d1.insertParticipation(user,eve);
						if(x>0)
						{
					    	JOptionPane.showMessageDialog(null,"Registerd Successfully");
						    setVisible(false);
						   
						}
						else
					    	JOptionPane.showMessageDialog(null,"Registered Unsuccessfull.");
					   
				}
				});
	        }
		});
		b2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					setVisible(false);
				}
			});
	}
	public void buildComponents() {
		JPanel p2=new JPanel();
		l1=new JLabel("Enter the UserName:");
		l2=new JLabel("Enter the event: ");
		t1=new JTextField(10);
		b1=new JButton("SUBMIT");
		b1.setPreferredSize(new Dimension(80,40));
		b1.setBackground(Color.green);
		b2=new JButton("BACK");
		b2.setBackground(Color.red);
	}
	public void designComponents() {
		JPanel p=new JPanel();
		add(l1);add(t1);add(l2);add(p1);add(b1);add(b2);
	}
}
public class miniProject {
	public static void main(String[] args)
	{
	   HomePage f1=new HomePage();
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	   
	}

}
