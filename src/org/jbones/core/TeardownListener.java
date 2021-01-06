package org.jbones.core;

import java.util.EventListener;

public interface TeardownListener extends EventListener {
                      	
   public void onTeardown(TeardownEvent te) throws TeardownException;
                     	
}
