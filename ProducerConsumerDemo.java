class Buffer {
    private int[] items;
    private int size;
    private int count = 0;
    private int in = 0;
    private int out = 0;
    
    public Buffer(int size) {
        this.size = size;
        items = new int[size];
    }
    
    public synchronized void produce(int item) {
        while (count == size) {
            try {
                System.out.println("Buffer full. Producer waiting...");
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        
        items[in] = item;
        System.out.println("Produced: " + item + " at position " + in);
        in = (in + 1) % size;
        count++;
        
        notify();
    }
    
    public synchronized int consume() {
        while (count == 0) {
            try {
                System.out.println("Buffer empty. Consumer waiting...");
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        
        int item = items[out];
        System.out.println("Consumed: " + item + " from position " + out);
        out = (out + 1) % size;
        count--;
        
        notify();
        return item;
    }
}

class Producer extends Thread {
    private Buffer buffer;
    
    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }
    
    public void run() {
        int item = 1;
        while (true) {
            buffer.produce(item);
            item++;
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

class Consumer extends Thread {
    private Buffer buffer;
    
    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }
    
    public void run() {
        while (true) {
            buffer.consume();
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(3);
        
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);
        
        producer.start();
        consumer.start();
    }
}
