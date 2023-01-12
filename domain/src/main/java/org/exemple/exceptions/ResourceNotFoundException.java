package org.exemple.exceptions;
/**
 * 
 * ResourceNotFoundException to throw when there is no resource found of the data in request
 * @author Abdelmouheimen TRABELSSI
 *
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
