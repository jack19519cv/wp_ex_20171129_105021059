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
public class Client extends Thread{
    private InetAddress ipAdr;
    private Socket socket;
    private PrintStream outStream;
    private BufferedReader inStream;
    private Clientf cf;
    public Client(Clientf cef){
        this.setDaemon(true);
        cf=cef;
    }
    public void run(){
        try{
            ipAdr = InetAddress.getLocalHost();
            socket = new Socket(cf.getIP(),2555);
            outStream = new PrintStream(socket.getOutputStream());
            inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            send2server("client is connected");
            String str= "";
            while (!(str=inStream.readLine()).equals("")){
                cf.addMsg(str);
            }
        }catch(UnknownHostException e){
            System.out.println("client 錯誤"+e);
        }catch (IOException ioe){
            System.out.println("client 錯誤"+ioe);
        }



    }


    public void send2server(String msg){
        try{
            if(outStream!=null){
                outStream.println(msg);
            }else{
                System.out.println("請先連結SERVER");
            }
        }
        catch (Exception e){
            System.out.println("error"+e.toString());

        }
    }
    public void CloseSocket(){
        try{
            inStream.close();
            socket.close();
        }catch (Exception e){
            System.out.println("error"+e.toString());
        }
    }
}
