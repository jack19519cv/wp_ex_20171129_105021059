package maga;

import java.io.*;
import java.net.Socket;

public class Client extends Thread{
    private PrintStream out;
    private BufferedReader inStream;
    private ClientFrame cf;
    private Socket socket;
    private DataOutputStream dataOutStream;//在此我使用PrintStream將int進行編寫和送出
    private DataInputStream dataInStream;//在此我使用PrintStream將int進行接收和讀取
    int  input=0;
    public Client(ClientFrame ClientFrame) {
        cf = ClientFrame;
    }
    @Override
    public void run() {
        try {
           socket=new Socket("127.0.0.1",1723);
           out=new PrintStream(socket.getOutputStream());
            dataOutStream = new DataOutputStream(socket.getOutputStream());//由於是將資料編寫並送出，所以是Output
            dataInStream =new DataInputStream(socket.getInputStream());//接收傳進來的資料，所以是Input
//           client2server("已成功與Server連線");

            input=dataInStream.readInt();
            while(input!=0){
                cf.addMsg(input);
                input=0;
                dataOutStream.flush();
            }



        }catch (Exception e){
            System.out.println("資料傳輸錯誤"+e);
        }
    }
    public void client2server(int msg){
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
