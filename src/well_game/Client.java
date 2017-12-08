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
    private GameFrame cf;
    public Client(GameFrame cef){

        cf=cef;
    }
    public void run(){
        try{

            ipAdr = InetAddress.getLocalHost();
            socket = new Socket("127.0.1.1",2555);
            outStream = new PrintStream(socket.getOutputStream());
            outStreamb = new PrintStream(socket.getOutputStream());

            // create a new output stream
            OutputStream os = new FileOutputStream("test.txt");

            // craete a new input stream
            InputStream is = new FileInputStream("test.txt");
            inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            inStreamb = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            send2server("client is connected");
            String str= "";
            int tmp= 0;
//            while (!(str=inStream.readLine()).equals("")){
//                cf.addMsg(str);
//
//            }
            while (!(tmp==inStreamb.read())){
                cf.addxy(tmp,tmp);

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
    public void send2servera(int msg,int s){
        try{
            if(outStreamb!=null){
//                ObservableArray
                outStreamb.println(msg);
                outStreamb.println(s);


            }else{
               // JOptionPane.showMessageDialog(this.cf,"請先連結SERVER");
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this.cf,"error"+e.toString());
        }
    }
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
