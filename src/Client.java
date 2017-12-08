import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client extends Thread{
    private PrintStream out;
    private BufferedReader inStream;
    private ClientFrame cf;
    private Socket socket;
    public Client(ClientFrame ClientFrame) {
        cf = ClientFrame;
    }
    @Override
    public void run() {
        try {
           socket=new Socket("127.0.0.1",1723);
           out=new PrintStream(socket.getOutputStream());
           inStream=new BufferedReader(new InputStreamReader(socket.getInputStream()));
           client2server("已成功與Server連線");
           String str= "";
           while(!(str=inStream.readLine()).equals("")){
               cf.addMsg(str);
           }
        }catch (Exception e){
            System.out.println("資料傳輸錯誤"+e);
        }
    }
    public void client2server(String msg){
        if(out!=null){
            out.println(msg);
        }else{
            System.out.println("outStream is null");
        }
    }
}
