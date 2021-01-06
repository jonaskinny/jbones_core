package org.jbones.core;

/**
	Class that represents an exception occured during a call to a feature
   that has not yet been implemented.
*/
public class IFException extends CoreException {
   private static final long serialVersionUID=0L;
   public IFException() {
      super("Feature not complete yet...");
   }
}
