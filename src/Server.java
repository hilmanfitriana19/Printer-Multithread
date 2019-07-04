import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*; 
import static java.util.Collections.list;
import java.util.concurrent.ConcurrentLinkedQueue;
  
// Server class 
public class Server{     
    public static void main(String[] args) throws IOException  {                 
        ArrayList<Printer> queue = new ArrayList<>();
        queue.add(new Printer(1));
        queue.add(new Printer(2));
        queue.add(new Printer(3));        
        ServerSocket ss = new ServerSocket(5056);         
        System.out.println("server run");                               
        Thread t1 = new Printer(queue.get(0).getid(),queue.get(0).getQueue());                
        Thread t2 = new Printer(queue.get(1).getid(),queue.get(1).getQueue());                
        Thread t3 = new Printer(queue.get(2).getid(),queue.get(2).getQueue());                
        System.out.println("1 "+queue.get(0).getQueueSize());
        System.out.println("2 "+queue.get(1).getQueueSize());
        System.out.println("3 "+queue.get(2).getQueueSize());
        t1.start();
        t2.start();
        t3.start();
        while (true){ 
            Socket s = null;             
            try { 
                s = ss.accept();                   
                System.out.println("A new client is connected : " + s); 
                System.out.println("Assigning new thread for this client");                  
                Runnable ch = new ClientHandler(s,queue);                
                new Thread(ch).start();                
            }catch (Exception e){ 
                s.close(); 
                e.printStackTrace(); 
            } 
        } 
    }      
} 