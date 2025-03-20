package oblig3;

import java.util.Set;
import java.util.HashSet;
public class JavaSetToMengde<T> implements MengdeADT<T> {
    private Set<T> a;

    public JavaSetToMengde() {
        this.a=new HashSet<T>();
    }

    public boolean erTom(){
        return a.isEmpty();
    }

    public boolean inneholder(T element) {
        return a.contains(element);
    }

    public T fjern(T element) {
        if (a.contains(element)) {
            a.remove(element);
            return element;
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    public T[] tilTabell() {
        return a.toArray((T[]) new Object[a.size()]);
    }

    public int antallElementer() {
        return a.size();
    }

    public void leggTil(T element) {
        a.add(element);
    }

    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
        T[] newElements = annenMengde.tilTabell();
        for (T element : newElements) {
            this.leggTil(element);
        }
    }

    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        MengdeADT<T> unionen = new JavaSetToMengde<>();
        unionen.leggTilAlleFra(annenMengde);
        unionen.leggTilAlleFra(this);
        return unionen;
    }

    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        MengdeADT<T> snittet = new JavaSetToMengde<>();
        T[] elements = annenMengde.tilTabell();
        for (T element : elements) {
            if(this.inneholder(element)) {
                snittet.leggTil(element);
            }
        }
        return snittet;
    }

    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        if(this.antallElementer() > annenMengde.antallElementer()) {
            return false;
        }
        T[] delmengde = this.tilTabell();
        for (T element : delmengde) {
            if (!annenMengde.inneholder(element)) {
                return false;
            }
        }
        return true;
    }

    public boolean erLik(MengdeADT<T> annenMengde) {
        if (this.antallElementer() != annenMengde.antallElementer()) {
            return false;
        }
        T[] likeElement = annenMengde.tilTabell();
        for (T element : likeElement) {
            if (!this.inneholder(element)) {
                return false;
            }
        }
        return true;
    }

    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        T[] ulikeElement = annenMengde.tilTabell();
        for (T element : ulikeElement) {
            if (this.inneholder(element)) {
                return false;
            }
        }
        return true;
    }

    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        MengdeADT<T> result = new TabellMengde<>();
        T[] elements = this.tilTabell();
        for (T element : elements) {
            if (!annenMengde.inneholder(element)) {
                result.leggTil(element);
            }
        }
        return result;
    }
}
