package org.fundacionjala.core.util.exceptions;

/**
 * new class for execptions of file.
 */
public class FileException extends RuntimeException {
    /**
     * Message when there isn't the config.feature file.
     */
    public FileException() {
        super("There is a problem reading the specified file");
    }
}
