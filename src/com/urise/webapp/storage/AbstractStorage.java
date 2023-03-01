package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void clear() {
        clearStorage();
    }

    @Override
    public void update(Resume r) {
        updateResume(r);
    }

    @Override
    public void save(Resume r) {
        saveResume(r);
    }

    @Override
    public Resume get(String uuid) {
        return getResume(uuid);
    }

    @Override
    public void delete(String uuid) {
        deleteResume(uuid);
    }

    @Override
    public Resume[] getAll() {
        return getAllResumes();
    }

    @Override
    public int size() {
        return sizeStorage();
    }

    protected abstract void clearStorage();

    protected abstract void updateResume(Resume r);

    protected abstract void saveResume(Resume r);

    protected abstract Resume getResume(String uuid);

    protected abstract void deleteResume(String uuid);

    protected abstract Resume[] getAllResumes();

    protected abstract int sizeStorage();
}
