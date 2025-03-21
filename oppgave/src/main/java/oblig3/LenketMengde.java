package oblig3;

public class LenketMengde<T> implements MengdeADT<T> {
    private Node first;
    private int size;
    
    public LenketMengde(){
        this.first = null;
        this.size = 0;
    }

    private class Node{
        private T data;
        private Node next;
    }

    public boolean erTom(){
        return size==0;
    }

    public void leggTil(T element) {
        if (!this.inneholder(element)) {
            Node nyNode = new Node();
            nyNode.data = element;
            nyNode.next = null;
    
            if (first == null) {
                first = nyNode; // If the list is empty, set the new node as the first node
            } else {
                Node current = first;
                while (current.next != null) {
                    current = current.next; // Traverse to the end of the list
                }
                current.next = nyNode; // Add the new node at the end
            }
            size++;
        }
    }

    public boolean inneholder(T element) {
        Node current = first;
		while (current != null) {
			if (current.data.equals(element)) {
				return true;
			}
			current = current.next;
		}
		return false;
    }

    public T fjern(T element){
        if (first == null) {
            return null; 
        }
        Node current = first;
        if (current.data.equals(element)) {
            first = first.next;
            size--;
            return element;
        }
        while (current.next != null) {
            if (current.next.data.equals(element)) {
                current.next = current.next.next;
                size--;
                return element;
            }
            current = current.next;
        }
        return null;
    }

    public int antallElementer() {
        return size;
    }

    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
        T[] array = annenMengde.tilTabell();
        for (int i = 0; i < array.length; i++) {
            this.leggTil(array[i]);
        }
    }
    @SuppressWarnings("unchecked")
    public T[] tilTabell() {
        T[] result = (T[]) new Object[size];
        Node current = first;
        for (int i = 0; i < size; i++) {
            result[i] = current.data;
            current = current.next;
        }
        return result;
    }

    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        MengdeADT<T> result = new LenketMengde<>();
        Node current = first;
        while (current != null) {
            if (!annenMengde.inneholder(current.data)) {
                result.leggTil(current.data);
            }
            current = current.next;
        }
        return result;
    }

    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        MengdeADT<T> result = new LenketMengde<>();
        Node current = first; 
        while (current != null) {
            if (annenMengde.inneholder(current.data)) {
                result.leggTil(current.data);
            }
            current = current.next;
        }
        return result;
    }

    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        MengdeADT<T> result = new LenketMengde<>();
        result.leggTilAlleFra(this);
        result.leggTilAlleFra(annenMengde);
        return result;
    }

    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        Node current = first;
        while (current != null) {
            if (!annenMengde.inneholder(current.data)) {
                return false; 
            }
            current = current.next;
        }
        return true; 
    }
    

    public boolean erLik(MengdeADT<T> annenMengde) {
        if (this.antallElementer() != annenMengde.antallElementer()) {
            return false;
        }
    
        Node current = first;
        while (current != null) {
            if (!annenMengde.inneholder(current.data)) {
                return false;
            }
            current = current.next;
        }
    
        return true;
    }

    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        T[] array = annenMengde.tilTabell();
        for (int i = 0; i < array.length; i++) {
            if(this.inneholder(array[i])) {
                return false;
            }
        }
        return true;
    }
}

