package org.jbones.core.util;

import java.util.*;
/**
Class that implements an ordered hashtable.  the 
keys in this hashtable will be returned in the order
they were added.
*/
public class OrderedHashtable extends Hashtable {
private static final long serialVersionUID=-1399400480224735074L;

	private Vector ids;
	
	/**
	Constructs an empty OrderedHashtable.
	*/
	public OrderedHashtable(){
		super();
		ids = new Vector();
	}
	private Vector getIds(){
	   if (ids == null){
	    ids = new Vector();
    }
    return ids;
 }


	/**
	Constructs an empty OrderedHashtable with the specified initial capacity.
	*/
	public OrderedHashtable(int initialCapacity){
		super(initialCapacity);
		ids = new Vector(initialCapacity);
	}
	/**
	Appends a key/value pair to the end of this OrderedHashtable.
	Neither the key nor the value can be null. Returns the previous value 
	of the specified key in this hashtable, or null if it did not have one.
	*/
	public Object put(Object key, Object value){
	  if (! getIds().contains(key)){
	  	getIds().addElement(key);
   }
   	return super.put(key,value);
	}
	/**
	Inserts a key/value pair to this OrderedHashtable at the specified index.
	All key/value pairs currently occupying the specified, or subsequent, index in this OrderedHashtable,
	will be shifted one index to the right (+1).
	0 = first index in this OrderedHashtable.
	*/
	public Object put(Object key, Object value, int index){
	  if (getIds().contains(key)){
	   getIds().remove(key);
    }
		getIds().add(index,key);
	  return super.put(key,value);
	}
	/**
	Returns the value Object at the specified index.
	*/
	public Object get(int index){
		//return super.get(getIds().get(index)); not supported in personal java
    return super.get(getIds().elementAt(index));
	}
	/**
	Clears this OrderedHashtable so that it contains no keys.
	*/
	public void clear(){
		super.clear();
		getIds().clear();
	}
	/**
	Removes the key/value pair at the specified index in this OrderedHashtable.
	Returns the value object removed from the specified index in this OrderedHashtable.
	*/
	public Object remove(int index)
   {
      Vector v = getIds();
      Object key = v.elementAt(index);
      v.removeElementAt(index);
		return super.remove(key);
	}

	/**
	Removes the key (and its corresponding value) from this hashtable.
	Returns the value object removed from this OrderedHashtable.
	*/
	public Object remove(Object key){
		getIds().removeElement(key);
		return super.remove(key);

	}

	/*
	Replaces the key/value pair at the specified index in this OrderedHashtable.
	Returns the value object previousely at the specified index in this OrderedHashtable.

	public Object set(Object key, Object value,int index){
		Object o = super.remove(getIds().set(index,key));
		super.put(key,value);
		return o;
	}
	*/
public Enumeration keys() {
   return getIds().elements();
}

 public int indexOf( Object key ) {
return getIds().indexOf(key);
}

public void merge( OrderedHashtable ht)
{
Enumeration keys = ht.keys();
while(keys.hasMoreElements())
{
Object k = keys.nextElement();
put( k, ht.get(k));
}
}


public Object clone() {
   OrderedHashtable oht = (OrderedHashtable)super.clone();
   oht.ids = (Vector)getIds().clone();
   return oht;
}
}
