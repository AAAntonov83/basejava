package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private int size;
    private final Resume[] storage = new Resume[10000];

    public int getSize() {
        return size;
    }

    public Resume get(String uuid) {
        int index = findPosition(uuid);
        if (index < 0) {
            System.out.printf("ОШИБКА. Резюме c id \"%s\" отсутствует в хранилище.%n", uuid);
            return null;
        }
        return storage[index];
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void save(Resume r) {
        if (size >= storage.length) {
            System.out.println("ОШИБКА. База резюме переполнена.");
        } else if (findPosition(r.getUuid()) >= 0) {
            System.out.printf("ОШИБКА. Резюме c id \"%s\" уже есть в хранилище.%n", r.getUuid());
        } else {
            storage[size++] = r;
        }
    }

    public void delete(String uuid) {
        int index = findPosition(uuid);
        if (index < 0) {
            System.out.printf("ОШИБКА. Резюме c id \"%s\" отсутствует в хранилище.%n", uuid);
            return;
        }
        storage[index] = storage[size - 1];
        storage[--size] = null;
    }

    public void update(Resume resume) {
        int index = findPosition(resume.getUuid());
        if (index < 0) {
            System.out.printf("ОШИБКА. Резюме c id \"%s\" уже отсутствует в хранилище.%n", resume.getUuid());
            return;
        }
        storage[index] = resume;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    private int findPosition(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
