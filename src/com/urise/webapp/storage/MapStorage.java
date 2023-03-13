package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {

    final HashMap<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storage.put((String) searchKey, r);
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        storage.put((String) searchKey, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    protected int doSize() {
        return storage.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExists(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }
}
