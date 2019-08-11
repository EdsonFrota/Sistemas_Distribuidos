package calculadora;

import java.math.BigDecimal;
import java.rmi.RemoteException;

public class CalculatorImpl extends   
    java.rmi.server.UnicastRemoteObject implements Calculator {
           
    public CalculatorImpl() 
                throws java.rmi.RemoteException { 
                    super();
           } 

    @Override
           public BigDecimal adicao(BigDecimal Op1, BigDecimal Op2) 
                 throws java.rmi.RemoteException { 
                     return Op1 + Op2; 
           }
           public BigDecimal sububtracao(BigDecimal Op1, BigDecimal b) 
                 throws java.rmi.RemoteException { 
                     return Op1 - Op2; 
           } 
    @Override
           public BigDecimal multiplicacao(BigDecimal Op1, BigDecimal b) 
                 throws java.rmi.RemoteException {
                     return Op1 * Op2; 
           }

    @Override
           public BigDecimal divisao(BigDecimal a, BigDecimal b) 
                 throws java.rmi.RemoteException { 
                     return Op1 / Op2;
           } 

    @Override
    public BigDecimal subtracao(BigDecimal a, BigDecimal b) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
} 