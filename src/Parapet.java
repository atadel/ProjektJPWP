import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

public class Parapet {
    // komponenty
    static public JFrame frame = new JFrame("Wirtualny Parapet <3");
    static public JPanel panelGuziki = new JPanel();
    static public JPanel panelRosliny = new JPanel();
    static public int wysokosc = 500;
    protected static List<Roslina> kwiatki = new ArrayList<>();

    // liczniki do statystyk
    static public int licznikUsuszonych = 0;

    // wektory do statystyk
    static public String[] typyRoslin = {
            "kaktus",
            "bratek",
            "chryzantemy",
            "lawenda",
            "irys",
            "hortensja"
    };

    @SuppressWarnings("serial")
    public static void main(String[] args){

        // panele i ramka
        frame.setLayout(new BorderLayout());
        panelRosliny.setLayout(new BoxLayout(panelRosliny, BoxLayout.X_AXIS));

        // ramka
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, wysokosc);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Ustawianie tła dla paneluRosliny
        panelRosliny.setBackground(Color.pink); // Ustawienie przezroczystego tła
        panelRosliny.setSize(new Dimension(2000, 200));

        // Utworzenie niestandardowego panelu, aby narysować tło
        ImageIcon backgroundImageIcon = new ImageIcon("resources/okno.png");
        Image backgroundImage = backgroundImageIcon.getImage();

        JPanel panelTlo = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Skalowanie i rysowanie obrazu tła
                int width = panelRosliny.getWidth();
                int height = panelRosliny.getHeight();
                g.drawImage(backgroundImage, 0, 0, width, height, this);
            }
        };

        JScrollPane scrollPane = new JScrollPane(panelRosliny);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(new Rectangle(2000,wysokosc));
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JLayeredPane layeredPane = new JLayeredPane();
        //panelRosliny.add(layeredPane);
        Dimension panelRoslinySize = panelRosliny.getSize();
        layeredPane.setSize(panelRoslinySize);
        layeredPane.add(panelTlo, 0);
        panelTlo.setBounds(0, 0, panelRoslinySize.width, panelRoslinySize.height);

        panelRosliny.revalidate();
        panelRosliny.repaint();

        //frame.add(panelRosliny, BorderLayout.CENTER);
        frame.add(panelGuziki, BorderLayout.WEST);
        panelGuziki.setLayout(new BoxLayout(panelGuziki, BoxLayout.Y_AXIS));

        UIManager.put("Button.background", Color.GREEN);
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 16));



        // tworzenie i dodawanie guzików
        JButton usunKwiatek = new JButton("usuń kwiatek");
        JButton ileKwiatkowZabilam = new JButton("wykres kolowy");
        JButton ileJakichKwiatkow = new JButton ("wykres slupkowy");
        JButton utworzKwiatek = new JButton("utwórz kwiatek");

        panelGuziki.add(utworzKwiatek);
        panelGuziki.add(ileJakichKwiatkow);
        panelGuziki.add(ileKwiatkowZabilam);
        panelGuziki.add(usunKwiatek);


        // actionListenery do guzików
        utworzKwiatek.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Roslina roslina = new Roslina();
                kwiatki.add(roslina);
                JPanel panelPomocniczy = new JPanel();
                //panelPomocniczy.setPreferredSize(new Dimension(roslina.getKwiatek().getHeight(), roslina.getKwiatek().getWidth()));
                panelPomocniczy.add(roslina.getKwiatek());
                panelPomocniczy.setOpaque(false);
                //panelPomocniczy.setBackground(Color.pink);
                panelRosliny.add(panelPomocniczy);
                //panelRosliny.revalidate();
                panelRosliny.repaint();
                roslina.getKwiatek().setAlignmentY(0.0f);
                frame.revalidate();
                frame.repaint();
            }
        });

        ileJakichKwiatkow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int [] tyleTakichKwiatkow = new int[typyRoslin.length];

                // licze ile jest kwiatkow jakiego typu
                for (Roslina kwiatek : kwiatki) {
                    String typ = kwiatek.selectedTypRoslin;

                    switch (typ) {
                        case "kaktus":
                            tyleTakichKwiatkow[0]++;
                            break;
                        case "bratek":
                            tyleTakichKwiatkow[1]++;
                            break;
                        case "chryzantemy":
                            tyleTakichKwiatkow[2]++;
                            break;
                        case "lawenda":
                            tyleTakichKwiatkow[3]++;
                            break;
                        case "irys":
                            tyleTakichKwiatkow[4]++;
                            break;
                        case "hortensja":
                            tyleTakichKwiatkow[5]++;
                            break;
                    }
                }

                WykresSlupkowy wykres = new WykresSlupkowy(tyleTakichKwiatkow,typyRoslin);

            }

        });

        ileKwiatkowZabilam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int licznikWszystkich = 0;
                // licze ile jest kwiatkow jakiego typu
                for (Roslina kwiatek : kwiatki) {
                    licznikWszystkich++;
                }

                new WykresKolowy(licznikUsuszonych, licznikWszystkich);

            }

        });

        usunKwiatek.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // usuwanie wszystkiego z panelu
                panelRosliny.removeAll();
                frame.revalidate();
                frame.repaint();


                kwiatki.remove(kwiatki.size()-1);

                // dodawanie tego co ma zostać w panelu
                for (int i=0; i< kwiatki.size(); i++) {
                    panelRosliny.add(kwiatki.get(i).getKwiatek());
                }
            }

        });

    }



    public List<Roslina> getKwiatki() {
        return kwiatki;
    }

    public void addKwiatki(Roslina kwiatek) {
        kwiatki.add(kwiatek);
    }

}

    
    
    
    
    
    
    
   