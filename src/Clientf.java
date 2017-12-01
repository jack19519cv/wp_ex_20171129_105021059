import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.in;

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
        client = new Client(Clientf.this);
        for(int i = 0 ;i<9 ;i++){
            jbtn[i] = new JButton();
            jp1.add(jbtn[i]);

        }
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

//        byte buff[]= new byte[1024];
//
//
//            try {
//                InetAddress adr = InetAddress.getLocalHost();
//                System.out.println("connect time...");
//
//                Socket s = new Socket("127.0.1.1", 2555);
//                System.out.println("has been link...");
//                InputStream in = s.getInputStream();
//                int n = in.read(buff);
//                System.out.println("server:");
//                jta.setText(new String(buff, 0, n));
//                in.close();
//                s.close();
//            } catch (Exception ex) {
//                System.out.println("發現" + ex + " 的 client意外");
//            }



        btnsend.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            client.send2server(jtf.getText());
                jta.append("Client:"+jtf.getText()+"\n");
                jtf.setText("");
            }
        });}

public void addMsg(String inStr){
    jta.append("Server:"+inStr+"\n");
    }

    public String getIP(){
        return jtfip.getText();
    }


    public static void main(String[] args) {

        Clientf clientf = new Clientf();
        clientf.setVisible(true);
    }

    }







