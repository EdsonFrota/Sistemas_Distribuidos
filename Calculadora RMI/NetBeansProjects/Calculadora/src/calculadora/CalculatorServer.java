
package calculadora;
import java.net.MalformedURLException;
import java.rmi.Naming; 
import java.rmi.RemoteException;

public class CalculatorServer { 
   public CalculatorServer() { 
      try { 
            Calculator c = new CalculatorImpl(); 
            Naming.rebind("//localhost/CalculatorService", c); 
      } catch (MalformedURLException | RemoteException e) { 
            System.out.println("Trouble: " + e); 
      }
   } 
   public static void main(String args[]) { 
       CalculatorServer calculatorServer = new CalculatorServer();
   }
} 