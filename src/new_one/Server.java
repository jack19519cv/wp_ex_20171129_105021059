package new_one;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    private InetAddress ipAdr;
    private ServerFrame sf;
    private ServerSocket svs;
    private Socket socket;
    private Socket socket2;
    private PrintStream outStream;
    private BufferedReader inStream;
    public Server(ServerFrame ServerFrame){
        sf=ServerFrame;
        try{
            svs=new ServerSocket(1723);
        }catch (Exception e){
            System.out.println("erro"+e.toString());
        }

    }

    @Override
    public void run() {
        try {
            socket=svs.accept();
            outStream = new PrintStream(socket.getOutputStream());
            inStream=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            send2client("已與伺服器成功連線");
            String str;
            while(!(str=inStream.readLine()).equals("")){
                sf.addMsg(str);

            }
        }catch (Exception e){
            System.out.println("資料傳輸錯誤"+e.toString());
        }
    }
    public void send2client(String msg){
        if(outStream!=null){
           outStream.println(msg);
        }else{
            System.out.println("outStream is null");
        }
    }
}
