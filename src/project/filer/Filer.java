package project.filer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * An abstract Class that manages the creation and deletion of directories and
 * files, and that also tests their existence through static methods.
 * 
 * @author h.miranda-queiros
 */
public abstract class Filer {
    /**
     * The basic directory where the SatelliteFiles and their ControlCenterFiler
     * will communicate. The use of Path allows the following paths to be readable
     * by all the OS.
     */
    protected static final Path basic_path = Paths.get("src", "channels");

    /**
     * Creates the directory of a new SatelliteFile.
     * 
     * @param name_sat The name of the SatteliteFile that will have this directory.
     * 
     * @throws IOException
     */
    public static void createDirSat(String name_sat) throws IOException {
        Path dir_path = basic_path.resolve(Paths.get(name_sat));
        Files.createDirectory(dir_path);
    }

    /**
     * Creates a file in the directory of a SatelliteFile.
     * 
     * @param name_sat  The name of the SatteliteFile that will have this file.
     * @param name_file The name of the file that is created.
     * @throws IOException
     */
    public static void createFile(String name_sat, String name_file) throws IOException {
        Path file_path = basic_path.resolve(Paths.get(name_sat, name_file));
        Files.createFile(file_path);
    }

    /**
     * Deletes a file in the directory of a SatelliteFile.
     * 
     * @param name_sat  The name of the SatteliteFile that will have a file deleted.
     * @param name_file The name of the file that is deleted.
     * @throws IOException
     */
    public static void deleteFile(String name_sat, String name_file) throws IOException {
        Path file_path = basic_path.resolve(Paths.get(name_sat, name_file));
        Files.delete(file_path);
    }

    /**
     * Tests whether a file exists or not in the directory of a SatelliteFile.
     * 
     * @param name_sat  The name of the SatteliteFile.
     * @param name_file The name of the file that is tested.
     * @return
     */
    public static boolean existFile(String name_sat, String name_file) {
        Path file_path = basic_path.resolve(Paths.get(name_sat, name_file));
        return Files.exists(file_path);
    }

    /**
     * Tests whether a directory exists to know if a SatelliteFile exists.
     * 
     * @param name_sat The name of the SatteliteFile that is tested.
     * @return
     */
    public static boolean existDir(String name_sat) {
        Path file_path = basic_path.resolve(Paths.get(name_sat));
        return Files.exists(file_path);
    }

}
