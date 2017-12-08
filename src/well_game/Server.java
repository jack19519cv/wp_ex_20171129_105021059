package well_game;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by jackwang on 2017/12/1.
 */
public class Server  extends  Thread{
    private InetAddress ipAdr;
    private ServerSocket sSocket;
    private static Socket socket;
    private PrintStream outStream;
    private PrintStream outStreamb;
    private static BufferedReader inStream;
    private static BufferedReader inStreamb;
    private GameFrameS sf;

    public Server(GameFrameS serf){

    sf=serf;
    try{
        ipAdr = InetAddress.getLocalHost();
        sSocket = new ServerSocket(2555);
    }catch(UnknownHostException e){

        JOptionPane.showMessageDialog(this.sf, "server 錯誤"+e);
    }catch (IOException ioe){

        JOptionPane.showMessageDialog(this.sf, "server 錯誤"+ioe);
    }
        this.setDaemon(true);
    }
public void run(){
    try{
        socket = sSocket.accept();
        outStream = new PrintStream(socket.getOutputStream());
        outStreamb = new PrintStream(socket.getOutputStream());
        inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        inStreamb = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        send2client("connect complete");
        String str = "";
        int tmp= 0;
//        while (!(str=inStream.readLine()).equals("")){
//            sf.addMsg(str);
//
//        }
        while (!(tmp==inStreamb.read())){
            sf.addxy(tmp,tmp);

        }
    }catch (Exception e) {
        JOptionPane.showMessageDialog(this.sf, "server_run錯誤"+e);

    }

}

public String getIP(){

    return ipAdr.getHostAddress();
}
public void send2client(String msg){
    try{
        if(outStream!=null){
            outStream.println(msg);
        }else{
            JOptionPane.showMessageDialog(this.sf, "請先連結客戶端");
        }
    }
    catch (Exception e){
        JOptionPane.showMessageDialog(this.sf, "error"+e.toString());
    }
}
    public void send2clienta(int msg,int s){
        try{
            if(outStreamb!=null){
//                ObservableArray
                //  outStreamb.write(msg);
                outStreamb.println(msg);
                outStreamb.println(s);

            }else{
                // JOptionPane.showMessageDialog(this.cf,"請先連結SERVER");
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this.sf,"error"+e.toString());
        }
    }
    public static void CloseSocket() {
        try {
            inStream.close();
            socket.close();
        } catch (Exception e) {

            System.out.println("error" + e.toString());
        }
    }
}

