import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*;

class ClientHandler implements Runnable{     
    final DataInputStream dis; 
    final DataOutputStream dos; 
    final Socket s; 
    ArrayList<Printer> queue;
    
    ClientHandler(Socket s,ArrayList<Printer> queue) throws IOException {        
        this.s = s;
        this.queue = queue;
        this.dis = new DataInputStream(s.getInputStream()); 
        this.dos = new DataOutputStream(s.getOutputStream()); 
    }
  
    @Override
    public void run(){        
        String received;                 
//        Thread t1 = new Printer(queue.get(0).getid(),queue.get(0).getQueue());                
//        Thread t2 = new Printer(queue.get(1).getid(),queue.get(1).getQueue());                
//        Thread t3 = new Printer(queue.get(2).getid(),queue.get(2).getQueue());                
//        System.out.println("1 "+this.queue.get(0).getQueueSize());
//        System.out.println("2 "+this.queue.get(1).getQueueSize());
//        System.out.println("3 "+this.queue.get(2).getQueueSize());
//        t1.start();
//        t2.start();
//        t3.start();
System.out.println("1s "+queue.get(0).getQueueSize());
        System.out.println("2s "+queue.get(1).getQueueSize());
        System.out.println("3s "+queue.get(2).getQueueSize());
        while (true){ 
            try {                 
                dos.writeUTF("Enter 'exit' to stop\nText to print : ");                
                received = dis.readUTF();                
                if(received.equals("Exit")){  
                    System.out.println("Client " + this.s + " sends exit..."); 
                    System.out.println("Closing this connection."); 
                    this.s.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                } 
                Message message = new Message(received,this.s);                
                Printer pr;
                int queue1 =queue.get(0).getQueueSize();
                int queue2 =queue.get(1).getQueueSize();
                int queue3 =queue.get(2).getQueueSize();
                if(queue1<=queue2){
                    if(queue1<=queue3){
                        pr = this.queue.get(0);                                                                            
                    }else{
                        pr = queue.get(2);                                                                                                
                    }
                }else{
                    pr = queue.get(1);                                        
                }
                pr.Tambahqueue(message);                    
                System.out.println("1a "+this.queue.get(0).getQueueSize());
        System.out.println("2a "+this.queue.get(1).getQueueSize());
        System.out.println("3a "+this.queue.get(2).getQueueSize());
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        }
        try{             
            this.dis.close(); 
            this.dos.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
    }     
} 