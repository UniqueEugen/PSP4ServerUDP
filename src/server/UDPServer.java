package server;

import count.Counter;
import myLibrary.console.Console;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public final static int DEFAULT_PORT = 8001;
    public final String QUIT_CMD = "QUIT";
    public final String COUNT_CMD = "COUNT";
    public final String UNKNOWN_CMD = "Unknown command";

    public void runServer() throws IOException {
        DatagramSocket socket = null;
        try {
            boolean stopFlag = false;
            byte[] buf;
            socket = new DatagramSocket(DEFAULT_PORT);
            Console.log("UDPServer: Started on " + socket.getLocalAddress() + ":"
                    + socket.getLocalPort());
            while (!stopFlag) {
                buf = new byte[194];
                DatagramPacket recvPacket = new DatagramPacket(buf, buf.length);//создание объекта дейтаграммы для получения данных
                socket.receive(recvPacket);
                String cmd = new String(recvPacket.getData()).trim();
                Console.log("UDPServer: Command: " + cmd);
                DatagramPacket sendPacket = new DatagramPacket(
                        buf, 0, recvPacket.getAddress(), recvPacket.getPort()
                );
                int n = 0;//количество байт в ответе
                if (cmd.equals(COUNT_CMD)) {//проверка версии команды
                    socket.receive(recvPacket);
                    String DATA = new String(recvPacket.getData()).trim();
                    Counter counter = new Counter(DATA);
                    String answer = counter.count();
                    n = answer.getBytes().length;
                    System.arraycopy(answer.getBytes(), 0, buf, 0, n);
                } else if (cmd.equals(QUIT_CMD)) {
                    stopFlag = true;//остановка сервера
                    continue;
                } else {
                    n = UNKNOWN_CMD.getBytes().length;
                    System.arraycopy(UNKNOWN_CMD.getBytes(), 0, buf, 0, n);
                }
                sendPacket.setData(buf);//установить массив посылаемых данных
                sendPacket.setLength(n);//установить длину посылаемых данных
                socket.send(sendPacket);//послать сами данные
            } // while(server is not stopped)
            Console.log("UDPServer: Stopped");
        }
        finally {
            if (socket != null) {
                socket.close();//закрытие сокета сервера
            }
        }
    }

}
