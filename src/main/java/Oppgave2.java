import java.util.ArrayList;

public class Oppgave2 {
    //skriver ut array til konsollen, generisk
    public static <E> void skrivArray(E[] array){
        for(E verdi : array) {
            System.out.print(verdi);
        }
        System.out.println();
    }
    public static <E> void reverserArray(E[]array){
        for(int i=array.length-1; i>=0; i--){
            E skrivut = array[i];
            System.out.print(skrivut);
        }
        System.out.println();
    }

    public static class Zandrasliste<E> extends ArrayList<E> {
        int listeLengde;
        public Zandrasliste(){
            listeLengde = 0;
        }

        public boolean add(E elem){
            //metode for å legge til element
            //returnerer false hvis listen er full

            if (listeLengde > 47){
                return false;
            }
            listeLengde ++;
            return super.add(elem);
        }
        public E get(int index){
            //finner verdi på index-plassen
            if(index < 0 || index >= listeLengde){
                throw new IndexOutOfBoundsException("Index ikke gyldig");
            }
            int teller = 0;
            for(E elem : this){
                if(teller == index){
                    return elem;
                }
                teller ++;
            }

            return null;
        }
        public int size(){
            //returnerer antall elementer i listen
            return listeLengde;
        }
    }
    public static void main(String[] args){
        Zandrasliste liste = new Zandrasliste();
        liste.add("Zandra");
        liste.add(5);
        System.out.println(liste.get(1));
        System.out.println(liste.size());
        Integer[] intArray = {1,2,3,4,5,6};
        Character[] charArray = {'Z','a','n'};

        ArrayList fruktListe = new ArrayList();
        fruktListe.add("eple");
        fruktListe.add("banan");
        fruktListe.add("drue");
        System.out.println(fruktListe);

        skrivArray(intArray);
        skrivArray(charArray);
        reverserArray(intArray);
        reverserArray(charArray);
    }
}
