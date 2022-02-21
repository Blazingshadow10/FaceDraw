import java.util.Random;
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
    private int xPosition;
    private int yPosition;
    private int height;
    private int width;
    private int smileStatus;
    private OvalDraw eyesL;
    private OvalDraw eyesR;

    public int getXPosition() {
        return xPosition;
    }
    public void setXPostion(int x) {
        xPosition = x;
    }
    public int getYPosition() {
        return yPosition;
    }
    public void setYPostion(int x) {
        yPosition = x;
    }
    public int getheight() {
        return height;
    }
    public void setheight(int x) {
        height = x;
    }
    public int getwidth() {
        return width;
    }
    public void setwidth(int x) {
        width = x;
    }
    public int getsmileStatus() {
        return smileStatus;
    }
    public void setsmileStatus(int x) {
        smileStatus = x;
    }

    public Face() {

        xPosition = anyRandom.ran(0, 900);
        yPosition = anyRandom.ran(0, getHeight());
        height = anyRandom.ran(100,300);
        width = anyRandom.ran(100,300);
        smileStatus = anyRandom.ran(1,3);

        int eyeHeight = height / 5;
        int eyeWidth = eyeHeight / 2;
        int eyePositionX = xPosition + (width / 2) - (eyeWidth / 2);
        int eyePoisitonY = yPosition + (height / 2) - (eyeHeight / 2);

        eyesL = new OvalDraw(eyePositionX - 25, eyePoisitonY, eyeWidth, eyeHeight);
        eyesR = new OvalDraw(eyePositionX + 25, eyePoisitonY, eyeWidth, eyeHeight);
    }

    public String toString() {
        String s = "X = " + getXPosition() + " Y = " + getYPosition() + " Width = " + getwidth() + " Height = " + getheight();
        return s;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.yellow);
        g.fillOval(xPosition, yPosition, width, height);
        g.setColor(Color.black);
        eyesR.paintComponent(g);
        eyesL.paintComponent(g);
    
        switch(smileStatus) {
            case 1:
                g.drawArc(xPosition+5, yPosition-((height/2)-(height/3)), width-10, height-10, -45, -90);
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
        g.fillOval(getPositionX(), getPositionY(), getWidth(), getHeight());
    }
}

class FacePanel extends JPanel implements anyRandom {
    ArrayList<Face> FaceList = new ArrayList<Face>();
    public FacePanel() {
        int numFaces = anyRandom.ran(3,10);
        for (int i = 1; i <= numFaces; i++) {
            FaceList.add(new Face());
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Face f : FaceList){
            f.paintComponent(g);
            System.out.println(f);
        }
    }
}

public class FaceDraw {
    public static void main(String[] args) {
        System.out.println("FaceDraw...");

        JFrame myFrame = new JFrame("FaceDraw");
        myFrame.setBounds(0,0,900,800);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FacePanel myFacePanel = new FacePanel();
        myFrame.add(myFacePanel);
        myFrame.setVisible(true);
    }
}