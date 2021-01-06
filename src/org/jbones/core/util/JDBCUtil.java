package org.jbones.core.util;

import java.sql.*;

import org.jbones.core.*;


/** 
	Class that contains useful functions common to relational database activities.
*/
public class JDBCUtil {

	public static void release(Connection conn, ResultSet rst, Statement stmt, PreparedStatement pstmt){
		release(rst);
		release(stmt);
		release(pstmt);
      release(conn);
	}
   public static void release(Connection arg) {
		if (arg != null) {
			try {
				arg.close();
			} catch (Exception e) {
				Log.getLog(Log.ERR).log(CoreException.getStackTrace(e));
			}
		}
	}
	public static void release(ResultSet arg) {
		if (arg != null){
			try {
			arg.close();
			} catch (Exception e){
				Log.getLog(Log.ERR).log(CoreException.getStackTrace(e));
			}
		}
	}
	public static void release(Statement arg) {
		if (arg != null){
			try {
			arg.close();
			} catch (Exception e) {
				Log.getLog(Log.ERR).log(CoreException.getStackTrace(e));
			}
		}
	}
	public static void release(PreparedStatement arg) {
		if (arg != null){
			try {
			arg.close();
			} catch (Exception e) {
				Log.getLog(Log.ERR).log(CoreException.getStackTrace(e));
			}
		}
	}
	public static void rollback(Connection arg) {
		if (arg != null){
			try {
			arg.rollback();
			} catch (Exception e) {
            Log.getLog(Log.ERR).log("Rollback operation failed on Connection " + arg);
            Log.getLog(Log.ERR).log(CoreException.getStackTrace(e));
            try {
               Log.getLog(Log.ERR).log("Connection warnings are : " + arg.getWarnings());
            } catch (SQLException se) {
               Log.getLog(Log.ERR).log("Rollback operation logging failed to get Connection Warnings");
               Log.getLog(Log.ERR).log(CoreException.getStackTrace(se));
            }
			}
		}
	}
 public static void commit(Connection arg) {
		if (arg != null){
			try {
			arg.commit();
			} catch (Exception e) {
            Log.getLog(Log.ERR).log("Commit operation failed on Connection " + arg);
            Log.getLog(Log.ERR).log(CoreException.getStackTrace(e));
            try {
               Log.getLog(Log.ERR).log("Connection warnings are : " + arg.getWarnings());
            } catch (SQLException se) {
               Log.getLog(Log.ERR).log("Commit operation logging failed to get Connection Warnings");
               Log.getLog(Log.ERR).log(CoreException.getStackTrace(se));
            }
			}
		}
	}
	public static void setAutoCommit(Connection arg, boolean autocommit) {
		if (arg != null){
			try {
   			arg.setAutoCommit(autocommit);
   		} catch (SQLException se) {
            Log.getLog(Log.ERR).log("setAutoCommit operation failed on Connection " + arg);
            Log.getLog(Log.ERR).log(CoreException.getStackTrace(se));
   		}
		}
	}
}

