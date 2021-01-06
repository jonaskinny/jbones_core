package org.jbones.core.util;

import java.util.*;
import java.sql.*;
import java.text.*;

import org.jbones.core.*;


/** 
	Class that contains useful functions common to date related activities.
*/
public class DateUtil {
         
   public static java.util.Date getDate(){
      return new java.util.Date();
   }
   public static Timestamp getTimestamp(java.util.Date date){
      return new Timestamp(date.getTime());
   }
   public static java.sql.Date getSqlDate(java.util.Date date){
      return new java.sql.Date(date.getTime());
   }
   public static java.sql.Date getSqlDate(String dateString,String date_format){
     try {
          SimpleDateFormat format = new SimpleDateFormat(date_format);
          java.util.Date utilDate = format.parse(dateString);
          return getSqlDate(utilDate);
      } catch (ParseException e) {
          Log.getLog(Log.OUT).log(CoreException.getStackTrace(e));
          throw new RuntimeException(e.getMessage());
      }
  }

}

  

   
