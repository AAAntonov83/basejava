import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private int quantity;
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, 0, quantity, null);
        quantity = 0;
    }

    void save(Resume r) {
        storage[quantity++] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < quantity; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < quantity; i++) {
            if (storage[i].uuid.equals(uuid)) {
                if (i < storage.length - 1) {
                    System.arraycopy(storage, i + 1, storage, i, --quantity - i);
                }
                storage[quantity] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, quantity);
    }

    int size() {
        return quantity;
    }
}
