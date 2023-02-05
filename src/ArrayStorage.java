import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private int number;
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, 0, number, null);
    }

    void save(Resume r) {
        storage[number++] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < number; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < number; i++) {
            if (storage[i].uuid.equals(uuid)) {
                if (i < storage.length - 1) {
                    System.arraycopy(storage, i + 1, storage, i, --number - i);
                }
                storage[number] = null;
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
