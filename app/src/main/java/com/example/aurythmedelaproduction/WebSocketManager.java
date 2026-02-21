package com.example.aurythmedelaproduction;

import android.util.Log;

import org.json.JSONObject;

import java.util.function.Consumer;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;


public class WebSocketManager {
    private static final String TAG = "WebSocket";
    private WebSocket webSocket;
    private static WebSocketManager instance;
    private JSONObject lastActivityConfig;
    private Consumer<String> fragmentListener;
    private Consumer<String> globalListener;

    // --- Callbacks ---
    public interface OnConnected {
        void run();
    }
    public interface OnError {
        void run(String error);
    }

    // --- Méthode principale pour se connecter ---
    public void connect(String url, OnConnected onConnected, OnError onError) {

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
            public void onMessage(WebSocket ws, String message) {
                Log.d(TAG, "Message reçu: " + message);

                try {
                    JSONObject json = new JSONObject(message);
                    String type = json.getString("type");

                    if (type.equals("ACTIVITY_CONFIG")) {
                        lastActivityConfig = json;
                        Log.d(TAG, "ACTIVITY_CONFIG sauvegardé");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (globalListener != null)
                    globalListener.accept(message);

                if (fragmentListener != null)
                    fragmentListener.accept(message);
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

    public static WebSocketManager getInstance() {
        if(instance == null)
            instance = new WebSocketManager();
        return instance;
    }
    public JSONObject getLastActivityConfig() {
        return lastActivityConfig;
    }
    public void setFragmentListener(Consumer<String> listener) {
        this.fragmentListener = listener;
    }
    public void setGlobalListener(Consumer<String> listener) {
        this.globalListener = listener;
    }
}
