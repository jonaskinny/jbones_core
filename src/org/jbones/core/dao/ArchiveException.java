package org.jbones.core.dao;

import org.jbones.core.CoreException;

/** 
	Class that represents an exception occured during a process on an object
  that implements some interface that contains a method of this exception's name.
*/
public class ArchiveException extends CoreException {
private static final long serialVersionUID=-7652414822624381208L;
   public ArchiveException(String s) {
      super(s);
   }
   
   public ArchiveException(Exception e) {
      super(getStackTrace(e));
   }
}
