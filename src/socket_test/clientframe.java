package socket_test;

/**
 * Created by jackwang on 2017/12/9.
 */
public class clientframe {
    public static void main(String[] argv)
    {
        new Client("127.0.0.1", 2555).start();//建立物件，傳入IP和Port並執行等待接受連線的動作
        //由於此範例都在自己電腦上執行，所以IP設定為127.0.0.1
    }
}
