# Wirtualny Parapet 
## Zadanie 1
```
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Zadanie1{
    public static void main(String[] args) {

        // Poniższy kod generuje prosty interfejs użytkownika składający się z 2 analogicznych paneli.
        // Każdy z paneli zawiera etykietę, 2 guziki oraz pole tekstowe.

        // TODO:
        // zmień parametry wyświetlanych komponentów (czcionki, tła, kolory...) używając UIManagera
        // użyj co najmniej jednego własnego koloru (tzn. spoza domyślnych dostępnych w bibliotece Color - przykład poniżej)
        // użyj metody UIManager.put(String key, Object value), gdzie key to klucz identyfikujący wartość komponentu, a value to nowa wartość.

        // Twoim celem jest stworzenie unikalnego i atrakcyjnego wyglądu aplikacji, który będzie wyróżniał się spośród domyślnych motywów.

        // Modyfikacje zadania dla chętnych:
        // 1. Dodaj actionListener do guzika, który wyświetla nowe okno, sprawdz czy motyw działa analogicznie w drugim oknie.
        // 2. Zobacz, co się stanie, jeśli ręcznie zmienisz parametr komponentu, który ustawiłeś w UIManagerze
        // 		(np. jeśli w naszym przykładzie zmienisz kolor panel1 na zielony). Który parametr jest w GUI wyjściowym?
        // 3. Usuń panel2 i na jego miejsce dodaj do interfejsu inny komponent (np. tabelę, listę...)
        //		a następnie dostosuj parametry tego komponentu, aby pasował do pozostałych.
        // 4. Zobacz co się stanie, jeżeli umieścisz metodę UIManager.put(String key, Object value) drugi raz w programie po utworzeniu kilku komponentów danej klasy
        // 		(np. zmień tło guzika po stworzeniu drugiego panelu). Jaką wartość przyjmuje wtedy parametr? Czy jest to wartość wspólna dla wszystkich komponentów tej klasy?

        // Wskazówka: poniżej znajduje się przykład dodania własnego koloru (w formacie RGB)
        // oraz przykład zmiany czcionki etykiety i koloru tła panelu:

        Color backgroundColor = new Color(248, 24, 148);

        UIManager.put("Panel.background", backgroundColor);
        UIManager.put("Label.font", new Font("Arial", Font.BOLD, 24));
        UIManager.put("Label.foreground", Color.white);



        // MIEJSCE NA TWÓJ KOD - modyfikacja parametrów komponentów


        // Tworzenie ramki
        JFrame frame = new JFrame("Przykładowa ramka");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Tworzenie panelu 1
        JPanel panel1 = new JPanel();
        //panel1.setBackground(Color.cyan);
        panel1.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel label1 = new JLabel("Panel 1");
        JButton button1_1 = new JButton("guzik 1.1");
        JButton button1_2 = new JButton("guzik 1.2");
        JTextField text1 = new JTextField("pole tekstowe");

        // button1_1.setBackground(Color.yellow);

        panel1.add(label1);
        panel1.add(button1_1);
        panel1.add(button1_2);
        panel1.add(text1);

        // Tworzenie panelu 2
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel label2 = new JLabel("Panel 2");
        JButton button2_1 = new JButton("guzik 2.1");
        JButton button2_2 = new JButton("guzik 2.2");
        JTextField text2 = new JTextField("pole tekstowe");

        panel2.add(label2);
        panel2.add(button2_1);
        panel2.add(button2_2);
        panel2.add(text2);


        // Dodawanie paneli do ramki
        frame.getContentPane().setLayout(new GridLayout(1, 2, 20, 20));
        frame.getContentPane().add(panel1);
        frame.getContentPane().add(panel2);

        // Wyświetlanie ramki
        frame.setVisible(true);
    }
}
```
## Zadanie 2 
### To-do-list w JavaFX
Napisz w javafx program tworzący listę zadań (to-do-list), który składa się z:
  * listy zadań w postaci check-boxów z możliwością scrollowania (ScrollPane)
  * pola tekstowego do wpisania treści zadania 
  * przcisku "dodaj", który dodaje wpisane w pole tekstowe zadanie
  * przycisku "usuń", który usuwa zaznaczone zadania
Zadania powinny się wyświetlać po lewej stronie, a pole tekstowe wraz z przyciskami po prawej (SplitPane).

## Zadanie 3
### To-do-list w Swing
Napisz analogiczną to-do-list z wykorzystaniem pola tekstowego, check-boxów i dwóch przycisków w java swing. Zaobserwuj różnice.

## Zadanie 4
### Efekty w JavaFX
Napisz w javafx program składający się ze zdjęcia i 4 przycisków, w którym 
* pierwszy przycisk dodaje efekt sepii 
* drugi dodaje efekt bluru 
* trzeci tworzy blend sepii i bluru 
* czwarty prezentuje zdjęcie bez efektów. 
Zwróć uwagę na konieczność użycia Image oraz ImageView.

## Zadanie 5
### Dodatkowe
Przy użyciu podstawowych kształtów i kolorów  utwórz obrazek (np. biedronkę) w java swing lub javafx.
