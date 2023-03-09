package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.LinkedList;

public class ListStorage extends AbstractStorage {

    final LinkedList<Resume> storage = new LinkedList<>();

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storage.set((int) searchKey, r);
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        storage.add(r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((int) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    protected Resume[] doGetAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int doSize() {
        return storage.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    protected boolean isExists(Object searchKey) {
        return (int) searchKey >= 0;
    }
}
