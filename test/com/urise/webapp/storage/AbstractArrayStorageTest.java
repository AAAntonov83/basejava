package com.urise.webapp.storage;

import com.urise.webapp.exception.*;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final Resume RESUME_1 = new Resume("uuid1");
    private static final Resume RESUME_2 = new Resume("uuid2");
    private static final Resume RESUME_3 = new Resume("uuid3");
    private static final Resume RESUME_4 = new Resume("uuid4");
    private static final String UUID_NOT_EXIST = "dummy";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Assert.assertArrayEquals(new Resume[0], storage.getAll());
    }

    @Test
    public void update() {
        Resume r = new Resume(RESUME_2.getUuid());
        storage.update(r);
        Assert.assertSame(r, storage.get(r.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws NotExistStorageException {
        storage.update(new Resume(UUID_NOT_EXIST));
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = StorageException.class)
    public void saveStorageOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < 10000; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Storage overflow while filling within length");
        }
        storage.save(new Resume());
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() throws ExistStorageException {
        storage.save(RESUME_1);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws NotExistStorageException {
        storage.get(UUID_NOT_EXIST);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        try {
            storage.delete(RESUME_2.getUuid());
        } catch (NotExistStorageException e) {
            Assert.fail("Error when deleting element");
        }
        assertSize(2);
        assertGet(RESUME_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws NotExistStorageException {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void getAll() {
        Resume[] resumes = {RESUME_1, RESUME_2, RESUME_3};
        Assert.assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void size() {
        assertSize(3);
    }

    private void assertSize(int expectedSize) {
        Assert.assertEquals(expectedSize, storage.size());
    }

    private void assertGet(Resume r) {
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }

}