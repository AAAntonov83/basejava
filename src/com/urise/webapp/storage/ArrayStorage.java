package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("ERROR. Storage is full.");
        } else if (findIndex(r.getUuid()) >= 0) {
            System.out.printf("ERROR. Resume \"%s\" is already in storage.%n", r.getUuid());
        } else {
            storage[size++] = r;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.printf("Resume \"%s\" is not in storage.%n", uuid);
            return;
        }
        storage[index] = storage[size - 1];
        storage[--size] = null;
    }

    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
