import server.UDPServer;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        try {
            UDPServer udpSvr = new UDPServer();
            udpSvr.runServer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}