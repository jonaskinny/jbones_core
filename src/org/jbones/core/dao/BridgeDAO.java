package org.jbones.core.dao;

import java.util.List;
// tagging interface for a class that bridges 2 or more entities
// classes that implement this interface should return entity x
// based on a reference to entity y ... so we return reference 
// entities.  a typical class that implements this interface
// is a dao to a reference table containing foriegn keys to 
// entities.  passing a key to entity x should return a full
// entity y or a List of entity y ... and visa versa.
public interface BridgeDAO extends DDAO {
   // what is a parent and what is a child are arbitrary ..
   // what is important is that there is a restriction on
   // only 2 entities being represented in bridgeDAO tables...
   // so listChildren(long) uses the parent id to find all
   // children, and listParents(long) uses the child id to find
   // all parents.  the parents are the first entity in this class'
   // name, and the children are the second.  this enables interface
   // design on the bridgDAO level and specific named methods in the 
   // actions... so EstimateEffortAction bean uses EstimateEffortDAOJDBC.getEstimateEffortsbyEstimateId whould map to listChildren
   // and EstimateEffortAction bean uses EstimateEffortDAOJDBC.getEstimateEffortsByEffortId whould map to listChildren.
   // list was used instead of find since we are returning associative entities (look up table entries) and dont
   // want to load huge objects ... ie estimateEffort would not want to return a full Estimate object in itself
   // since we can only reach EstimateEffort objects via the Estimate(Parent in this case) id... so only build and 
   // return the List objects.
   public List<DTO> listChildren(long arg) throws ListException;
   
   public List<DTO> listParents(long arg) throws ListException;

}