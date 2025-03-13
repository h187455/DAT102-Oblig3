public class TabellMengde<T> implements MengdeADT<T> {
    private int size;
    private T[] elementsCopy;
    private T[] elements;

    @SuppressWarnings("unchecked")
    public TabellMengde() {
        this.elements = (T[]) new Object[10];
        this.size = 0;
    }

    public boolean erTom(){
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    public void leggTil(T element) {
        boolean check = true;
        for (int i = 0; i < size; i++) {
            if (elements[i] == element) {
                check = false;
            }
        }
        
        if (size == elements.length && check) {
            
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
    @SuppressWarnings("unchecked")
    public T[] tilTabell(){
        
        T[] arr = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = elements[i];
        }
        return arr;
    }

    public void leggTilAlleFra(MengdeADT<T> annenMengde){
        T[] newElements = annenMengde.tilTabell();
        for (int i = 0; i < newElements.length; i++) {
            this.leggTil(newElements[i]);
        }
    }

    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        T[] newElements = annenMengde.tilTabell();
        for (int i = 0; i < newElements.length; i++) {
            this.fjern(newElements[i]);
        }
        
    }
}
