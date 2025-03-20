package oblig3;

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
        boolean ikkjeDer = !this.inneholder(element);
        if (size == elements.length && ikkjeDer) {
            
            this.elementsCopy = (T[]) new Object[size*2];
            for (int i = 0; i < size; i++) {
                elementsCopy[i] = elements[i]; 
            }
            elements = elementsCopy;
        }

        if (ikkjeDer) {
            elements[size++] = element;
        }
    }

    public T fjern(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
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
        MengdeADT<T> result = new TabellMengde<>();

        for (T element : elements) {
            if (!annenMengde.inneholder(element)) {
                result.leggTil(element);
            }
        }
        return result;
    }

    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        MengdeADT<T> result = new TabellMengde<>();
        result.leggTilAlleFra(this);
        if(annenMengde != null) {
            result.leggTilAlleFra(annenMengde);
        }
        return result;
    }

    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        T[] snittMengde = annenMengde.tilTabell();
        MengdeADT<T> result = new TabellMengde<>();
        for (int i = 0; i < size; i++) {
            boolean inBoth = false;
            for (T element : snittMengde) {
                if(elements[i].equals(element) && elements[i] != null) {
                    inBoth = true;
                    break;
                }
            }
            if (inBoth) {
                result.leggTil(elements[i]);
            }
        }
        return result;
    }

    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        T[] snittMengde = annenMengde.tilTabell();
        
        for (int i = 0; i < size; i++) {
            for (T element : snittMengde) {
                if(elements[i].equals(element) && elements[i] != null) {
                    return false;
                }
            } 
        }
        return true;
    }

    public boolean erLik(MengdeADT<T> annenMengde) {
        if (this.antallElementer() != annenMengde.antallElementer()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if(!annenMengde.inneholder(elements[i])){
                return false;
            }
        }
        return true;
    }

    public boolean inneholder(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        T[] delmengde = annenMengde.tilTabell();
        for (int i = 0; i < annenMengde.antallElementer(); i++) {
            if(!this.inneholder(delmengde[i])) {
                return false;
            }
        }
        return true;
    }
}

