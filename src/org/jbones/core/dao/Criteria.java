package org.jbones.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.jbones.core.Key;

/**
   Class that represents a Criteria object.
*/
public abstract class Criteria implements Serializable, Cloneable {

   private static final long serialVersionUID=-9122547078957428477L;

   /**
      returns the value of the key as a string.
   */
   public List getCriterion() {
      return null;
   }
   
   /**
      returns the value of the key as a string.
   */
   public List getCriterionString() {
      return null;
   }

   /**
      returns true if the string value of this object's key value
      is equal to the string value of the parameter's key value.
      functions as string.equals(string2).
   */
   public boolean equals(Object o){
      return false;
   }

   /**
      will return the same hashcode value for any Key or Key subclass
      with same key value;  this class and its subclasses are safe to be
      used as key values in collections that operate on hashcode uniqueness.
   */
   public int hashCode(){
      return -1;
   }

   //inner class for individual criterion to be used as 'line items'
   protected class Criterion {
      protected String attribute;
      protected String comparitor;
      protected String value;
      
      public Criterion(String attribute,String comparitor,String value) {
             Criterion c = new Criterion();
             c.attribute = attribute;
             c.comparitor = comparitor;
             c.value = value;
      }
      private Criterion () {

      }
   }
   // jcole remove had to comment out for compilation in datastore package
   /*
   private Criteria () {
      
   }
   */
}
