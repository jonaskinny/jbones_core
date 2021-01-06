package org.jbones.core;

import java.io.Serializable;

/**
   Class that represents a Key object.
*/
public class Key implements Serializable, Cloneable {
   private static final long serialVersionUID=0L;
   private String theKey = "";
  
   /**
      returns the key value of this object as a string.
   */
   public String getVal()
   {
      return theKey;
   }
   public long getVal_long(){
      try{
         return Long.parseLong(theKey);
      }catch (NumberFormatException nfe){
         return -1;                                 	
      }
   }
   /**
      returns true if the string value of this object's key value
      is equal to the string value of the parameter's key value.
      functions as string.equals(string2).
   */
   public boolean equals(Object o){
      try{
         return ((Key)o).getVal().equals(getVal());
      }catch (Exception e){
         return false;
      }
   }
   private Key(){}
   public Key(String arg) {
      theKey = arg;
   }
   public Key(long arg)
   {
      theKey = String.valueOf(arg);
   }
   /**
      will return the same hashcode value for any Key or Key subclass
      with same key value;  this class and its subclasses are safe to be
      used as key values in collections that operate on hashcode uniqueness.
   */
   public int hashCode(){
      return theKey.hashCode();
   }
   public String toString() {
      return getVal();
   }
}
