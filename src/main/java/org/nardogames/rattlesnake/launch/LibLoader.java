package org.nardogames.rattlesnake.launch;

import com.google.common.base.StandardSystemProperty;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Utility class to copy native files to temp dir, and then addEntity that dir to java.library.path
 */
public class LibLoader {
    private static final Logger log = LoggerFactory.getLogger(LibLoader.class);

    private final Path tempDir;

    private static LibLoader instance = null;

    public static void loadAll(String... libraryFiles) {
        get().load(libraryFiles);
    }

    private static LibLoader get() {
        if (instance == null)
            instance = new LibLoader();
        return instance;
    }

    private LibLoader() {
        try {
            tempDir = Paths.get(StandardSystemProperty.JAVA_IO_TMPDIR.value()).resolve("org.nardogames.rattlesnake");
            Files.createDirectories(tempDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        addJavaLibraryPath(tempDir);
    }

    private static void addJavaLibraryPath(Path dir) {
        try {
            final Field usrPathsField = ClassLoader.class.getDeclaredField("usr_paths");
            usrPathsField.setAccessible(true);

            //get array of paths
            final String[] paths = (String[]) usrPathsField.get(null);

            //addEntity the new path
            final String[] newPaths = Arrays.copyOf(paths, paths.length + 1);
            newPaths[newPaths.length - 1] = dir.toString();
            usrPathsField.set(null, newPaths);
        } catch (Exception e) {
            throw new LibLoaderException("Could not alter java.library.path", e);
        }
    }

    public void load(String... librayFilenames) {
        for (String librayFilename : librayFilenames) {
            load(librayFilename);
        }
    }

    public void load(String libraryFilename) {
        URL resource = getClass().getClassLoader().getResource(libraryFilename);
        if(resource == null)
            throw new LibLoaderException("Could not find resource: " + libraryFilename);
        File outFile = tempDir.resolve(libraryFilename).toFile();
        outFile.deleteOnExit();
        log.debug("Loading " + outFile);
        try (FileOutputStream outStream = new FileOutputStream(outFile)) {
            Resources.copy(resource, outStream);
        } catch (IOException e) {
            throw new LibLoaderException("Problems loading library: " + libraryFilename, e);
        }
    }
}
