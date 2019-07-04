
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author my pc
 */
public class Message {
    private String content;
    private Socket s;    

    public Message(String pesan, Socket s){
        this.content = pesan;
        this.s = s;        
    }    
    public String getPesan() {
        return this.content;
    }

    public Socket getSocket() {
        return this.s;
    }
    
        
}
