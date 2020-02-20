package EliotMoss;
//Słownik imienia Eliot Mossa
import java.io.File;
import java.util.*;

public class Jęz {
    Map<String, Map<Character, Integer>> kroteczki;
    String nazwa;
    List<String> początki;
    int krotność;

    public Jęz(int krotność){
        this.kroteczki = new HashMap();
        this.początki = new ArrayList();
        this.krotność = krotność;
    }

    public void uczSię(String nazwa){   //długość krotki, nazwa pliku
        try {
            Scanner obj = new Scanner(new File(nazwa));
            obj.useDelimiter("\\P{L}+");
            String pre = ""; //Prefiks zaznaczający początek
            for (int j = this.krotność-1; j>0; j--){
                pre = pre+"$";
            }
            String dod = pre+"$";
            while(obj.hasNext()){
                String sł = obj.next().toLowerCase();
                String słówko = dod+sł+"$";  //Nie rozumiem powstania wielkiej litery
                String pocz = pre+sł.substring(0,1); //Pierwsza litera
                if (!this.początki.contains(pocz)){this.początki.add(pocz);} //Dodaje początek słowa, jeśłi nie występował
                for (int i = 0; i<słówko.length()-this.krotność; i++){
                    String s = słówko.substring(i, i+this.krotność);
                    if (this.kroteczki.containsKey(s)){
                        if (this.kroteczki.get(s).containsKey(słówko.charAt(i+this.krotność))){ //Sprawdza, czy zawiera znak za zadaną krotką
                            this.kroteczki.get(s).put(słówko.charAt(i+this.krotność), this.kroteczki.get(s).get(słówko.charAt(i+this.krotność))+1); //Zwiększa wartość
                        }
                        else{ //Jeśli nie zidentyfikowano wcześniej danego znaku
                            this.kroteczki.get(s).put(słówko.charAt(i+this.krotność), 1);

                        }}
                    else{  //Jeśli nie zawiera danej krotki
                        this.kroteczki.put(s, new HashMap<Character, Integer>());
                        this.kroteczki.get(s).put(słówko.charAt(i+this.krotność), 1);
                        }
                    }
                }
            nazwij(dod);
            }

        catch (Exception e){
            System.out.println("Brak pliku "+nazwa+" :c");
        }
    }

    public void nazwij(String pocz){  //Tworzy nazwę ze znaków znajdujących się za najbardziej słowotwórczym prefiksem
     //   String naz = "";
        String k = "";
        int liczba = 0;
        for (Map.Entry<String, Map<Character, Integer>> e: this.kroteczki.entrySet()){
            //Jeżeli rozmiar słownika większy+prefiks nie jest równy początkowi
            if (e.getValue().size()>liczba&&!e.getKey().substring(0,1).equals("$")) {
                liczba = e.getValue().size();
                k = e.getKey();
            }
        }
      /*  for (Character c: this.kroteczki.get(k).keySet()){
            naz = naz+c;  //Tworzy nazwę ze znaków przyporządkowanych danemu prefiskowi
        }*/
        this.nazwa = k.substring(0,1).toUpperCase()+k.substring(1);
    }

    public Map<String, Map<Character, Integer>> dajSłownik(){return this.kroteczki;}
    public String dajNazwę(){return this.nazwa;}
    public List<String> dajPoczątki(){return this.początki;}
    public int dajKrotność(){return this.krotność;}
}
