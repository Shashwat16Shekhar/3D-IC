/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;

import java.text.DecimalFormat;

/**
 *
 * @author Vinay,Priya,Shashwat
 */
public class FormatNumbers {
    
    DecimalFormat decformat=new DecimalFormat("#0.00000");
    public double formatDecimal(double d){
        return Double.parseDouble(decformat.format(d));
    }
    
    
}
