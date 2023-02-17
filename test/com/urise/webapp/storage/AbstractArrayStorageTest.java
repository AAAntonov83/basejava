package com.urise.webapp.storage;

import com.urise.webapp.exception.*;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume[] resumes = Arrays.copyOf(storage.getAll(), storage.size());
        storage.update(new Resume(UUID_2));
        Assert.assertArrayEquals(resumes, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws NotExistStorageException {
        storage.update(new Resume());
    }

    @Test
    public void save() {
        Resume[] resumes = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3), new Resume("uuid4")};
        storage.save(resumes[resumes.length - 1]);
        Assert.assertArrayEquals(resumes, storage.getAll());
    }

    @Test(expected = StorageException.class)
    public void saveStorageOverflow() {
        try {
            for (int i = storage.size(); i < 10000; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Storage overflow while filling within length");
        }
        storage.save(new Resume());
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() throws ExistStorageException {
        storage.save(new Resume(UUID_1));
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws NotExistStorageException {
        storage.get("dummy");
    }

    @Test
    public void delete() {
        Resume[] resumes = {new Resume(UUID_1), new Resume(UUID_3)};
        storage.delete(UUID_2);
        Assert.assertArrayEquals(resumes, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws NotExistStorageException {
        storage.delete("dummy");
    }

    @Test
    public void getAll() {
        Resume[] resumes = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        Assert.assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void sizeAfterClear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

}