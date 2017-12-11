package maga;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    private InetAddress ipAdr;
    private ServerFrame sf;
    private ServerSocket svs;//伺服器端的Socket，接收Client端的連線
    private Socket socket;//Server和Client之間的連線通道
    private Socket socket2;
//    private PrintStream outStream;//在此我使用PrintStream將字串進行編寫和送出
//    private BufferedReader inStream;//在此我使用BufferedReader將資料進行接收和讀取
    private DataOutputStream dataOutStream;//在此我使用PrintStream將int進行編寫和送出
    private DataInputStream dataInStream;//在此我使用PrintStream將int進行接收和讀取
    int  input=0;
    public Server(ServerFrame ServerFrame){
        sf=ServerFrame;
        try{
            svs=new ServerSocket(1723);//建立伺服器端的Socket，並且設定Port
        }catch (Exception e){
            System.out.println("erro"+e.toString());
        }

    }

    @Override
    public void run() {
        try {
            socket=svs.accept();//等待伺服器端的連線，若未連線則程式一直停在這裡
            dataOutStream = new DataOutputStream(socket.getOutputStream());//由於是將資料編寫並送出，所以是Output
            dataInStream =new DataInputStream(socket.getInputStream());//接收傳進來的資料，所以是Input
            
            System.out.println("已與伺服器成功連線");
            //送出端的編寫必須和接收端的接收Class相同
            //使用Socket的getInputStream()和getOutputStream()進行接收和發送資料
            //想要寫入字串可以用 PrintStream；想要把各種基本資料型態，如 int, double...等的 "值" 輸出，可以用 DataOutputStream；想要把整個物件 Serialize，則可以用 ObjectOutputStream。

            input=dataInStream.readInt();

            while(input!=0){
                sf.addMsg(input);
                input=0;
                dataOutStream.flush();

            }

        }catch (Exception e){
            System.out.println("資料傳輸錯誤"+e.toString());
        }
    }
    public void send2client(int msg){
        try {
            if (dataOutStream != null) {
                dataOutStream.writeInt(msg);
                dataOutStream.flush();

            } else {
                System.out.println("outStream is null");
            }
        }catch (Exception e){
            System.out.println( e);

        }
    }
}
