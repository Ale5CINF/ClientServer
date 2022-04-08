import java.net.*;
import java.io.*;

public class EchoClient {
	public static void main(String[] args) throws IOException {
		Socket socket=null;
		BufferedReader in=null, stdIn=null;
		PrintWriter out=null;
		InetAddress addr;
		addr = InetAddress.getByName(args[0]);
		
		try {
			socket = new Socket(addr, 25565);
			System.out.println("Client avviato!");
			
			// creazione stream di input da socket
			InputStreamReader isr = new InputStreamReader( socket.getInputStream());
			in = new BufferedReader(isr);
			
			// creazione stream di output su socket
			OutputStreamWriter osw = new OutputStreamWriter( socket.getOutputStream());
			BufferedWriter bw = new BufferedWriter(osw);
			out = new PrintWriter(bw, true);
			
			// creazione stream di input da tastiera
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			String userInput;
			
			// ciclo di lettura da tastiera, invio al server e stampa risposta
			while (true){
				userInput = stdIn.readLine();
				out.println(userInput);
				if (userInput.equals("CHIUDI")) break;
				System.out.println("Invio messaggio: " + in.readLine());
			}
		} catch (UnknownHostException e) {
			System.out.println("Non trovo host locale!");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Impossibile collegarsi al server!");
			System.exit(1);
		}
		stdIn.close();
		socket.close();
	}
} 