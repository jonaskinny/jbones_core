package org.jbones.core;

import java.io.Serializable;
/**
   Class that represents a value object.
   A typical Value object use is Color.RED.
*/
public abstract class VO implements Serializable, Cloneable {
   private static final long serialVersionUID=0L;
   /**
      deep clone the fields and sub objects.
      this object is cloned, its referenced objects are 
      still pointing to the same references as the original.
      use with caution.
   */
   protected Object clone() throws CloneNotSupportedException {
      try {
          VO vo = (VO) super.clone();
          return vo;
      } catch (Exception e){
        throw new CloneNotSupportedException(new ObjectCloneException(CoreException.getStackTrace(e)).getMessage());
      }
   }
   
}

