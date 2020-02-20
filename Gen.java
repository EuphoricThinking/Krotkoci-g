package EliotMoss;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.shuffle;

public class Gen {
    Jęz narzecze;

    public Gen(Jęz j){
        this.narzecze = j;
    }

    private char random(String prefiks){
        if(this.narzecze.dajSłownik().containsKey(prefiks)){
        Map<Character, Integer> orig_znaki = this.narzecze.dajSłownik().get(prefiks);
        List<Character> znaczki = new ArrayList();
        for (Map.Entry<Character, Integer> e: orig_znaki.entrySet()){
            for (int i=0; i<e.getValue(); i++){
                znaczki.add(e.getKey());  //Dodaje znaki do listy
            }
        }
        shuffle(znaczki);
        return znaczki.get(0);}  //Pierwszy element na losowo przemieszanej liście zawsze losowy
        else{
            return "$".toCharArray()[0];
        }
    }

    public String dajSłowo(){
        List<String> początki = this.narzecze.dajPoczątki();
        shuffle(początki);
        String słowo = początki.get(0);
        while(!słowo.substring(słowo.length()-1).equals("$")){ //Dopóki nieosiągnięty koniec
            String prefiks = słowo.substring(słowo.length()-this.narzecze.dajKrotność());  //Okno od końca słowa
            char c = random(prefiks);
            słowo = słowo+random(prefiks);
        }
        return słowo.substring(this.narzecze.dajKrotność()-1, słowo.length()-1);
    }
}
