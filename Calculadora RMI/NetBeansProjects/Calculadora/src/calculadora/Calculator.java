
package calculadora;

import java.math.BigDecimal;
import java.rmi.RemoteException;


public interface Calculator 
       extends java.rmi.Remote { 

    public BigDecimal adicao(BigDecimal a, BigDecimal b) 
                   throws java.rmi.RemoteException; 
    
            public BigDecimal subtracao(BigDecimal a, BigDecimal b) 
                   throws java.rmi.RemoteException; 
            
            public BigDecimal multiplicacao(BigDecimal a, BigDecimal b) 
                   throws java.rmi.RemoteException; 
            
    public BigDecimal divisao(BigDecimal a, BigDecimal b) 
                   throws java.rmi.RemoteException; 
}