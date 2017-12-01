import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import static java.lang.System.in;

public class Serverf extends JFrame{

    private JTextArea jta = new JTextArea("");
    private JTextField jtf = new JTextField();
    private JTextField jtfport = new JTextField("1723");
    private JButton  jbtn[] =new JButton[10];
private JButton btnstart = new JButton("Start");
private JButton btnstop = new JButton("Stop");
private JButton btnexit = new JButton("Exit");
private JButton btnsend = new JButton("Send");
private  JLabel jlb1 = new JLabel("Server IP:");
private  JLabel jlb2 = new JLabel("10.51.3.69");
private  JLabel jlb3 = new JLabel("Port:");
    private  JScrollPane jsp = new JScrollPane(jta);
private JPanel jp1 = new JPanel(new GridLayout(3,3,3,3));
private JPanel jp2 = new JPanel(new GridLayout(8,1,3,3));
private JPanel jp3 = new JPanel(new GridLayout(1,1,3,3));
private  Container cp;
    private Server serv;





    public Serverf(){
        init();
    }
    private  void init(){
        this.setTitle("server");
   this.setBounds(100,100,700,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
   cp=this.getContentPane();
   cp.setLayout(new BorderLayout(5,5));
cp.add(jp1,BorderLayout.CENTER);
cp.add(jp2,BorderLayout.EAST);
cp.add(jp3,BorderLayout.WEST);
cp.add(jtf,BorderLayout.SOUTH);
    jp2.add(jlb1);
    jp2.add(jlb2);
    jp2.add(jlb3);
    jp2.add(jtfport);
    jp2.add(btnstart);
    jp2.add(btnstop);
    jp2.add(btnexit);
    jp2.add(btnsend);
    jp3.add(jsp);
     jsp.setPreferredSize(new Dimension(200,400));
     jta.setLineWrap(true);
        btnstart.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serv = new Server(Serverf.this);
                serv.start();
                jta.append("waiting connect in ...\n");
                ((JButton)e.getSource()).setEnabled(false);

            }
        });
        btnstop.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Server.CloseSocket();
                btnstop.setEnabled(false);
                btnstart.setEnabled(true);
            }
        });
        btnexit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnsend.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            serv.send2client(jtf.getText());
                jta.append("Server:"+jtf.getText()+"\n");
                jtf.setText("");
            }
        });

for(int i = 0 ;i<9 ;i++){
    jbtn[i] = new JButton();
    jp1.add(jbtn[i]);

}




    }
    public void addMsg(String inStr){
        jta.append("Client"+inStr+"\n");
    }

    public static void main(String[] args) {
        Serverf serverf = new Serverf();
        serverf.setVisible(true);

    }


}
