package employee.details.management;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ViewEmployee extends JFrame implements ActionListener{
    
    JTable table;
    Choice cemployeeId;
    JButton search, print, update, back,remove;
    
    ViewEmployee(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel searchlbl = new JLabel("Search by Employee Id");
        searchlbl.setBounds(20, 20, 150, 20);
        add(searchlbl);
        
        cemployeeId = new Choice();
        cemployeeId.setBounds(180, 20, 150, 20);
        add(cemployeeId);
        
                
                        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            while(rs.next()){
                cemployeeId.add(rs.getString("empId"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        table = new JTable();
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);
        
        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        add(search);
        
        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        print.setBackground(Color.BLACK);
        print.setForeground(Color.WHITE);
        add(print);
        
        update = new JButton("Update");
        update.setBounds(220, 70, 80, 20);
        update.addActionListener(this);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        add(update);
        
        remove = new JButton("Remove");
        remove.setBounds(320, 70, 90, 20);
        remove.addActionListener(this);
        remove.setBackground(Color.BLACK);
        remove.setForeground(Color.WHITE);
        add(remove);
        
        back = new JButton("Exit");
        back.setBounds(800, 70, 80, 20);
        back.addActionListener(this);
        back.setBackground(Color.red);
        back.setForeground(Color.WHITE);
        add(back);
        
        setSize(900,700);
        setLocation(300,100);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == search){
            String query = "select * from employee where empId = '"+cemployeeId.getSelectedItem()+"'";
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource() == print){
            try{
                table.print();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource() == update){
            setVisible(false);
            new UpdateEmployee(cemployeeId.getSelectedItem());
        }else if(ae.getSource() == remove){
            setVisible(false);
            new RemoveEmployee();
        }else{
            setVisible(false);
            new Home();
        }
    }
    
    public static void main(String args[]){
        new ViewEmployee();
    }
}
