package org.jbones.core.dao;

import org.jbones.core.CoreException;
/**
	Class that represents an exception occured during a process on an object
  that implements some interface that contains a method of this exception's name.
*/
public class CriteriaException extends CoreException {
private static final long serialVersionUID=-4257180472958827786L;
   public CriteriaException(String s) {
      super(s);
   }
   
   public CriteriaException(Exception e) {
      super(getStackTrace(e));
   }
}
