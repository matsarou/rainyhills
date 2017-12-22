package service;

import javax.inject.Named;

@Named("volumeService")
public class VolumeServiceImpl implements VolumeService {

    @Override
    public int getVolume(Integer[] numbers) {
        return calculateVolume(numbers);
    }

    private int calculateVolume(Integer[] numbers) {
        int volume = 0;
        int size = numbers.length;
        int left[] = new int[size];
        left[0] = numbers[0];
        for (int i = 1; i < size; i++) {
            left[i] = Math.max(left[i - 1], numbers[i]);
        }
        int right[] = new int[size];
        right[size-1] = numbers[size-1];
        for (int i = size-2; i >= 0; i--)
            right[i] = Math.max(right[i+1], numbers[i]);

        for (int i = 0; i < size; i++)
            volume += Math.min(left[i],right[i]) - numbers[i];

        return volume;
    }
}
