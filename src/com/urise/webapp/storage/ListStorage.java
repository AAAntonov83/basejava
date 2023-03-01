package com.urise.webapp.storage;

import com.urise.webapp.exception.*;
import com.urise.webapp.model.Resume;

import java.util.LinkedList;

public class ListStorage extends AbstractStorage {
    final LinkedList<Resume> storage = new LinkedList<>();

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected void updateResume(Resume r) {
        int index = storage.indexOf(r);
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        storage.set(index, r);
    }

    @Override
    protected void saveResume(Resume r) {
        int index = storage.indexOf(r);
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
        storage.add(r);
    }

    @Override
    protected Resume getResume(String uuid) {
        int index = storage.indexOf(new Resume(uuid));
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage.get(index);
    }

    @Override
    protected void deleteResume(String uuid) {
        if (!storage.remove(new Resume(uuid))) {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    protected Resume[] getAllResumes() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int sizeStorage() {
        return storage.size();
    }
}
