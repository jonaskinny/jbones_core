package org.jbones.core.dao;

import org.jbones.core.ServiceLocatorException;

import java.util.List;

   /**
	  Interface for managing DTO persistance.
   */
   public interface DAO {
   /**
   * Simple create function for a persisted object based on the DTO used.
   * Subclasses should implement this function.
   */
   public boolean create(DTO arg) throws CreateException, UniqueException;
	/**
   * Simple read function for a persisted object based on the long used.
   * Subclasses should implement this function.
   */
	public DTO read(long arg) throws ReadException;
	/**
   * Simple read function for a persisted object based on the long used.
   * Subclasses should implement this function.
   */
	public DTO readList(long arg) throws ReadException;
	/**
   * Simple find function for a List of persisted objects based on the Criteria used.
   * Subclasses should implement this function.
   */
	public List find(Criteria arg) throws FindException;
	/**
   * Simple list function for a List of all persisted objects.
   * Subclasses should implement this function.
   */
	public List list() throws ListException;
	/**
   * Simple update of all properties for this persisted object.
   * Any properties not set prior to a call to this method will
   * result in the default values being used.  Typically this means
   * null for Strings and objects, -1 for integers etc.
   * Subclasses should implement this function.
   */
	public boolean update(DTO arg) throws UpdateException, UniqueException;
	/**
   * Simple create statement for data based on the Criteria used.
   * Any properties not set prior to a call to this method will
   * result in the default values being used.  Typically this means
   * null for Strings and objects, -1 for integers etc.
   * Subclasses should implement this function.
   */
	public Criteria createCriteria(DTO arg) throws CreateException;
	/**
   * Creates a Criteria Object to be used with this object.
   */
	public Criteria createCriteria() throws CreateException;
}
