package org.jbones.core;
/**
	Tagging interface for objects that represent a copyable object.
*/
public interface Copyable {
   /**
   intended to be a deep copy including new copies of any 
   contained objects.
   */
   public Object copy(Object o) throws ObjectCopyException, CloneNotSupportedException, NoSuchMethodException;
}

