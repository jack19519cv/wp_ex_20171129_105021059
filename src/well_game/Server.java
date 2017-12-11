package well_game;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by jackwang on 2017/12/1.
 */
public class Server  extends  Thread{
    private InetAddress ipAdr;
    private ServerSocket sSocket;//伺服器端的Socket，接收Client端的連線
    private static Socket socket;//Server和Client之間的連線通道
    private PrintStream outStream;
    private PrintStream outStreamb;//在此我使用PrintStream將字串進行編寫和送出
    private static BufferedReader inStream;
    private static BufferedReader inStreamb;//在此我使用BufferedReader將資料進行接收和讀取
    private static DataInputStream dis;
    private static DataOutputStream dos;
    private GameFrameS sf;

    public Server(GameFrameS serf){

    sf=serf;
    try{
        ipAdr = InetAddress.getLocalHost();
        sSocket = new ServerSocket(2555);//建立伺服器端的Socket，並且設定Port
    }catch(UnknownHostException e){

        JOptionPane.showMessageDialog(this.sf, "server 錯誤"+e);
    }catch (IOException ioe){

        JOptionPane.showMessageDialog(this.sf, "server 錯誤"+ioe);
    }
        this.setDaemon(true);
    }
public void run(){
    try{
        socket = sSocket.accept();//等待伺服器端的連線，若未連線則程式一直停在這裡
//        outStream = new PrintStream(socket.getOutputStream());
        outStreamb = new PrintStream(socket.getOutputStream());//由於是將資料編寫並送出，所以是Output
//        inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        inStreamb = new BufferedReader(new InputStreamReader(socket.getInputStream()));//接收傳進來的資料，所以是Input
//        send2client("connect complete");
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
//        String str = "";
//        int tmp= 350-61;
//        int tmp1=400-79;
        int number ;
                //= dis.readInt();
//        while (!(number=inStream.readLine())){
//            System.out.println("number=" + number);
//
//        }
        while((number = dis.readInt()) != 0) {
                     sf.addxy(number,number);
        }
//        while (!(tmp=inStreamb.readLine().equals(350-61))){
//            sf.addxy(tmp);
//
//        }
    }catch (Exception e) {
        JOptionPane.showMessageDialog(this.sf, "server_run錯誤"+e);

    }

}

public String getIP(){

    return ipAdr.getHostAddress();
}
//public void send2client(String msg){
//    try{
//        if(outStream!=null){
//            outStream.println(msg);//將資料編寫進串流內
//        }else{
//            JOptionPane.showMessageDialog(this.sf, "請先連結客戶端");
//        }
//    }
//    catch (Exception e){
//        JOptionPane.showMessageDialog(this.sf, "error"+e.toString());
//    }
//}
    public void send2clientax(int x){
        try{
            if(outStreamb!=null){
//                ObservableArray
                //  outStreamb.write(msg);
//                outStreamb.println(x);//將資料編寫進串流內
//                outStreamb.flush();//清空緩衝區並送出資料
                dos.writeInt(x);
                dos.flush();
            }else{
                // JOptionPane.showMessageDialog(this.cf,"請先連結客戶端");
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this.sf,"error"+e.toString());
        }
    }
    public void send2clientay(int y){
        try{
            if(outStreamb!=null){
//                ObservableArray
                //  outStreamb.write(msg);
                dos.writeInt(y);
//                outStreamb.println(y);//將資料編寫進串流內
//                outStreamb.flush();//清空緩衝區並送出資料
                dos.flush();

            }else{
                // JOptionPane.showMessageDialog(this.cf,"請先連結客戶端");
            }

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this.sf,"error"+e.toString());
        }
    }
    public static void CloseSocket() {
        try {
            inStream.close();
            socket.close();//一旦連線建立成功，且不需要再接收其他連線，則可關閉ServerSocket
        } catch (Exception e) {

            System.out.println("error" + e.toString());
        }
    }
}

