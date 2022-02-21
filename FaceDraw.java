import java.util.Random;
import java.awt.Container;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.ArrayList;

interface anyRandom {
    public static int ran(int min, int max) {
        Random ran = new Random();
        return min + ran.nextInt(max-min+1);
    }
}

class Face extends OvalDraw implements anyRandom {
    private int xPosition = anyRandom.ran(0, 700);
    private int yPosition = anyRandom.ran(0, 600);
    private int height = anyRandom.ran(100,200);
    private int width = anyRandom.ran(100,200);
    private int smileStatus = anyRandom.ran(1,3);
    private OvalDraw eyesL;
    private OvalDraw eyesR;

    public Face() {
        int eyeHeight = height / 5;
        int eyeWidth = eyeHeight / 2;
        int eyePositionX = xPosition + (width / 2) - (eyeWidth / 2);
        int eyePoisitonY = yPosition + (height / 3) - (eyeHeight / 2);

        eyesL = new OvalDraw(eyePositionX - 25, eyePoisitonY, eyeWidth, eyeHeight);
        eyesR = new OvalDraw(eyePositionX + 25, eyePoisitonY, eyeWidth, eyeHeight);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        eyesR.paintComponent(g);
        eyesL.paintComponent(g);
        g.drawOval(xPosition, yPosition, width, height);
        
        switch(smileStatus) {
            case 1:
                g.drawArc(xPosition, yPosition+(height/2), width-10, height-10, 45, 90);
                break;
            case 2:
                g.drawArc(xPosition, yPosition+(height/2), width-10, height-10, 45, 90);
                break;
            case 3:    
                g.drawLine(xPosition+(width/2)-(width/4), yPosition+(height/2)+(height/6), xPosition+(width/2)+(width/4), yPosition+(height/2)+(height/6));       
                break;
        }
    }
}

class OvalDraw extends Oval {

    public OvalDraw() {
        super(0, 0, 0, 0);
    }

    public OvalDraw(int positionXIn, int positionYIn, int widthIn, int heightIn) {
        super(positionXIn, positionYIn, widthIn, heightIn);
    }

    public void paintComponent(Graphics g) {
        g.drawOval(getPositionX(), getPositionY(), getWidth(), getHeight());
    }
}

class FacePanel extends JPanel implements anyRandom {
    //ArrayList<Face> allFaces = new ArrayList<Face>();
    private Face f;
    
    public FacePanel() {
        f = new Face();
         //int numFaces = anyRandom.ran(3,10);
         //for (int i = 0; i <= numFaces; i++) {
         //   allFaces.add(new Face());
         // }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        f.paintComponent(g);
        //for (int j = 0; j <= allFaces.size(); j++){
           // allFaces.get(j).paintComponent(g);
        //}
    }
}

public class FaceDraw {
    public static void main(String[] args) {
        System.out.println("FaceDraw...");

        JFrame myFrame = new JFrame("FaceDraw");
        myFrame.setBounds(50,50,900,800);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FacePanel myFacePanel = new FacePanel();
        myFrame.add(myFacePanel);
        myFrame.setVisible(true);
    }
}