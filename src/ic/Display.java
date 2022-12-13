/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vinay,Priya,Shashwat
 */
public class Display implements ActionListener{
    Project proj;
    JTextField no_of_stimulation;
    DefaultTableModel defaultModel;
    JLabel error;
    JButton exec;
    JButton reset;
    JTextField fileStimulations;
    public Display(Project proj){
        this.proj=proj;
        init();
        displayTable();
    }
    public Display(){
        init();
    }

    public void init() {
        JFrame main = new JFrame();
        main.setTitle("TEST");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(520, 520);

        JPanel top = new JPanel();
        top.setLayout(null);
        
        JLabel lab=new JLabel("No of Stimulation :");
        lab.setBounds(100, 30, 150, 25);        
        top.add(lab);
        
        no_of_stimulation=new JTextField("1", 4);
        no_of_stimulation.setBounds(220,30,70,25);        
        top.add(no_of_stimulation);
        
        exec=new JButton("RUN");
        exec.setBounds(320,30,70,30);        
        top.add(exec);        
        exec.addActionListener(this);
        
        error=new JLabel();
        error.setForeground(Color.RED);
        error.setBounds(150,60,200 ,25);
        top.add(error);
        
        reset=new JButton("RESET");
        reset.setBounds(200,90,100,30);        
        top.add(reset);        
        reset.addActionListener(this);
        
        lab=new JLabel("Number of Stimulations Done : ");
        lab.setBounds(100,150,180,30);
        top.add(lab);
        
        fileStimulations=new JTextField("0");
        fileStimulations.setBounds(290, 150, 70, 30);
        fileStimulations.setEditable(false);
        top.add(fileStimulations);
        
        top.setBounds(10, 10, 500, 200);
        top.setBackground(Color.WHITE);
        top.setVisible(true);
        
        main.add(top);
        
        JPanel bottom=new JPanel();
        
        bottom.setBounds(10, 200, 500, 300);
        bottom.setBackground(Color.WHITE);
        main.add(bottom);
        
        
       

        String[] columns = new String[] {"Number of TSV", "Number of Stimulation", "Percentage"};
        defaultModel = new DefaultTableModel(columns, 0);
        
        
        JTable myTable = new JTable(defaultModel);
         myTable.setPreferredScrollableViewportSize(new Dimension(450,200));
        myTable.setFillsViewportHeight(true);
        myTable.setAutoCreateRowSorter(true);
        myTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane js=new JScrollPane(myTable);
        bottom.add(js, 0);
        
         
        JPanel myPanel=new JPanel();
        
        myPanel.setBounds(200, 100, 100, 200);
        myPanel.setBackground(Color.DARK_GRAY);
        main.add(myPanel);
        
        main.setVisible(true);
        main.setResizable(false);
        
    }
    public void actionPerformed(ActionEvent e){
        //System.out.println("EVENT");
        if(e.getSource()==reset){
            while(defaultModel.getRowCount() > 0)
            {
                defaultModel.removeRow(0);
                fileStimulations.setText("0");
            }
            proj.fm.deleteDirectory();
            //System.out.println("DELETED");
        }
        else if(e.getSource()==exec){
            String S=no_of_stimulation.getText();
            try{
                int n=Integer.parseInt(S);
                if(n>0 && n<=100){
                    while(defaultModel.getRowCount() > 0)
                    {
                        defaultModel.removeRow(0);
                        fileStimulations.setText("0");
                    }
                    error.setText("");
                    proj.execution(n);
                    displayTable();
                }
                else{
                    error.setText("Enter Between 1 and 100");
                }
            }
            catch(Exception ed){
                error.setText("Invalid Entry!! Enter Number");
            }
        }
    }
    public void displayTable(){
        try{
            File file=new File(proj.st.directory+"Report.txt");
            //System.out.println("DISPLAY TABLE");
            if(file.exists()){
                FileInputStream fis=new FileInputStream(proj.st.directory+"Report.txt");
                InputReader in=new InputReader(fis);
                in.readString();
                in.readString();
                in.readString();
                in.readString();
                int temp=in.nextInt();
                fileStimulations.setText(""+temp);
                int num=0;
                while(num<temp){
                    int tsv=in.nextInt();
                    int stimulation=in.nextInt();
                    String per=in.readString();
                    num+=stimulation;
                    defaultModel.addRow(new Object[]{tsv,stimulation,per});
                }
                in=null;
                fis.close();
                file=null;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

}
