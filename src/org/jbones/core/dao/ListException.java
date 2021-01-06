package org.jbones.core.dao;

import org.jbones.core.CoreException;

/** 
	Class that represents an exception occured during a process on an object
  that implements some interface that contains a method of this exception's name.
*/
public class ListException extends CoreException {
private static final long serialVersionUID=-8531041506599859615L;
   public ListException(String s) {
      super(s);
   }
   
   public ListException(Exception e) {
      super(getStackTrace(e));
   }
}
