package org.jbones.core;

import org.jbones.core.util.*;
/**
	Class that represents an object Factory in the abstract sense.
*/
public abstract class Factory implements Service {
   public abstract void initialize() throws CoreException;
   private boolean safeToUse = false;

   public String getName() throws CoreException {
      // should return the name of the subclass
      try {
          return ClassName.name(this.getClass());
      } catch(Exception e){
            Log.getLog(Log.ERR).log("problem getting Factory name");
            throw new RuntimeException(CoreException.getStackTrace(e));
      }
   }
   public boolean safeToUse() throws CoreException {
      return safeToUse;
   }
   public void setUnsafeToUse() {
      safeToUse = false;
   }
   public void setSafeToUse() {
      safeToUse = true;
   }
}
