package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void clear() {
        doClear();
    }

    @Override
    public void update(Resume r) {
        doUpdate(getExistingSearchKey(r), r);
    }

    @Override
    public void save(Resume r) {
        doSave(getNotExistingSearchKey(r), r);
    }

    @Override
    public Resume get(String uuid) {
        return doGet(getExistingSearchKey(new Resume(uuid)));
    }

    @Override
    public void delete(String uuid) {
        doDelete(getExistingSearchKey(new Resume(uuid)));
    }

    @Override
    public Resume[] getAll() {
        return doGetAll();
    }

    @Override
    public int size() {
        return doSize();
    }

    private Object getExistingSearchKey(Resume r) {
        Object searchKey = getSearchKey(r);
        if (!isKeyExists(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        }
        return searchKey;
    }

    private Object getNotExistingSearchKey(Resume r) {
        Object searchKey = getSearchKey(r);
        if (isKeyExists(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        }
        return searchKey;
    }

    protected abstract void doClear();

    protected abstract void doUpdate(Object searchKey, Resume r);

    protected abstract void doSave(Object searchKey, Resume r);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract Resume[] doGetAll();

    protected abstract int doSize();

    protected abstract Object getSearchKey(Resume r);

    protected abstract boolean isKeyExists(Object searchKey);

}
