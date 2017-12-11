package new_one;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientFrame extends JFrame {
    private Container cp;
    protected JTextArea jtaTalkDate=new JTextArea();
    private JScrollPane jspTalkDate=new JScrollPane(jtaTalkDate);
    private JPanel center=new JPanel(new GridLayout(3,3,5,5));
    private JButton btn[]=new JButton[9];
    private JPanel east=new JPanel(new GridLayout(7,1,5,5));
    private JLabel jlbIP=new JLabel("Server IP:");
    private JTextField jtfIP=new JTextField();
    private JLabel jlbPort =new JLabel("Port");
    private JTextField jtfPort=new JTextField("1723");
    private JButton start=new JButton("Start");
    private JButton stop=new JButton("Stop");
    private JButton exit=new JButton("Exit");
    private JPanel south=new JPanel(new GridLayout(1,2,5,5));
    private JTextField jtfTalk=new JTextField();
    private JButton send=new JButton("Send");
    private Boolean flag=true;
    private Client client;
    public ClientFrame(){
        initComp();
    }
    private void initComp(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(1000,200,700,450);
        client=new Client(ClientFrame.this);
        cp=getContentPane();
        cp.setLayout(new BorderLayout(5,5));
        cp.add(BorderLayout.CENTER,center);
        cp.add(BorderLayout.EAST,east);
        east.setPreferredSize(new Dimension(100,450));
        cp.add(BorderLayout.WEST,jspTalkDate);
        jspTalkDate.setPreferredSize(new Dimension(150,450));
        jtaTalkDate.setEnabled(false);
        cp.add(BorderLayout.SOUTH,south);
        for(int i=0;i<btn.length;i++){
            btn[i]=new JButton();
            btn[i].setName(Integer.toString(i));
            center.add(btn[i]);
            btn[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton tmpBtn=(JButton)e.getSource();
                    if(flag==true){
                        tmpBtn.setText("O");
                        flag=!flag;
                    }else{
                        tmpBtn.setText("X");
                        flag=!flag;
                    }
                    flagFunction();
                }
            });
        }
        east.add(jlbIP);
        try {
            InetAddress idr = InetAddress.getLocalHost();
            jtfIP.setText(idr.getHostAddress());
        }catch(UnknownHostException e){
            JOptionPane.showMessageDialog(ClientFrame.this,"無法取得IP位址");
        }
        jtfIP.setEnabled(true);
        east.add(jtfIP);
        east.add(jlbPort);
        east.add(jtfPort);
        east.add(start);
        east.add(stop);
        east.add(exit);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jtaTalkDate.append("等待連接中...\n");
                client.start();
            }
        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                client.client2server(jtfTalk.getText());
                jtaTalkDate.append("Client:"+jtfTalk.getText()+"\n");
                jtfTalk.setText("");
            }
        });
        cp.add(BorderLayout.SOUTH,south);
        south.add(jtfTalk);
        south.add(send);

    }
    private void flagFunction(){
        if(!btn[0].getText().equals("")&&btn[0].getText().equals(btn[1].getText())&&btn[1].getText().equals(btn[2].getText())){
            JOptionPane.showMessageDialog(ClientFrame.this,btn[0].getText()+"  is win");
            stop();
        }else if(!btn[3].getText().equals("")&&btn[3].getText().equals(btn[4].getText())&&btn[4].getText().equals(btn[5].getText())){
            JOptionPane.showMessageDialog(ClientFrame.this,btn[0].getText()+"  is win");
            stop();
        }else if(!btn[6].getText().equals("")&&btn[6].getText().equals(btn[7].getText())&&btn[7].getText().equals(btn[8].getText())){
            JOptionPane.showMessageDialog(ClientFrame.this,btn[0].getText()+"  is win");
            stop();
        }else if(!btn[0].getText().equals("")&&btn[0].getText().equals(btn[4].getText())&&btn[4].getText().equals(btn[8].getText())){
            JOptionPane.showMessageDialog(ClientFrame.this,btn[0].getText()+"  is win");
            stop();
        }else if(!btn[2].getText().equals("")&&btn[2].getText().equals(btn[4].getText())&&btn[4].getText().equals(btn[6].getText())){
            JOptionPane.showMessageDialog(ClientFrame.this,btn[0].getText()+"  is win");
            stop();
        }
    }
    private void start(){
        for(int i=0;i<btn.length;i++){
            btn[i].setText("");
            btn[i].setEnabled(true);
        }
    }
    private void stop(){
        for(int i=0;i<btn.length;i++){
            btn[i].setEnabled(false);
        }
    }
    public void addMsg(String str){
        jtaTalkDate.append("Server:"+str+"\n");
    }
}
