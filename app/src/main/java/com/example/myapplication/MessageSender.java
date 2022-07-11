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


    @Override
    protected Void doInBackground(String... strings) {
        String message=strings[0];

        try {
            socket = new Socket("10.3.5.111",9876);
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
