import java.io.*;
import java.net.*;
import java.sql.Timestamp;
import java.util.Date;

public class Server
{
	public static void main(String[] args) throws IOException {
	    //Avvio il server sulla porta selezionata
        ServerSocket serverSocket = new ServerSocket(25566);
        System.out.println("Avvio server scoket sulla porta 25565");
        Socket clientSocket=null;
        BufferedReader textReceive=null;
        PrintWriter out=null;
        try {
            //Accetto le richieste dal client
            clientSocket = serverSocket.accept();
            
            //Creo uno stream in input dal client
            InputStreamReader InputStreamClient = new InputStreamReader(clientSocket.getInputStream());
            textReceive = new BufferedReader(InputStreamClient);
            
            //Creo uno stream in output dal client
            OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedWriter bw = new BufferedWriter(osw);
            out = new PrintWriter(bw, true);
            //ciclo di ricezione dal client e invio di risposta
            while (true) {
                String str = textReceive.readLine();
				//Se mando il messaggio chiudi la connessione viene chiusa
                if (str.equals("DATA")){
					System.out.println("Comanda della data ricevuto!");
					Date date = new Date();
					System.out.println(new Timestamp(date.getTime()));
				}
                System.out.println("Nuovo messaggio: " + str);
                out.println(str);
            }
        } catch (IOException e) {
            //Errore generale chiudo tutto
            System.out.println("Impossibile accettare la richiesta!");
            System.exit(1);
        }
        //Chiudo le connessioni
        clientSocket.close();
        serverSocket.close();
	}
}
