package org.jbones.core.dao;

import org.jbones.core.CoreException;

/** 
	Class that represents an exception occured during a process on an object
  that implements some interface that contains a method of this exception's name.
*/
public class DeleteException extends CoreException {
private static final long serialVersionUID=-6310007633229678236L;
   public DeleteException(String s) {
      super(s);
   }
   
   public DeleteException(Exception e) {
      super(getStackTrace(e));
   }
}
