import java.util.Scanner;

class MaxThread extends Thread {
    private int[] arr;
    private int max;
    
    public MaxThread(int[] arr) {
        this.arr = arr;
    }
    
    public void run() {
        max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("Max thread found maximum: " + max);
    }
    
    public int getMax() {
        return max;
    }
}

class AverageThread extends Thread {
    private int[] arr;
    private double average;
    
    public AverageThread(int[] arr) {
        this.arr = arr;
    }
    
    public void run() {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        average = (double) sum / arr.length;
        System.out.println("Average thread found average: " + average);
    }
    
    public double getAverage() {
        return average;
    }
}

public class MaxAverageDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of elements in the array: ");
        int n = sc.nextInt();
        
        int[] numbers = new int[n];
        
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            numbers[i] = sc.nextInt();
        }
        
        System.out.println("\nArray elements entered:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println("\n");
        
        MaxThread maxThread = new MaxThread(numbers);
        AverageThread avgThread = new AverageThread(numbers);
        
        maxThread.start();
        avgThread.start();
        
        try {
            maxThread.join();
            avgThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
        
        int maxValue = maxThread.getMax();
        double avgValue = avgThread.getAverage();
        double result = maxValue / avgValue;
        
        System.out.println("\n--- Final Result ---");
        System.out.println("Maximum value: " + maxValue);
        System.out.println("Average value: " + avgValue);
        System.out.println("Result (max / average): " + result);
        
        sc.close();
    }
}
