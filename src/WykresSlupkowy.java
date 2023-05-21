import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WykresSlupkowy extends JPanel {

    private int[] dane;
    private String[] etykiety;

    public WykresSlupkowy(int[] dane, String[] etykiety) {
        this.dane = dane;
        this.etykiety = etykiety;

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(this);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int szerokosc = getWidth();
        int wysokosc = getHeight();

        int iloscSlupkow = dane.length;
        int szerokoscSlupka = szerokosc / iloscSlupkow;

        int maxWartosc = znajdzMaksymalnaWartosc(dane);

        for (int i = 0; i < iloscSlupkow; i++) {
            int wysokoscSlupka = (int) ((double) dane[i] / maxWartosc * wysokosc/2);
            if (wysokoscSlupka == 0) {
                wysokoscSlupka = 2;
            }

            int x = i * szerokoscSlupka;
            int y = wysokosc - wysokoscSlupka-20;

            g.setColor(Color.green);
            g.fillRect(x, y, szerokoscSlupka, wysokoscSlupka);

            g.setColor(Color.BLACK);
            g.drawString(etykiety[i] + ", " + dane[i], x, wysokosc-5);
        }
    }

    private int znajdzMaksymalnaWartosc(int[] tablica) {
        int maksimum = tablica[0];
        for (int i = 1; i < tablica.length; i++) {
            if (tablica[i] > maksimum) {
                maksimum = tablica[i];
            }
        }
        return maksimum;
    }

    public static void main(String[] args) {
        int[] dane = {1,2,3,0,4,6,};
        String[] etykiety = {"kaktus","bratek","chryzantemy","lawenda",	"irys","hortensja"};

        new WykresSlupkowy(dane, etykiety);
    }
}
