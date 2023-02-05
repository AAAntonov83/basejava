import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, 0, size(), null);
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        if (size() == 0) {
            return null;
        }

        for (Resume resume : storage) {
            if (resume == null) {
                return null;
            }
            if (resume.uuid.equals(uuid)) {
                return resume;
            }
        }
        return null;
    }

    void delete(String uuid) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (storage[i] == null) {
                break;
            }

            if (storage[i].uuid.equals(uuid)) {
                if (i < storage.length - 1) {
                    System.arraycopy(storage, i + 1, storage, i, size - 1 - i);
                }
                storage[size - 1] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int size = 0;
        for (Resume resume : storage) {
            if (resume == null) {
                return size;
            }
            size++;
        }
        return size;
    }
}
