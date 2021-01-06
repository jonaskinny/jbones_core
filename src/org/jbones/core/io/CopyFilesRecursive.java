package org.jbones.core.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.*;

import org.jbones.core.*;
import org.jbones.core.log.*;
import org.jbones.core.util.*;

public class CopyFilesRecursive {

    public static void main(String[] args) throws IOException {

        Path sourceLocation= Paths.get("C:/DATA/jbones/jbones_core/compare_dir/in");
        Path targetLocation =Paths.get("C:/DATA/jbones/jbones_core/compare_dir/out");

        BaseFileVisitor fileVisitor = new BaseFileVisitor(sourceLocation, targetLocation);
        //You can specify your own FileVisitOption
        Files.walkFileTree(sourceLocation, fileVisitor);
    }
}