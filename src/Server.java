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
    private static BufferedReader inStream;
    private Serverf sf;

    public Server(Serverf serf){
    sf=serf;
    try{
        ipAdr = InetAddress.getLocalHost();
        sSocket = new ServerSocket(2555);
    }catch(UnknownHostException e){
        System.out.println("server 錯誤"+e);
    }catch (IOException ioe){
        System.out.println("server 錯誤"+ioe);
    }

    }
public void run(){
    try{
        socket = sSocket.accept();
        outStream = new PrintStream(socket.getOutputStream());
        inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        send2client("connect complete");
        String str = "";
        while (!(str=inStream.readLine()).equals("")){
            sf.addMsg(str);
        }
    }catch (Exception e) {
        System.out.println("server_run錯誤"+e);
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
            System.out.println("請先連結客戶端");
        }
    }
    catch (Exception e){
        System.out.println("error"+e.toString());

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

