package org.jbones.core.dao;

import java.util.List;

/**
	Interface for deleting data transfer objects.
*/
   public interface DDAO extends DAO {
   /**
   * Deletes all data based on the long used.
   * Subclasses should implement this function.
   */
	public boolean delete(long arg) throws DeleteException;
}
