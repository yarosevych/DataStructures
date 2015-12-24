package list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ArrayList<E> implements List<E>{

    private E [] data;
    private int size;

    public ArrayList() {
        this(10);
    }

    public ArrayList(int initialCapacity) {
        data = (E[]) new Object [initialCapacity];
    }

    @Override
    public void add(E el) {
        add(size, el);
    }

    @Override
    public void add(int index, E el) {
        if (index != size) {
            checkIndex(index);
        }
        if (index == size) {
            if (size == data.length) {
                E [] data = (E[]) new Object[(size * 3 / 2)];
                System.arraycopy(this.data, 0, data, 0, this.data.length);
                this.data = data;
            }
            data[size] = el;
        } else {
            if (size == data.length) {
                E [] data = (E[]) new Object[(size * 3 / 2)];
                System.arraycopy(this.data, 0, data, 0, index);
                data[index] = el;
                System.arraycopy(this.data, index, data, index + 1, size - index);
                this.data = data;
            } else {
                System.arraycopy(data, index, data, index + 1, size - index);
                data[index] = el;
            }
        }
        size++;
    }

    @Override
    public void add(E... args) {
        for (E element : args) {
            add(element);
        }
    }

    @Override
    public void set(int index, E el) {
        checkIndex(index);
        data[index] = el;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        if (index == size - 1) {
            data[index] = null;
        } else {
            System.arraycopy(data, index + 1, data, index, size - index);
            data[size - 1] = null;
        }
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int index = 0; index < size; index++) {
                if (data[index] == null) {
                    return index;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (element.equals(data[index])) {
                    return index;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        if (element == null) {
            for (int index = size -1; index >= 0; index--) {
                if (data[index] == null) {
                    return index;
                }
            }
        } else {
            for (int index = size -1; index >= 0; index--) {
                if (element.equals(data[index])) {
                    return index;
                }
            }
        }
        return -1;
    }


    @Override
    public void clear() {
        for (int index = 0; index < size; index++) {
            data[index] = null;
        }
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void checkIndex(int index) {
        if(index < 0 || index > size -1) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }


    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if( i != size -1) {
                sb.append(", ");
            } else {
                sb.append("]");
            }
        }
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<E> {

        private int position = -1;

        private ArrayListIterator() {

        }

        @Override
        public boolean hasNext() {
            return size != 0 && position + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            position++;
            return get(position);
        }

        @Override
        public void remove() {
            ArrayList.this.remove(position);
        }
    }
}
