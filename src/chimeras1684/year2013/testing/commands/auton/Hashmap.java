/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chimeras1684.year2013.testing.commands.auton;

import java.util.Hashtable;

/**
 *
 * @author Arhowk
 */
public class Hashmap {
    Hashtable main;
    
    public Hashmap(){
        main = new Hashtable();
    }
    
// <editor-fold desc="public void getI  (String alpha, String beta, int     set)" defaultstate="collapsed">
    public int getI (String alpha, String beta){
        try {
            return ((Integer)((Hashtable)main.get(alpha)).get(beta)).intValue();
        } catch (Exception e) {
            return 0;
        }
    }
// <editor-fold desc="public void setI  (String alpha, String beta, int     set)" defaultstate="collapsed">
    public double getD (String alpha, String beta){
        try {
            return ((Double)((Hashtable)main.get(alpha)).get(beta)).doubleValue() ;
        } catch (Exception e) {
            return 0;
        }
    }
    public byte getBY (String alpha, String beta){
        try {
            return ((Byte)((Hashtable)main.get(alpha)).get(beta)).byteValue();
        } catch (Exception e) {
            return 0;
        }
    }
    public String getT (String alpha, String beta){
        try {
            return ((String)   ((Hashtable)main.get(alpha)).get(beta));
        } catch (Exception e) {
            return "";
        }
    }
    public short getSH (String alpha, String beta){
        try {
            return ((Short)((Hashtable)main.get(alpha)).get(beta)).shortValue();
        } catch (Exception e) {
            return 0;
        }
    }
    public long getL (String alpha, String beta){
        try {
            return ((Long)((Hashtable)main.get(alpha)).get(beta)).longValue();
        } catch (Exception e) {
            return 0;
        }
    }
    public float getF (String alpha, String beta){
        try {
            return ((Float)((Hashtable)main.get(alpha)).get(beta)).floatValue();
        } catch (Exception e) {
            return 0;
        }
    }
    public boolean getBO (String alpha, String beta){
        try {
            return ((Boolean)((Hashtable)main.get(alpha)).get(beta)).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }
    public char getC (String alpha, String beta){
        try {
            return ((Character)((Hashtable)main.get(alpha)).get(beta)).charValue();
        } catch (Exception e) {
            return 0;
        }
    }
    public Object getO (String alpha, String beta){
        try {
            return ((Hashtable)main.get(alpha)).get(beta);
        } catch (Exception e) {
            return null;
        }
    }
    public Hashtable get (String alpha, String beta){
        try {
            return (Hashtable)main.get(alpha);
        } catch (Exception e) {
            return null;
        }
    }
    
// <editor-fold desc="public void setI  (String alpha, String beta, int     set)" defaultstate="collapsed">
    public void setI (String alpha, String beta, int set){
        if(!(main.get(alpha) instanceof Hashtable)){
            main.put(alpha, new Hashtable());
        }
        ((Hashtable)main.get(alpha)).put(beta, new Integer(set));
    }
    // </editor-fold>
    // <editor-fold desc="public void setD  (String alpha, String beta, double  set)" defaultstate="collapsed">
    public void setD (String alpha, String beta, double set){
        if(!(main.get(alpha) instanceof Hashtable)){
            main.put(alpha, new Hashtable());
        }
        ((Hashtable)main.get(alpha)).put(beta, new Double(set));
    }
    // </editor-fold>
    // <editor-fold desc="public void setBY (String alpha, String beta, byte    set)" defaultstate="collapsed">
    public void setBY (String alpha, String beta, byte set){
        if(!(main.get(alpha) instanceof Hashtable)){
            main.put(alpha, new Hashtable());
        }
       ((Hashtable)main.get(alpha)).put(beta, new Byte(set));
    }
    // </editor-fold>
    // <editor-fold desc="public void setT  (String alpha, String beta, String  set)" defaultstate="collapsed">
    public void setT (String alpha, String beta, String set){
        if(!(main.get(alpha) instanceof Hashtable)){
            main.put(alpha, new Hashtable());
        }
        ((Hashtable)main.get(alpha)).put(beta, set);
    }
    // </editor-fold>
    // <editor-fold desc="public void setSH (String alpha, String beta, short   set)" defaultstate="collapsed">
    public void setSH (String alpha, String beta, short set){
        if(!(main.get(alpha) instanceof Hashtable)){
            main.put(alpha, new Hashtable());
        }
       ((Hashtable)main.get(alpha)).put(beta, new Short(set));
    }
    // </editor-fold>
    // <editor-fold desc="public void setL  (String alpha, String beta, long    set)" defaultstate="collapsed">
    public void setL (String alpha, String beta, long set){
        if(!(main.get(alpha) instanceof Hashtable)){
            main.put(alpha, new Hashtable());
        }
        ((Hashtable)main.get(alpha)).put(beta, new Long(set));
    }
    // </editor-fold>
    // <editor-fold desc="public void setF  (String alpha, String beta, float   set)" defaultstate="collapsed">
    public void setF (String alpha, String beta, float set){
        if(!(main.get(alpha) instanceof Hashtable)){
            main.put(alpha, new Hashtable());
        }
        ((Hashtable)main.get(alpha)).put(beta, new Float(set));
    }
    // </editor-fold>
    // <editor-fold desc="public void setBO (String alpha, String beta, boolean set)" defaultstate="collapsed">
    public void setBO (String alpha, String beta, boolean set){
        if(!(main.get(alpha) instanceof Hashtable)){
            main.put(alpha, new Hashtable());
        }
        ((Hashtable)main.get(alpha)).put(beta, (set ? Boolean.TRUE : Boolean.FALSE));
    }
    // </editor-fold>
    // <editor-fold desc="public void setC  (String alpha, String beta, char    set)" defaultstate="collapsed">
    public void setC (String alpha, String beta, char set){
        if(!(main.get(alpha) instanceof Hashtable)){
            main.put(alpha, new Hashtable());
        }
       ((Hashtable)main.get(alpha)).put(beta, new Character(set));
    }
    // </editor-fold>
    // <editor-fold desc="public void setO  (String alpha, String beta, Object  set)" defaultstate="collapsed">
    public void setO (String alpha, String beta, Object set){
        if(!(main.get(alpha) instanceof Hashtable)){
            main.put(alpha, new Hashtable());
        }
        ((Hashtable)main.get(alpha)).put(beta, set);
    }
    // </editor-fold>
}
