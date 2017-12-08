package well_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Clientf extends JFrame {
    private JTextArea jta = new JTextArea("");
    private JTextField jtf = new JTextField();
    private JTextField jtfport = new JTextField("1723");
    private JButton  jbtn[] =new JButton[10];
    private JButton btnstart = new JButton("Start");
    private JButton btnstop = new JButton("Stop");
    private JButton btnexit = new JButton("Exit");
    private JButton btnsend = new JButton("Send");
    private  JLabel jlb1 = new JLabel("Server IP:");
    private  JTextField jtfip = new JTextField("127.0.1.1");
    private  JLabel jlb2 = new JLabel("Port:");
    private  JScrollPane jsp = new JScrollPane(jta);
    private JPanel jp1 = new JPanel(new GridLayout(3,3,3,3));
    private JPanel jp2 = new JPanel(new GridLayout(8,1,3,3));
    private JPanel jp3 = new JPanel(new GridLayout(1,1,3,3));
    private  Container cp;
    private Client client;
    private  boolean b;
    private int tmp=0;
public String str;
    public Clientf(){


        init();
    }


    private  void init(){

        this.setTitle("client");
        this.setBounds(100,100,700,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cp=this.getContentPane();
        cp.setLayout(new BorderLayout(5,5));
        cp.add(jp1,BorderLayout.CENTER);
        cp.add(jp2,BorderLayout.EAST);
        cp.add(jp3,BorderLayout.WEST);
        cp.add(jtf,BorderLayout.SOUTH);
        jp2.add(jlb1);
        jp2.add(jtfip);
        jp2.add(jlb2);
        jp2.add(jtfport);
        jp2.add(btnstart);
        jp2.add(btnstop);
        jp2.add(btnexit);
        jp2.add(btnsend);
        jp3.add(jsp);
        jsp.setPreferredSize(new Dimension(200,400));
        jta.setLineWrap(true);
//        client = new Client(Clientf.this);

btnstart.addActionListener(new AbstractAction() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
//            if (((JButton) e.getSource()).getText().equals("Start")) {
                client.start();
            btnstop.setEnabled(true);
            btnstart.setEnabled(false);
//                ((JButton) e.getSource()).setText("stop");
//            } else {

//                ((JButton) e.getSource()).setText("Start");
//            }
        }catch (Exception e1){
            System.out.println(e1);
        }
    }
});
        btnstop.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.CloseSocket();
                btnstop.setEnabled(false);
                btnstart.setEnabled(true);
            }
        });


        btnsend.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            client.send2server(jtf.getText());
                jta.append("Client:"+jtf.getText()+"\n");
                jtf.setText("");

            }
        });

        for (int i = 0; i < 9; i++) {

            jbtn[i] = new JButton();
            jp1.add(jbtn[i]);
            jbtn[i].setOpaque(true);
         jbtn[i].setFont(new Font(null,Font.BOLD,80));


                jbtn[i].addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (b==true) {
                            str="O";
                            // TODO addBtn(str);
                            ((JButton) e.getSource()).setText(str);
                            //((JButton) e.getSource()).setEnabled(false);


                            b=false;
                            tmp++;
                            client.send2servera(tmp,500);
                            jta.append("Client:"+tmp+"\n");
                          //  win();
                        }else {
                            str="X";
//                            client.send2servera(((JButton) e.getSource()).getText(),((JButton) e.getSource()).getA);
//                             addBtn( ((JButton) e.getSource()).getText(   ));
                            ((JButton) e.getSource()).setText(str);
                           // ((JButton) e.getSource()).setEnabled(false);
                            tmp++;
                            client.send2servera(tmp,500);
                            jta.append("Client:"+tmp+"\n");
                            b=true;
//                            client.send2servera(((JButton) e.getSource()).getText());
                           // win();
                        }

                    }
                });



        }
    }



public void addMsg(String inStr){

    jta.append("Server:"+inStr+"\n");

    }

    public void addBtn(int inbtn){
        jta.append("Server:"+ inbtn +"\n");



    }
    public String getIP(){

        return jtfip.getText();
    }

    public  void po(int i){
        jbtn[i].getText();
    }
public void win(){

    if(str=="O"||str=="X") {
        if (jbtn[0].getText() ==str && jbtn[1].getText() == str && jbtn[2].getText() == str) {JOptionPane.showMessageDialog(this, str+" has win");}
        if (jbtn[3].getText() == str && jbtn[4].getText() ==str && jbtn[5].getText() == str) {JOptionPane.showMessageDialog(this, str+" has win");}
        if (jbtn[6].getText() == str && jbtn[7].getText() == str && jbtn[8].getText() == str) {JOptionPane.showMessageDialog(this, str+" has win");}
        if (jbtn[0].getText() == str && jbtn[3].getText() == str&& jbtn[6].getText() == str) {JOptionPane.showMessageDialog(this, str+" has win");}
        if (jbtn[1].getText() == str && jbtn[4].getText() == str&& jbtn[7].getText() ==str) {JOptionPane.showMessageDialog(this,str+" has win");}
        if (jbtn[2].getText() == str&& jbtn[5].getText() ==str&& jbtn[8].getText() ==str) {JOptionPane.showMessageDialog(this, str+" has win");}
        if (jbtn[0].getText() == str&& jbtn[4].getText() ==str && jbtn[8].getText() == str) {JOptionPane.showMessageDialog(this, str+" has win");}
        if (jbtn[2].getText() ==str && jbtn[4].getText() == str && jbtn[6].getText() == str) {JOptionPane.showMessageDialog(this, str+" has win");}


    }
}

    public static void main(String[] args) {

        Clientf clientf = new Clientf();
        clientf.setVisible(true);
    }


    }







