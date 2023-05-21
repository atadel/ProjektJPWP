import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Roslina {
    // komponenty
    private JPanel kwiatek = new JPanel();
    private JComboBox<String> JComboBoxTypyRoslin = new JComboBox<>(Parapet.typyRoslin);
    private JButton ususzUratuj = new JButton("ususz/uratuj");

    // obrazki
    private JLabel imageLabel = new JLabel(new ImageIcon());
    private JLabel flippedLabel = new JLabel(new ImageIcon());
    private ImageIcon scaledIcon;
    private Image scaledImage;

    // zmienne
    private boolean czyUsuszony = false;
    public String selectedTypRoslin;
    private int wysokoscParapetu = Parapet.wysokosc - ususzUratuj.getHeight() - JComboBoxTypyRoslin.getHeight()-20;
    public int szerokoscPanelu = 200;
    public JPanel pusty = new JPanel();

    public Roslina() {
        initComponents();
    }

    private void initComponents() {

        // inicjalizuję ustawienia panelu parapetu
        kwiatek.setOpaque(false);
        kwiatek.setLayout(new BoxLayout(kwiatek, BoxLayout.Y_AXIS));
        kwiatek.setPreferredSize(new Dimension(szerokoscPanelu, Parapet.panelRosliny.getHeight()));

        kwiatek.add(JComboBoxTypyRoslin);

        JPanel ususzUratujPomocniczy = new JPanel();
        ususzUratujPomocniczy.setOpaque(false);
        ususzUratujPomocniczy.add(ususzUratuj);
        kwiatek.add(ususzUratujPomocniczy);


        pusty.setPreferredSize(new Dimension(szerokoscPanelu, wysokoscParapetu));
        pusty.setVisible(true);
        pusty.setOpaque(false);
        pusty.setLocation(0, JComboBoxTypyRoslin.getHeight()+ususzUratuj.getHeight());;
        pusty.setBounds(new Rectangle(200, 400));

        kwiatek.add(pusty);
        kwiatek.add(imageLabel);



        kwiatek.revalidate();
        kwiatek.repaint();



        // LISTA ROZWIJANA - TYPY ROSLIN -------------------------------------------------------------------------------------
        // tworzę ActionListener do listy rozwijanej
        JComboBoxTypyRoslin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // pobieram wybraną wartość - typ rosliny
                selectedTypRoslin = (String) JComboBoxTypyRoslin.getSelectedItem();

                // Wczytanie obrazka
                ImageIcon icon = new ImageIcon("resources/" + selectedTypRoslin + ".png");
                Image image = icon.getImage();

                // Obliczenie nowych wymiarów i przeskalowanie obrazka
                int width = szerokoscPanelu;
                int height = (int) ((double) image.getHeight(null) * width / image.getWidth(null));
                scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                scaledIcon = new ImageIcon(scaledImage);
                if (height > wysokoscParapetu) {
                    height = wysokoscParapetu;
                }

                // Utworzenie etykiety z przeskalowanym obrazkiem i dodanie jej do panelu

                imageLabel.setIcon(scaledIcon);
                pusty.setPreferredSize(new Dimension(szerokoscPanelu, wysokoscParapetu- 100 -((int)scaledIcon.getIconHeight())));
                kwiatek.add(imageLabel);
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                imageLabel.setAlignmentY(wysokoscParapetu-scaledIcon.getIconHeight());

                kwiatek.revalidate();
                kwiatek.repaint();
            }
        });

        // dodaje guzik ususz/uratuj -----------------------------------------------------------------------------------------
        ususzUratuj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (czyUsuszony) {
                    // uratuj
                    imageLabel.setIcon(scaledIcon);
                    czyUsuszony = false;
                    Parapet.licznikUsuszonych--;
                }
                else {

                    // Zastosowanie efektu sepia na obrazek
                    Image sepiaImage = SepiaEffect.applySepiaEffect(scaledImage);

                    // Utworzenie nowego ImageIcon z przekształconym obrazkiem
                    ImageIcon sepiaIcon = new ImageIcon(sepiaImage);

                    // Aktualizacja etykiety z obrazkiem
                    imageLabel.setIcon(sepiaIcon);

                    Parapet.licznikUsuszonych++;
                    czyUsuszony = true;
                }}});

        // DODAWANIE DO PANELU

    }

    public void wyczysc() {
        Image odwrocony = FlipEffect.flipImageHorizontal(scaledImage);

        //ImageIcon flippedIcon = new ImageIcon(odwrocony);
        ImageIcon flippedIcon = new ImageIcon(odwrocony);

        flippedLabel.setIcon(flippedIcon);
        kwiatek.add(flippedLabel);


    }

    public JPanel getKwiatek() {
        return kwiatek;
    }



}
