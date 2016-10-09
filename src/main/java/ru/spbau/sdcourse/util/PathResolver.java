package ru.spbau.sdcourse.util;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Resolves file names relative to current directory
 * (the one that is stored in system property "user.dir").
 *
 * Note, that java doesn't actually modify OS environment, but rather it's internal
 * mapping of environment variables, which is a snapshot of systems environment for
 * the moment, when application was run. So whenever we want to process files in current
 * directory or relative to current directory, we should use this resolver.
 *
 * Created by andy on 10/9/16.
 */
public class PathResolver {
    /**
     * @param path - path that caller wants to turn into absolute
     * @return corresponding canonical path, if no IOException is thrown
     * during canonisation, else just absolute path
     */
    public static Path resolvePath(String path) {
        return resolvePath(Paths.get(path));
    }

    /**
     * @param path - path that caller wants to turn into absolute
     * @return corresponding canonical path, if no IOException is thrown
     * during canonisation, else just absolute path
     */
    public static Path resolvePath(Path path) {
        if (path.isAbsolute())
            return path;
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path result = currentPath.resolve(path).toAbsolutePath();
        try {
            return Paths.get(result.toFile().getCanonicalPath());
        } catch (IOException e) {
            return result;
        }
    }
}
