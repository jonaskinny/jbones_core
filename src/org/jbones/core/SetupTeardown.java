package org.jbones.core;

/** 
	Interface for applications to create and destroy dependancies,object definitions etc.
*/
public interface SetupTeardown {
/**
   This function will be called when the system sets up.
   */
	public void setup() throws SetupException;
   /**
   Adds a listener to the object implementing this interface.
   */	
	public void addSetupListener(SetupListener arg) throws SetupException;
	/**
   Removes a listener to the object implementing this interface.
   */	
	public void removeSetupListener(SetupListener arg) throws SetupException;
	/**
   This function will be called when the system tears down.
   */
	public void teardown() throws TeardownException;
   /**
   Adds a listener to the object implementing this interface.
   */	
	public void addTeardownListener(TeardownListener arg)
   throws TeardownException;
	/**
   Removes a listener to the object implementing this interface.
   */	
	public void removeTeardownListener(TeardownListener arg)
   throws TeardownException;
   /**
   Notifies a listener of the object of a SetupEvent.
   */
   public void notifySetup(SetupEvent arg) throws SetupException;
   /**
   Notifies a listener of the object of a TeardownEvent.
   */
   public void notifyTeardown(TeardownEvent arg) throws TeardownException;
}
