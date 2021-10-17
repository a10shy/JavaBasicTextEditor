import java.io.*;
import java.util.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.*;

class EditorGUI{
    JFrame frm;

    EditorGUI(){
        frm = new JFrame("Text Editor");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frm.setSize(400,400);
        frm.setLayout(new FlowLayout());

        JButton save = new JButton("Save");
        JButton open = new JButton("Open");
        JButton copy = new JButton("Copy");
        JTextField path = new JTextField("E:/Java/new.txt");
        JTextArea textArea =  new JTextArea(40,40);
        
        

        class DoSomething implements ActionListener{
            public void actionPerformed(ActionEvent ae){
                System.out.println(ae.getActionCommand());
                if(ae.getActionCommand() == "Save"){
                    try(FileWriter fout = new FileWriter(path.getText())){
                        fout.write(textArea.getText());
                        fout.close();
                    }
                    catch(FileNotFoundException fe){
                        path.setText("No SUCH FILE");
                    }
                    catch(IOException ioe){
                        path.setText("An exception occured");
                    }
                    
                 }
                 else if(ae.getActionCommand() == "Open"){
                     
                     try(FileReader fr = new FileReader(path.getText());
                          BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))){
                         String toSet = new String();
                         
                         
                         int c = fr.read();
                         while(c != -1){
                            toSet+= (char) c;
                            c = fr.read();
                            

                         }
                         bw.write(toSet);
                         textArea.setText(toSet);
                         
                        

                     }
                     catch(FileNotFoundException fe){
                        path.setText("No SUCH FILE");
                    }
                    catch(IOException ioe){
                        path.setText("An exception occured");
                    }
                 }
            }

        }


        DoSomething doesSomething =  new DoSomething();

        save.addActionListener(doesSomething);
        open.addActionListener(doesSomething);

      


        frm.add(path);
        frm.add(save);
        frm.add(open);
        frm.add(copy);
        frm.add(textArea);
        
        


        frm.setVisible(true);

    }
}

class TextEditor{
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new EditorGUI();
            }
        });
    }
}