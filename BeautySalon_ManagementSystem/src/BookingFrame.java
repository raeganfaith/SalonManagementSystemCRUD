import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.border.LineBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BookingFrame extends JFrame {
	
	private Image img_logo = new ImageIcon(LoginFrame.class.getResource("res/LOGO-2.png")).getImage().getScaledInstance(300, 90, Image.SCALE_SMOOTH);
	private JPanel contentPane;
	private JTextField txt_bookid;
	private JTextField txt_name;
	private JTextField txt_phone;
	private JTextField txt_address;
	public JComboBox<String> cbx_services; 
	public JComboBox<String> cbx_hairstylist;
	private JTable table;
	private JScrollPane scrollPane;
	
	Connection con;
	Connection connection;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField textField;
	
	//Database Connection
	public void Connection() {
		String connection = "jdbc:sqlserver://localhost:1433;user=sa;password={arithmetic28pitpayt};encrypt = true;trustServerCertificate = true;";	
		try {
			con = DriverManager.getConnection(connection);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}	
	}
	public void showComboBox() {
		
	}
	//to fetch data from the database to the JComboBox
	public void fillComboBoxService()
	{
		try
		{
			String query = "SELECT * FROM Services ";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
				
			while(rs.next())
			{
				cbx_services.addItem(rs.getString("Services_Name"));
			}
			
			//rs.close();
			//ps.close();
			
		}
		catch (NullPointerException | SQLException e1) 
		{
			e1.printStackTrace();
			System.out.println("NullPointerException thrown!");
		}
	}
	public void fillComboBoxStylist()
	{
		try
		{
			String query = "SELECT * FROM Services ";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next())
			{
				cbx_hairstylist.addItem(rs.getString("Employee_Name"));		
			}

			//rs.close();
			//ps.close();
			
		}
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
	}
	
	//a method to show and fetch data from the database to the Jtable
	public void ShowData() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Booking ID");
		model.addColumn("Name");
		model.addColumn("Address");
		model.addColumn("Contact No.");
		model.addColumn("Service");
		model.addColumn("Stylist");
		try {
			String query = "select * from Booking";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				model.addRow(new Object [] {
					rs.getString("Booking_No"),	
					rs.getString("Cust_Name"),	
					rs.getString("Cust_Address"),	
					rs.getString("Cust_Phone"),
					rs.getString("Services_Name"),	
					rs.getString("Employee_Name"),
				});
					
				}
			
			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(55);
			table.getColumnModel().getColumn(1).setPreferredWidth(80);
			table.getColumnModel().getColumn(2).setPreferredWidth(90);
			table.getColumnModel().getColumn(3).setPreferredWidth(60);
			table.getColumnModel().getColumn(4).setPreferredWidth(60);
			table.getColumnModel().getColumn(5).setPreferredWidth(60);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookingFrame frame = new BookingFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public BookingFrame() {
		//To automatically shows the data to the Jtable
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				ShowData();
			}
		});
		
		Connection(); //method for the database
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 550); //Frame size
		contentPane = new JPanel();
		contentPane.setBackground(new Color(251, 213, 225)); //background color of the panel
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(194, 10, 310, 92);
		contentPane.add(lblLogo);
		setUndecorated(true);
		lblLogo.setIcon(new ImageIcon(img_logo)); //insert logo
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(null);
		panel.setBackground(new Color(250, 234, 240));
		panel.setBounds(0, 110, 700, 16);
		contentPane.add(panel);
		
		JLabel lblBookingTransaction = new JLabel("BOOKING TRANSACTION");
		lblBookingTransaction.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookingTransaction.setForeground(new Color(114, 115, 115));
		lblBookingTransaction.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblBookingTransaction.setBounds(224, 124, 248, 44);
		contentPane.add(lblBookingTransaction);
		
		txt_bookid = new JTextField();
		txt_bookid.setFont(new Font("Century Gothic", Font.PLAIN, 17));
		txt_bookid.setForeground(new Color(114, 115, 115));
		txt_bookid.setColumns(10);
		txt_bookid.setBorder(null);
		txt_bookid.setBackground(new Color(250, 234, 240));
		txt_bookid.setBounds(130, 171, 123, 23);
		contentPane.add(txt_bookid);
		
		txt_name = new JTextField();
		txt_name.setForeground(new Color(114, 115, 115));
		txt_name.setColumns(10);
		txt_name.setBorder(null);
		txt_name.setBackground(new Color(250, 234, 240));
		txt_name.setBounds(130, 204, 123, 23);
		contentPane.add(txt_name);

		cbx_hairstylist = new JComboBox();
		cbx_hairstylist.setForeground(new Color(114, 115, 115));
		cbx_hairstylist.setBackground(new Color(250, 234, 240));
		cbx_hairstylist.setBounds(130, 339, 123, 23);
		contentPane.add(cbx_hairstylist);
		fillComboBoxStylist();
		
		cbx_services = new JComboBox<String>();
		cbx_services.setForeground(new Color(114, 115, 115));
		cbx_services.addItemListener(new ItemListener() {
			//To populate the items in the cbx_hairstylist according to the selected item in other bx_services.
			public void itemStateChanged(ItemEvent e) {

				String s = (String) cbx_services.getSelectedItem();
	            String sql = "Select * from Services where Services_Name='" + s + "'";
	            try {
	                PreparedStatement pst = con.prepareStatement(sql);
	                ResultSet rs = pst.executeQuery();
	                cbx_hairstylist.removeAllItems();
	                while (rs.next()) {
	                	cbx_hairstylist.addItem(rs.getString("Employee_Name"));
	                }
	            } catch (NullPointerException | SQLException e3) 
				{
					e3.printStackTrace();
					System.out.println("NullPointerException thrown!");
				}
			}
		});
		cbx_services.setBackground(new Color(250, 234, 240));
		cbx_services.setBounds(130, 306, 123, 23);
		contentPane.add(cbx_services);
		fillComboBoxService();
				
		txt_phone = new JTextField();
		txt_phone.setForeground(new Color(114, 115, 115));
		txt_phone.setColumns(10);
		txt_phone.setBorder(null);
		txt_phone.setBackground(new Color(250, 234, 240));
		txt_phone.setBounds(130, 273, 123, 23);
		contentPane.add(txt_phone);
		
		JLabel lblContactNo = new JLabel("CONTACT NO:");
		lblContactNo.setForeground(new Color(114, 115, 115));
		lblContactNo.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblContactNo.setBounds(20, 270, 138, 20);
		contentPane.add(lblContactNo);
		
		JLabel lblHairSt = new JLabel("STYLIST:");
		lblHairSt.setForeground(new Color(114, 115, 115));
		lblHairSt.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblHairSt.setBounds(20, 336, 119, 26);
		contentPane.add(lblHairSt);
		
		JLabel lblService = new JLabel("SERVICE:");
		lblService.setForeground(new Color(114, 115, 115));
		lblService.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblService.setBounds(20, 303, 87, 22);
		contentPane.add(lblService);
		
		JLabel lblName = new JLabel("NAME:");
		lblName.setForeground(new Color(114, 115, 115));
		lblName.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblName.setBounds(20, 200, 87, 25);
		contentPane.add(lblName);
		
		JLabel lblCustomerId = new JLabel("BOOKING ID:");
		lblCustomerId.setForeground(new Color(114, 115, 115));
		lblCustomerId.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblCustomerId.setBounds(20, 172, 143, 23);
		contentPane.add(lblCustomerId);
		
		txt_address = new JTextField();
		txt_address.setForeground(new Color(114, 115, 115));
		txt_address.setColumns(10);
		txt_address.setBorder(null);
		txt_address.setBackground(new Color(250, 234, 240));
		txt_address.setBounds(130, 238, 123, 23);
		contentPane.add(txt_address);
		
		JLabel lblAddress = new JLabel("ADDRESS:");
		lblAddress.setForeground(new Color(114, 115, 115));
		lblAddress.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblAddress.setBounds(20, 235, 100, 25);
		contentPane.add(lblAddress);
		
		//Add button function linked with database
		JButton btnCreate = new JButton("CREATE");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String id = txt_bookid.getText();
				String names = txt_name.getText();	
				String address = txt_address.getText();
				String contact = txt_phone.getText();	
				String service = (String) cbx_services.getSelectedItem();
				String hairstylist = (String) cbx_hairstylist.getSelectedItem();
				
				try {
					pst = con.prepareStatement("insert into Booking(Cust_Name, Cust_Address, Cust_Phone, Services_Name, Employee_Name)values(?,?,?,?,?)");
					pst.setString(1, names);
					pst.setString(2, address);
					pst.setString(3, contact);
					pst.setString(4, service);
					pst.setString(5, hairstylist);

					int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to save?", "ALERT!", JOptionPane.YES_NO_OPTION);
					
					if(input == JOptionPane.YES_OPTION) {
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Successfully added!");
						ShowData(); // to automatically update the table
						txt_name.setText("");
						txt_address.setText("");
						txt_phone.setText("");
						cbx_services.setSelectedIndex(-1);
						cbx_hairstylist.setSelectedItem(-1);
					} else {
						JOptionPane.showMessageDialog(null, "Error!");
					}

				} catch (NullPointerException | SQLException e2) 
				{
					e2.printStackTrace();
					System.out.println("NullPointerException thrown!");
				}
			}
		});

		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCreate.setForeground(Color.BLACK);
				btnCreate.setBackground(new Color(253, 139, 180));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCreate.setForeground(Color.GRAY);
				btnCreate.setBackground(new Color(252, 193, 213));
			}});
		btnCreate.setForeground(new Color(114, 115, 115));
		btnCreate.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnCreate.setBorderPainted(false);
		btnCreate.setBackground(new Color(252, 193, 213));
		btnCreate.setBounds(20, 372, 233, 33);
		contentPane.add(btnCreate);
		
		//Update Jtable and database function
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = txt_bookid.getText();
				String names1 = txt_name.getText();	
				String address1 = txt_address.getText();
				String contact1 = txt_phone.getText();	
				String service1 = (String) cbx_services.getSelectedItem();
				String stylist1 = (String) cbx_hairstylist.getSelectedItem();
				
				try {
					
					pst = con.prepareStatement("UPDATE Booking SET Cust_Name='"+names1+"', Cust_Address='"+address1+"', Cust_Phone='"+contact1+"', Services_Name='"+service1+"',Employee_Name='"+stylist1+"' WHERE Booking_No='"+ID+"'");
					
					int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to make changes?", "ALERT!", JOptionPane.YES_NO_OPTION);
					if(input == JOptionPane.YES_OPTION) {
						pst.execute();
						JOptionPane.showMessageDialog(null, "Successfully updated!");
						ShowData();
					}else {
						
					}
						
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnUpdate.setForeground(Color.BLACK);
				btnUpdate.setBackground(new Color(253, 139, 180));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnUpdate.setForeground(Color.GRAY);
				btnUpdate.setBackground(new Color(252, 193, 213));
			}});
		btnUpdate.setForeground(new Color(114, 115, 115));
		btnUpdate.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBackground(new Color(252, 193, 213));
		btnUpdate.setBounds(20, 414, 233, 33);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 	DefaultTableModel model = (DefaultTableModel)table.getModel();
			        int SelectRowIndex = table.getSelectedRow();
			        String hold = model.getValueAt(SelectRowIndex, 0).toString();
		        	String queryy = "DELETE FROM Booking WHERE Booking_No='"+hold +"'";
		        	 
			        try{
			            PreparedStatement pst = con.prepareStatement(queryy);
			            int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "ALERT!", JOptionPane.YES_NO_OPTION); {
							if (input == JOptionPane.YES_OPTION) {
								pst.executeUpdate();
					               JOptionPane.showMessageDialog(null, "Deleted successfully.");
					               ShowData();
							}
						}
			            txt_bookid.setText("");
			            txt_name.setText("");
			            txt_address.setText("");
			            txt_phone.setText("");
			            cbx_services.setSelectedIndex(0);
			            cbx_hairstylist.setSelectedIndex(0);
			        }catch(HeadlessException | SQLException e11){
			            JOptionPane.showMessageDialog(null,e11);
			        }
			}
		});
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDelete.setForeground(Color.BLACK);
				btnDelete.setBackground(new Color(253, 139, 180));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnDelete.setForeground(Color.GRAY);
				btnDelete.setBackground(new Color(252, 193, 213));
			}});
		btnDelete.setForeground(new Color(114, 115, 115));
		btnDelete.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(252, 193, 213));
		btnDelete.setBounds(20, 453, 233, 33);
		contentPane.add(btnDelete);
		
		//A funtion to clear the value in JTextField and JComboBox.
		JButton btnClear = new JButton("CLEAR");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnClear.setForeground(Color.BLACK);
				btnClear.setBackground(new Color(253, 139, 180));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnClear.setForeground(Color.GRAY);
				btnClear.setBackground(new Color(252, 193, 213));
				//clear function
			}			
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showConfirmDialog(null, "Are you sure you want to clear your data?", "Warning", JOptionPane.WARNING_MESSAGE,JOptionPane.OK_CANCEL_OPTION);
				txt_bookid.setText("");
				txt_name.setText("");
				txt_address.setText("");
				txt_phone.setText("");
				cbx_services.setSelectedIndex(-1);
				cbx_hairstylist.setSelectedIndex(-1);
			}});
		btnClear.setForeground(new Color(114, 115, 115));
		btnClear.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		btnClear.setBorderPainted(false);
		btnClear.setBackground(new Color(252, 193, 213));
		btnClear.setBounds(20, 496, 233, 33);
		contentPane.add(btnClear);
		
		JLabel lblBack = new JLabel("BACK");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DashboardFrame cv = new DashboardFrame();
		    	cv.setVisible(true);
		    	BookingFrame.this.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblBack.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblBack.setForeground(Color.GRAY);
			}
		});
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBack.setForeground(new Color(114, 115, 115));
		lblBack.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblBack.setBounds(0, 0, 85, 37);
		contentPane.add(lblBack);
		
		JLabel lblclose = new JLabel("CLOSE");
		lblclose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
					BookingFrame.this.dispose();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblclose.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblclose.setForeground(Color.GRAY);
			}
		});
		lblclose.setHorizontalAlignment(SwingConstants.CENTER);
		lblclose.setForeground(new Color(114, 115, 115));
		lblclose.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblclose.setBounds(615, 0, 85, 37);
		contentPane.add(lblclose);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.setForeground(new Color(114, 115, 115));
		btnSave.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnSave.setBorderPainted(false);
		btnSave.setBackground(new Color(252, 193, 213));
		btnSave.setBounds(547, 474, 123, 33);
		contentPane.add(btnSave);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.LIGHT_GRAY);
		scrollPane.setFont(new Font("Century Gothic", Font.PLAIN, 9));
		scrollPane.setBounds(263, 171, 407, 293);
		contentPane.add(scrollPane);
		
		table = new JTable();
		//Display selected row in textFields and JComboBox.
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int SelectRowIndex = table.getSelectedRow();
				txt_bookid.setText(model.getValueAt(SelectRowIndex, 0).toString());
				txt_name.setText(model.getValueAt(SelectRowIndex, 1).toString());
				txt_address.setText(model.getValueAt(SelectRowIndex, 2).toString());
				txt_phone.setText(model.getValueAt(SelectRowIndex, 3).toString());
				cbx_services.setSelectedItem(model.getValueAt(SelectRowIndex, 4).toString());	
				cbx_hairstylist.setSelectedItem(model.getValueAt(SelectRowIndex, 5).toString());	
			}
		});
		table.setBorder(null);
		table.setBackground(new Color(250, 234, 240));
		table.setFont(new Font("Century Gothic", Font.PLAIN, 9));
		scrollPane.setViewportView(table);
		
		JLabel lblSearch = new JLabel("Total No. of Bookings:");
		lblSearch.setForeground(new Color(114, 115, 115));
		lblSearch.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblSearch.setBounds(263, 474, 159, 23);
		contentPane.add(lblSearch);
		
		textField = new JTextField();
		textField.setForeground(new Color(114, 115, 115));
		textField.setFont(new Font("Century Gothic", Font.PLAIN, 17));
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBackground(new Color(250, 234, 240));
		textField.setBounds(420, 474, 85, 23);
		contentPane.add(textField);
		
		//to customize the header/column
		JTableHeader JTHeader = table.getTableHeader();
		JTHeader.setFont(new Font("Century Gothic", Font.PLAIN, 9));
		JTHeader.setBackground(new Color(252, 193, 213));
	}
	
}


















