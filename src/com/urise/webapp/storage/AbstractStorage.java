package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void doUpdate(Object searchKey, Resume r);

    protected abstract void doSave(Object searchKey, Resume r);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract int doSize();

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExists(Object searchKey);

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
    public int size() {
        return doSize();
    }

    private Object getExistingSearchKey(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (!isExists(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        }
        return searchKey;
    }

    private Object getNotExistingSearchKey(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (isExists(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        }
        return searchKey;
    }
}
