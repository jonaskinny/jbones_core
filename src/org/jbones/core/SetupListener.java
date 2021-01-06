package org.jbones.core;

import java.util.EventListener;
/**
   Interface that represents an object that 'cares' about 
   Setup Events and is attached to the object that receives 
   the Setup Event.
*/
public interface SetupListener extends EventListener {
                      	
   public void onSetup(SetupEvent se) throws SetupException;
                      	
}
