package org.jbones.core.io;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemLoopException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;

import org.jbones.core.*;
import org.jbones.core.log.*;
import org.jbones.core.util.*;


public class BaseFileVisitor extends SimpleFileVisitor<Path> {

    final Path source;
    final Path target;

    public BaseFileVisitor(Path source, Path target) {
        this.source = source;
        this.target = target;
    }



    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Log.getLog(Log.OUT).log("BaseFileVisitor:preVisitDirectory()");
        Path newDirectory= target.resolve(source.relativize(dir));
        try{
            Log.getLog(Log.OUT).log("BaseFileVisitor:preVisitDirectory().Files.copy(dir,newDirectory)");
            Log.getLog(Log.OUT).log("using : " + dir + " : " + newDirectory);
            Files.copy(dir,newDirectory);
        }
        catch (FileAlreadyExistsException faie){
            //log it and move
            Log.getLog(Log.INFO).log("BaseFileVisitor:preVisitDirectory().Files.catch (FileAlreadyExistsException faie)");
            Log.getLog(Log.INFO).log(faie);
            //return SKIP_SUBTREE; // skip processing
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
       Log.getLog(Log.OUT).log("BaseFileVisitor:visitFile()");
       Path newFile = target.resolve(source.relativize(file));
        Log.getLog(Log.OUT).log("using file : " + file + " & new file : " + newFile);
        try{
           Log.getLog(Log.OUT).log("BaseFileVisitor:visitFile().Files.copy(file,newFile)");
            Files.copy(file,newFile);
        }
        catch (FileAlreadyExistsException faie){
            //log it and move
            Log.getLog(Log.INFO).log("BaseFileVisitor:visitFile().Files.catch (FileAlreadyExistsException faie)");
            Log.getLog(Log.INFO).log(faie);
        }

        return FileVisitResult.CONTINUE;

    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

        Log.getLog(Log.OUT).log("BaseFileVisitor:postVisitDirectory()");
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        if (exc instanceof FileSystemLoopException) {
            Log.getLog(Log.INFO).log("BaseFileVisitor:visitFileFailed.(exc instanceof FileSystemLoopException)");
        } else {
            Log.getLog(Log.WARN).log("BaseFileVisitor:visitFileFailed.else");
        }
        return CONTINUE;
    }
}