package org.jbones.core.dao;

import org.jbones.core.*;

import java.util.Properties;

   /**
   * Simple audit.
   */
public interface Audit {

   public abstract void initialize(Properties p) throws CoreException;
   
   public abstract boolean audit();
}

