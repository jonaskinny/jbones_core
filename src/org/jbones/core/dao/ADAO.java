package org.jbones.core.dao;

import java.util.List;

/**
	Interface for archiving and unarchiving data transfer objects.
*/
   public interface ADAO extends DAO {
   /**
   * Simple archive function for a persisted object based on the DTO used.
   * Subclasses should implement this function.
   */
   public boolean archive(long arg) throws ArchiveException, UniqueException;
   /**
   * Simple unarchive function for a persisted object based on the long used.
   * Subclasses should implement this function.
   */
	public boolean unarchive(long arg) throws CreateException, UniqueException;
	
	public abstract DTO readArchive(long arg) throws ReadException;
	
	public abstract List listArchive() throws ListException;
}
