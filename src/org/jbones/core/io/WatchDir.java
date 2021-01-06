package org.jbones.core.io;

import org.jbones.core.*;
import org.jbones.core.log.*;
import org.jbones.core.util.*;

import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;
import static java.nio.file.LinkOption.*;
import java.nio.file.attribute.*;
import java.io.*;
import java.util.*;

public abstract class WatchDir implements Service {

    private WatchService watcher;
    private Map<WatchKey,Path> keys;
    private boolean recursive = false;
    private boolean trace = false;

    // INTERFACE
   private boolean safeToUse = false;
   protected Properties p;

   public void initialize() throws CoreException {
      try {
         watcher = FileSystems.getDefault().newWatchService();
         keys = new HashMap<WatchKey,Path>();
         p = new PropertiesUtil().loadPropertiesFileFromClassPath("/org/jbones/core/io.properties");
         StringTokenizer tokens = new StringTokenizer(p.getProperty("WatchDir.paths"), ",", false);
         String watchDirPath = null;
         while(tokens.hasMoreTokens()) {
            watchDirPath = tokens.nextToken();
            Log.getLog(Log.OUT).log("loading Watch Directory " + watchDirPath);
            try {
               register(Paths.get(watchDirPath));
            } catch (Exception e) {
               setUnsafeToUse();
               Log.getLog(Log.ERR).log("problem loading Watch Directory " + watchDirPath);
               Log.getLog(Log.ERR).log(e.getMessage());
               Log.getLog(Log.ERR).log(CoreException.getStackTrace(e));
            }
         }
      } catch (Exception e) {
         setUnsafeToUse();
         Log.getLog(Log.ERR).log("problem initializing WatchDir");
         Log.getLog(Log.ERR).log(e.getMessage());
         Log.getLog(Log.ERR).log(CoreException.getStackTrace(e));
      }
      processEvents();
      setSafeToUse();
   }
   public String getName() throws CoreException {
      // should return the name of the subclass
      try {
          return ClassName.name(this.getClass());
      } catch(Exception e){
            Log.getLog(Log.ERR).log("problem getting class name");
            throw new RuntimeException(CoreException.getStackTrace(e));
      }
   }
   public boolean safeToUse() throws CoreException {
      return safeToUse;
   }
   public void setUnsafeToUse() {
      safeToUse = false;
   }
   public void setSafeToUse() {
      safeToUse = true;
   }
   public WatchDir() {
      // default constructor since our framework uses class.forname.newinstance() and 
      //this class defines a non-defualt constructor.
   }
    // INTERFACE

    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }

    /**
     * Register the given directory with the WatchService
     */
    private void register(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        if (trace) {
            Path prev = keys.get(key);
            if (prev == null) {
                Log.getLog(Log.OUT).log(dir + " registered");
            } else {
                if (!dir.equals(prev)) {
                    Log.getLog(Log.OUT).log(dir + " updated from " + prev);
                }
            }
        }
        keys.put(key, dir);
    }

    /**
     * Register the given directory, and all its sub-directories, with the
     * WatchService.
     */
    private void registerAll(final Path start) throws IOException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                throws IOException
            {
                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Process all events for keys queued to the watcher
     */
    void processEvents() {
        for (;;) {

            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                Log.getLog(Log.ERR).log("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                // TBD - provide example of how OVERFLOW event is handled
                if (kind == OVERFLOW) {
                    continue;
                }

                // Context for directory entry event is the file name of entry
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path child = dir.resolve(name);

                // notify any other components of the event
                // any interested component needs to be directly 
                // referenced in the notifyOfEvent method
                notifyOfEvent(event,child);

                // if directory is created, and watching recursively, then
                // register it and its sub-directories
                if (recursive && (kind == ENTRY_CREATE)) {
                    try {
                        if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
                            registerAll(child);
                        }
                    } catch (IOException x) {
                        // ignore to keep sample readable
                    }
                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }
    protected abstract void notifyOfEvent (WatchEvent event, Path child);
}