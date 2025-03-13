public class TabellMengde<T> implements MengdeADT<T> {
    private int size = 0;
    private T[] elementsCopy;
    private T[] elements;

    @SuppressWarnings("unchecked")
    public TabellMengde() {
        this.elements = (T[]) new Object[10];
    }

    public boolean erTom(){
        return size == 0;
    }

    public void leggTil(T element) {
        boolean check = true;
        for (int i = 0; i < size; i++) {
            if (elements[i] == element) {
                check = false;
            }
        }
        
        if (size == elements.length && check) {
            @SuppressWarnings("unchecked")
            this.elementsCopy = (T[]) new Object[size*2];
            for (int i = 0; i < size; i++) {
                elementsCopy[i] = elements[i]; 
            }
            elements = elementsCopy;
        }

        if (check) {
            elements[size++] = element;
        }
    }

    public T fjern(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element) {
                size--;
                elements[i] = elements[size];
                elements[size] = null;
                return element;
            }
        }
        return null;
    }

    public int antallElementer() {
        return size;
    }

    public T[] tilTabell(){
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = elements[i];
        }
        return arr;
    }
}
