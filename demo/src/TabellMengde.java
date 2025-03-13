public class TabellMengde<T> implements MengdeADT<T> {
    private int size = 0;
    private T[] elements;

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
        if (check) {
            elements[size++] = element;
        }
    }

}
