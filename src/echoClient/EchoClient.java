package echoClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {

    public static void main(String[] args) {
        //　ソケットや入出力用のストリームの宣言
        Socket echoSocket = null;
        DataOutputStream os = null;
        BufferedReader is = null;

        // ポート9999番を開く
        try {
            echoSocket = new Socket("localhost", 30000);
            os = new DataOutputStream(echoSocket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost");
        }

        // サーバーにメッセージを送る
        if (echoSocket != null && os != null && is != null) {
            try {
                // メッセージを送ります
                os.writeBytes("HELLO\n");

                // サーバーからのメッセージを受け取り画面に表示します
                String responseLine;
                if ((responseLine = is.readLine()) != null) {
                    System.out.println("Server: " + responseLine);
                }

                // 開いたソケットなどをクローズ
                os.close();
                is.close();
                echoSocket.close();
            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException: " + e);
            }
        }
    }
}
