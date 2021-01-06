package org.jbones.core;
/**
	Tagging interface for objects that represent a WrapperObject.
   Wrapper objects should be treated as their undelying counterparts, but
   the Wrapper class' methods should adjust their behavior to suit the 
   Wrapper's intended management system... ie a ConnectionWrapper should not 
   be set to null after close but should instead be marked as closed so the 
   management system can return it to the pool.
*/
public interface Wrapper {

}
