package com.example.aurythmedelaproduction;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WebSocketManager {
    private static final String TAG = "WebSocket";
    private WebSocket webSocket;

    // --- Callbacks ---
    public interface OnConnected {
        void run();
    }
    public interface OnError {
        void run(String error);
    }
    public interface OnMessageReceived {
        void run(String message);
    }

    // --- Méthode principale pour se connecter ---
    public void connect(String url, OnConnected onConnected, OnError onError, OnMessageReceived onMessageReceived) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        webSocket = client.newWebSocket(request, new WebSocketListener() {

            @Override
            public void onOpen(WebSocket ws, okhttp3.Response response) {
                Log.d(TAG, "Connexion ouverte");
                if (onConnected != null) onConnected.run();
            }

            @Override
            public void onMessage(WebSocket ws, String text) {
                Log.d(TAG, "Message reçu: " + text);
                if (onMessageReceived != null) onMessageReceived.run(text);
            }

            @Override
            public void onMessage(WebSocket ws, ByteString bytes) {
                Log.d(TAG, "Message binaire reçu: " + bytes.hex());
            }

            @Override
            public void onFailure(WebSocket ws, Throwable t, okhttp3.Response response) {
                Log.e(TAG, "Erreur WebSocket: " + t.getMessage());
                if (onError != null) onError.run(t.getMessage());
            }
        });
    }

    public void send(String message) {
        if (webSocket != null) {
            webSocket.send(message);
        }
    }

    public void close() {
        if (webSocket != null) {
            webSocket.close(1000, "Fermeture volontaire");
        }
    }
}
