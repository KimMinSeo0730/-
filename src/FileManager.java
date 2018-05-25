import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import java.io.*;
import javax.swing.event.*;
import java.util.*;
import java.text.*;

public class FileManager  implements TreeWillExpandListener,TreeSelectionListener
{
 private JFrame frame = new JFrame(" /home/ ");
 private Container con = null;

private JSplitPane Main=new JSplitPane();
private JScrollPane Left=null;
private JPanel Right=new JPanel(new BorderLayout());
private DefaultMutableTreeNode root = new DefaultMutableTreeNode(" .. ");
private JTree tree;
private JPanel North=new JPanel();
private JPanel South=new JPanel(new BorderLayout());
private JComboBox translate = new JComboBox();
private JLabel Text = new JLabel("File Explorer");
private JTextField Text2=new JTextField();
private Dimension dm,dm1;
private int xpos,ypos;

 FileManager(){
   init();
   start();
   frame.setSize(700,600);
   dm=Toolkit.getDefaultToolkit().getScreenSize();
   dm1=frame.getSize();
   xpos=(int)(dm.getWidth()/2-dm1.getWidth()/2);
   ypos=(int)(dm.getHeight()/2-dm1.getHeight()/2);
   frame.setLocation(xpos,ypos);
   frame.setVisible(true);
  }

 void init(){
   Main.setResizeWeight(1);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   con = frame.getContentPane();
   con.setLayout(new BorderLayout());
   Text2.setPreferredSize(new Dimension(600,20));
   translate.addItem("한국어");
   translate.addItem("English");
   South.add(Text, BorderLayout.WEST);
   South.add(translate, BorderLayout.EAST);
   
   con.add(North,"North");
   con.add(South,BorderLayout.SOUTH);
   File file=new File("");
   File list[]=file.listRoots();
   DefaultMutableTreeNode temp;
   tree=new JTree(root);
   Left=new JScrollPane(tree);
   
   Main.setDividerLocation(150);
   Main.setLeftComponent(Left);
   Main.setRightComponent(Right);
   con.add(Main);
  }
 void start()
  {
   tree.addTreeWillExpandListener(this);
   tree.addTreeSelectionListener(this);
  }
 public static void main(String args[]){
   JFrame.setDefaultLookAndFeelDecorated(true);
   new FileManager();
  }
  public void treeWillCollapse(TreeExpansionEvent event){}
  
  public void treeWillExpand(TreeExpansionEvent e)
  {
   if(((String)((DefaultMutableTreeNode)e.getPath().getLastPathComponent()).getUserObject()).equals("내컴퓨터")){}
   else
   {
    try{
     DefaultMutableTreeNode parent=(DefaultMutableTreeNode)e.getPath().getLastPathComponent();
     DefaultMutableTreeNode tempChild;
     parent.remove(0);
    }
    catch(Exception ex)
    {
     JOptionPane.showMessageDialog(frame, "디스크 혹은 파일을 찾을수 없습니다.");
    }
   }
  }
  public void valueChanged(TreeSelectionEvent e)
  {
   if(((String)((DefaultMutableTreeNode)e.getPath().getLastPathComponent()).getUserObject()).equals("내컴퓨터")){
    Text2.setText("내컴퓨터");
   }
   else
   {
    try
    {
    Main.setRightComponent(Right);
    }
    catch(Exception ex)
    {
     JOptionPane.showMessageDialog(frame, "디스크 혹은 파일을 찾을수 없습니다.");
    }
   }
  }
 }