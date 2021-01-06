package org.jbones.core;
/**
	Tagging interface for objects that represent a service.
*/
public interface Service {
 public void initialize() throws CoreException;
 public String getName() throws CoreException;
 public boolean safeToUse() throws CoreException;
 public void setUnsafeToUse();
 public void setSafeToUse();
}
