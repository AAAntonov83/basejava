package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index < 0) {
            System.out.printf("ERROR. Resume \"%s\" is not in storage.%n", resume.getUuid());
            return;
        }
        storage[index] = resume;
    }

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
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.printf("ERROR. Resume \"%s\" is not in storage.%n", uuid);
            return null;
        }
        return storage[index];
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
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract int findIndex(String uuid);
}
