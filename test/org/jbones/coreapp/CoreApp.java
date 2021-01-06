package org.jbones.coreapp;

import org.jbones.core.App;
import org.jbones.util.PropertiesUtil;
/**
	Class to illustrate how to define an App that uses org.jbones.core.
*/
public class CoreApp extends org.jbones.core.App {
   protected String appName() {
    return "coreapp";                         	
   }
   protected Properties getAppProperties() {
    Properties p = PropertiesUtil.loadPropertiesFileFromClassPath(
      "properties/org/jbones/coreapp/coreapp.properties",
      new Object().getClass().getClassLoader());
   }
}