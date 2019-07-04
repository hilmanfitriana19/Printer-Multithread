
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author my pc
 */
class Printer extends Thread{
    private int id;    
    private LinkedList queue = new LinkedList();

    
    public Printer(int id) {
        this.id=id;
    }
    
    public Printer(int id, LinkedList queue){
        this.id=id;
        this.queue=queue;
    }
    
    public int getid(){
        return id;
    }        

    public LinkedList getQueue() {
        return queue;
    }
    public synchronized int getQueueSize(){
        return this.queue.size();
    }
    public synchronized void Tambahqueue(Message ps){
        this.queue.addLast(ps);
    }
    public synchronized void delfirst(){
        this.queue.remove(0);
    }
    
    @Override
    public void run(){ 
        String received; 
        while(true){
            try{                         
                while(this.getQueueSize()!=0){
                    System.out.println("1 "+this.getQueueSize());                                        
                    Message ps = (Message)this.queue.get(0);                    
                    Socket l = ps.getSocket();    
                    received = ps.getPesan();
                    DataOutputStream dos = new DataOutputStream(l.getOutputStream()); 
                    dos.writeUTF("Printing process in "+this.id+" data :"+received);                                                   
                    System.out.println("printer "+this.id+" print request from  "+l.getPort()+"\ndata : "+received);
                    Thread.sleep(10000);
                    this.delfirst();                    
                }
         } catch (IOException e) { 
                e.printStackTrace();          
            } catch (InterruptedException ex) {
                Logger.getLogger(Printer.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
}
