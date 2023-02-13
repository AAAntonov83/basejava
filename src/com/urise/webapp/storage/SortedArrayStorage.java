package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            System.out.printf("ERROR. Resume \"%s\" is already in storage.%n", r.getUuid());
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("ERROR. Storage is full.");
        } else {
            index = Math.abs(index + 1);
            System.arraycopy(storage, index, storage, index + 1, size++ - index);
            storage[index] = r;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.printf("Resume \"%s\" is not in storage.%n", uuid);
            return;
        }
        System.arraycopy(storage, index + 1, storage, index, --size - index);
        storage[size] = null;
    }

    @Override
    protected int findIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }
}
