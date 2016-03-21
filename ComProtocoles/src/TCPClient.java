import java.io.*; 
import java.net.*;

class TCPClient 
{       
    private static Socket s;
    private static DataInputStream in = null;
    private static DataOutputStream out = null;
    
public static void main(String args[]) throws IOException, SocketException
  {   
    
            String input;
            String outcoming;
            boolean check=true;
            BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
            input = inFromUser.readLine();
            String upper=input.toUpperCase();
    try{
        while(check){
            Socket clientSocket = new Socket("localhost", 1234);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes(input + '\n');
            
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //for timeout
            clientSocket.setSoTimeout(1000);
            outcoming = inFromServer.readLine();
            System.out.println("From Server : "+ outcoming);
            
            if (outcoming.equals(upper)){
                System.out.println("From Server : "+ outcoming);
                clientSocket.close();
                check=false;
            }
            else {
             try{
                System.out.println("Else condition");
                sendRept();
                System.out.println("we resend msg to server "+ out.size());
               
             }catch (IOException ex) {
                System.out.println("IO Exception: "+ ex.getMessage() );
            }
            }
        }
  }catch(SocketException e){
      System.out.println("Socket Exception: "+ e.getMessage() );
  }
        
  }
        private static void sendRept() throws IOException {
		s = new Socket("localhost", 1234);
		in = new DataInputStream(s.getInputStream());
		out = new DataOutputStream(s.getOutputStream());
		out.writeBytes("REPT");
                
	}  
    }
