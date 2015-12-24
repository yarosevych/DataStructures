package list;

public interface List<E> extends Iterable<E> {

    void add(E el);
    void add(int index, E el);
    void add(E... args);
    void set(int index, E el);
    E get(int index);
    void remove(int index);
    int size();
    void clear();
    int indexOf(E element);
    boolean contains(E element);
    int lastIndexOf(E element);
    boolean isEmpty();
}
