package com.example.myapplication;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageSender extends AsyncTask<String,Void,Void> {
    Socket socket;
    DataOutputStream dataOutputStream;
    PrintWriter printWriter;
    private String ip_address;

    public void setIP(String ip_entry){
        ip_address = ip_entry;
    }

    @Override
    protected Void doInBackground(String... strings) {
        String message= strings[0];

        // set to variable ip_address
        // "10.3.7.169" "10.3.7.205"
        // 192.168.231.110

        try {
            socket = new Socket(ip_address,9876);
            printWriter= new PrintWriter(socket.getOutputStream());
            printWriter.write(message);
            printWriter.flush();
            printWriter.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
