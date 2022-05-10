import java.awt.Graphics;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *  Square Rose, a recursive algorithm
 */

public class SeripinskisTriangle extends JPanel
{
    private int levels;
    public SeripinskisTriangle(int lev)
    {
        levels = lev;
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);  // Call JPanel's paintComponent method
        //   to paint the background
        int xCenter = getWidth() / 2;
        int yCenter = getHeight() / 2;


        int [] xcoord = {xCenter - 128, xCenter, xCenter + 128};
        int [] ycoord = {yCenter, yCenter - 128, yCenter};

        g.setColor(Color.RED);
        drawAndSplit(g, xcoord, ycoord, levels);

    }

    public int [] midpoints(int [] x)
    {
        int [] m = new int [3];

        m[0] = (x[0] + x[1])/2;
        m[1] = (x[1] + x[2])/2;
        m[2] = (x[2] + x[0])/2;

        return m;
    }

    public void drawAndSplit(Graphics g, int []x, int [] y, int times) {
        if (times == 0) return;
        else if (times == 1) {
            g.fillPolygon(x,y, 3);
            return;
        } else if (times > 0) {
            g.drawPolygon(x, y, 3);
            int[] midX = midpoints(x);
            int[] midY = midpoints(y);

            //Left Triangle
            drawAndSplit(g, new int[] {x[0], midX[0], midX[2]}, new int[] {y[0], midY[0], midY[2]}, times - 1);
            //Top Triangle
            drawAndSplit(g, new int[] {midX[0], x[1], midX[1]}, new int[] {midY[0], y[1], midY[1]}, times - 1);
            //Right Triangle
            drawAndSplit(g, new int[] {midX[2], midX[1], x[2]}, new int[] {midY[2], midY[1], y[2]}, times - 1);
        }

    }
    public static void main(String[] args)
    {
        JFrame window = new JFrame("Fractals");
        window.setBounds(200, 200, 500, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SeripinskisTriangle panel = new SeripinskisTriangle(4);
        panel.setBackground(Color.WHITE);
        Container c = window.getContentPane();
        c.add(panel);
        window.setVisible(true);
    }
}
