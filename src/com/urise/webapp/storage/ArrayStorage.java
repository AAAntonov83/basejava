package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -size - 1;
    }

    @Override
    protected void saveResume(Resume r, int index) {
        storage[index] = r;
    }

    @Override
    protected void deleteResume(int index) {
        storage[index] = storage[size-- - 1];
    }
}
