package org.jbones.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import org.jbones.core.*;

/**
   Class that represents a data transfer object.
   DTO's represent simple/aggregated/composed data and may
   include meta-data related to the trasfer (source/date etc).
   A typical DTO use is transfering persistant entity values.
*/
public abstract class DTO implements Serializable, Cloneable {
   private static final long serialVersionUID=0L;
   // identifier field is used to identify the entity irrespective of 
   // persistance ids or other attributes.  its a 'handle'
   private static String INVALID_VALUE_STRING = "";
   private String identifier = INVALID_VALUE_STRING; // initialize so no npe
   private static long INVALID_VALUE_LONG = 0L;
   private long id = INVALID_VALUE_LONG; // initialize so no npe
   protected static List<String> uniqueFieldList = new ArrayList<String>();

   /**
      Field used to identify a DTO.
   */
   public String getIdentifier() {
      return identifier;
   }
   /**
      Field used to identify a DTO.
   */
   public void setIdentifier(String identifier) {
      this.identifier = identifier;
   }
   public long getId() {
      return id;
   }
   public void setId(long id) {
      this.id = id;
   }
   public String getUniqueFields() {
      return uniqueFieldList.toString();
   }
   /**
      deep clone the fields and sub objects.
      this object is cloned, its referenced objects are
      still pointing to the same references as the original.
      use with caution.
   */
   protected Object clone() throws CloneNotSupportedException {
      try {
          DTO dto = (DTO) super.clone();
          return dto;
      } catch (Exception e){
        throw new CloneNotSupportedException(new ObjectCloneException(CoreException.getStackTrace(e)).getMessage());
      }
   }

}

