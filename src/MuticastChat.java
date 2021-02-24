import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MuticastChat extends Thread{

    private static String user = null;
    private static InetAddress endereco;
    private static int porta;

    public MuticastChat() {
    }

    @Override
    public void run(){
        try{

            byte[] buffer = new byte[64];
            MulticastSocket socket = new MulticastSocket(porta);
            socket.joinGroup(endereco);

            System.out.println("ante do while");

            while (true){

                DatagramPacket receberPacote = new DatagramPacket(buffer, buffer.length);
                socket.receive(receberPacote);

                String mensagem = new String(receberPacote.getData());

                if(!mensagem.contains(user)){
                    System.out.println("\r                 ");
                    System.out.println("\n" + mensagem + "\n");
                    System.out.println("Digite a meensagem: ");
                }

                buffer = new byte[64];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//run

    public static void main(String[] args) {

        if(args.length != 2){
            System.out.println("Parametros incorretos: java MulticastChat<multicast> <porta>");
            System.exit(1);
        }

        try{

            endereco = InetAddress.getByName(args[0]);
            porta = Integer.parseInt(args[1]);

            Thread t = new MuticastChat();
            System.out.println("Criado thread");
            t.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
