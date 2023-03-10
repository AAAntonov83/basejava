package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storage[(int) searchKey] = r;
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveResume(r, Math.abs((int) searchKey + 1));
            size++;
        }
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    protected void doDelete(Object searchKey) {
        deleteResume((int) searchKey);
        storage[size] = null;
    }

    @Override
    protected int doSize() {
        return size;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return findIndex(uuid);
    }

    @Override
    protected boolean isExists(Object searchKey) {
        return (int) searchKey >= 0;
    }

    protected abstract int findIndex(String uuid);

    protected abstract void saveResume(Resume r, int index);

    protected abstract void deleteResume(int index);
}
