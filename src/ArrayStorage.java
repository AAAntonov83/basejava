import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private int count;
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, 0, count, null);
    }

    void save(Resume r) {
        storage[count++] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < count; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < count; i++) {
            if (storage[i].uuid.equals(uuid)) {
                if (i < storage.length - 1) {
                    System.arraycopy(storage, i + 1, storage, i, --count - i);
                }
                storage[count] = null;
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
