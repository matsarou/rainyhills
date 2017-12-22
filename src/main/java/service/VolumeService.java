package service;

/**
 * Service class, used by the API methods to serve the data (access the repository if needed)
 */
public interface VolumeService {

    /**
     * Calculates the volume of the remaining water according to the given array of integers.
     * @param numbers
     *          an array of integer numbers
     * @return an integer
     */
    int getVolume(Integer[] numbers);
}
