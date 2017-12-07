package well_game;//import sun.misc.IOUtils;

import javafx.collections.ObservableArray;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;

import java.net.Socket;
import java.net.UnknownHostException;


/**
 * Created by jackwang on 2017/12/1.
 */
public class Client extends Thread{
    private InetAddress ipAdr;
    private Socket socket;
    private PrintStream outStream;
    private PrintStream outStreamb;
    private BufferedReader inStream;
    private BufferedReader inStreamb;
    private StringWriter writer;
    private Clientf cf;
    public Client(Clientf cef){

        cf=cef;
    }
    public void run(){
        try{

            ipAdr = InetAddress.getLocalHost();
            socket = new Socket(cf.getIP(),2555);
            outStream = new PrintStream(socket.getOutputStream());
            outStreamb = new PrintStream(socket.getOutputStream());
            inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            inStreamb = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            send2server("client is connected");
            String str= "";
            while (!(str=inStream.readLine()).equals("")){
                cf.addMsg(str);

            }
            while (!(str=inStreamb.readLine()).equals("")){
//                cf.addBtn(str);

            }
        }catch(UnknownHostException e){
            JOptionPane.showMessageDialog(this.cf,"client 錯誤"+e);

        }catch (IOException ioe){
            JOptionPane.showMessageDialog(this.cf,"client 錯誤"+ioe);

        }



    }


    public void send2server(String msg){
        try{
            if(outStream!=null){
                outStream.println(msg);
            }else{
                JOptionPane.showMessageDialog(this.cf,"請先連結SERVER");
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this.cf,"error"+e.toString());
        }
    }
//    public void send2servera(String msg, Object j[]){
//        try{
//            if(outStreamb!=null){
////                ObservableArray
//
//                    outStreamb.println(msg, j[]);
//
//            }else{
//                JOptionPane.showMessageDialog(this.cf,"請先連結SERVER");
//            }
//        }
//        catch (Exception e){
//            JOptionPane.showMessageDialog(this.cf,"error"+e.toString());
//        }
//    }
    public void CloseSocket(){
        try{
            inStream.close();
            socket.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this.cf,"error"+e.toString());

        }
    }

//    public String convertStreamToString(InputStream is) {
//        StringWriter writer = new StringWriter();
//        IOUtils.copy(inputStream, writer);
//        String theString = writer.toString();
//        String theString = IOUtils.toString(inputStream);
//    }
}
