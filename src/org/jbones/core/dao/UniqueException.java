package org.jbones.core.dao;

import org.jbones.core.CoreException;

/** 
	Class that represents an exception occured during a process on an object
  that implements some interface that contains a method of this exception's name.
*/
public class UniqueException extends CoreException {
   private static final long serialVersionUID=-2395593747803773914L;
   public UniqueException(String s) {
      super(s);
   }
   
   public UniqueException(Exception e) {
      super(getStackTrace(e));
   }
}
