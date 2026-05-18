import java.util.Random;

class PingThread extends Thread {
    private Random random = new Random();
    
    public void run() {
        while (true) {
            System.out.println("Ping");
            try {
                int delay = 500 + random.nextInt(1000);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

class PongThread extends Thread {
    private Random random = new Random();
    
    public void run() {
        while (true) {
            System.out.println("PONG");
            try {
                int delay = 500 + random.nextInt(1000);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

public class PingPongDemo {
    public static void main(String[] args) {
        PingThread ping = new PingThread();
        PongThread pong = new PongThread();
        
        ping.start();
        pong.start();
    }
}




