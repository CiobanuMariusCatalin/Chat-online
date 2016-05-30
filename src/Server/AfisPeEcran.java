
package Server;
/*Fiecare Thread va afisa mesajele pe un singur terminal acela fiind interfata servarului
deci trebuie sa afisam sincronizat fiecare mesaj, asa ca pentru orice mesaj ce doresc
sa transmit interfeti servarului il transmit utilizand clasa aceasta*/
import Server.Interfata.*;
public class AfisPeEcran {
    static Interfata interfata;
    AfisPeEcran(Interfata interfata){
        this.interfata=interfata;
    }
    public synchronized static void Mesajul(String s){
       interfata.add(s);
    }
}
