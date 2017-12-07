package well_game;

/**
 * Created by jackwang on 2017/12/2.
 */
public class test {


        public static void main(String[] args) {
            Thread thread = new Thread(
                    new Runnable(){
                        public void run(){
                            while (true){
                                System.out.print("*");
                            }
                        }
                    }

            );
           thread.setDaemon(true);
            thread.start();
        }


}
