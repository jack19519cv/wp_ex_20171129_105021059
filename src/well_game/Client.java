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
    private static DataInputStream dis;
    private static DataOutputStream dos;
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
//            send2server("client is connected");
            String str= "";
            int tmp=350-61;
            int tmp1=400-79;
//            while (!(str=inStream.readLine()).equals("")){
//                cf.addMsg(str);
//
//            }
//            while (!(tmp==inStreamb.read()&&tmp1==inStreamb.read())){
//                cf.addxy(tmp,tmp1);
//
//            }
            int number ;
            //= dis.readInt();
//        while (!(number=inStream.readLine())){
//            System.out.println("number=" + number);
//
//        }
            while((number = dis.readInt()) != 0) {
                cf.addx(number);
            }
        }catch(UnknownHostException e){
            JOptionPane.showMessageDialog(this.cf,"client 錯誤"+e);

        }catch (IOException ioe){
            JOptionPane.showMessageDialog(this.cf,"client 錯誤"+ioe);

        }



    }


//    public void send2server(String msg){
//        try{
//            if(outStream!=null){
//                outStream.println(msg);
//
//            }else{
//                JOptionPane.showMessageDialog(this.cf,"請先連結SERVER");
//            }
//        }
//        catch (Exception e){
//            JOptionPane.showMessageDialog(this.cf,"error"+e.toString());
//        }
//    }
public void send2serverx(int x){
    try{
        if(outStreamb!=null){
//                ObservableArray
            //  outStreamb.write(msg);
//                outStreamb.println(x);//將資料編寫進串流內
//                outStreamb.flush();//清空緩衝區並送出資料
            dos.writeInt(x);
            dos.flush();
        }else{
            // JOptionPane.showMessageDialog(this.cf,"請先連結SERVER");
        }
    }
    catch (Exception e){
        JOptionPane.showMessageDialog(this.cf,"error"+e.toString());
    }
}
    public void send2servery(int y){
        try{
            if(outStreamb!=null){
//                ObservableArray
                //  outStreamb.write(msg);
                dos.writeInt(y);
//                outStreamb.println(y);//將資料編寫進串流內
//                outStreamb.flush();//清空緩衝區並送出資料
                dos.flush();

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
