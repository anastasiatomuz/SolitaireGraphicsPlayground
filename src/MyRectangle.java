import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MyRectangle extends Rectangle {
    private Card card;
    private int width = 100;
    private int height = 140;

    private int smallerHeight = 40;

    private Point startingPoint;

    private boolean halfHidden;

    private Dimension currentDimension;

    private final Dimension FULL_DIMENSION = new Dimension(100, 140);

    private final Dimension SMALL_DIMENSION = new Dimension(100, 40);

    public MyRectangle(Card card, Point startingPoint, boolean halfHidden){
        super(startingPoint, new Dimension(100, 140));
        this.card = card;
        this.startingPoint = startingPoint;
        this.halfHidden = halfHidden;
        this.currentDimension = FULL_DIMENSION;
    }

    public void changeCardType(boolean fullSize){
        if (fullSize){
            currentDimension = FULL_DIMENSION;
        } else {
            currentDimension = SMALL_DIMENSION;
        }
        setBounds(startingPoint.x, startingPoint.y, currentDimension.width, currentDimension.height);
    }
    public void updateCard(Card newCard){
        card = newCard;
    }

    public Card getCard(){
        return card;
    }

    public void draw(Graphics g) {
        Color color;
        if (card.isVisible()){
            color = Color.white;
        } else {
            color = Color.pink;
        }
        g.setColor(color);

        if (halfHidden){
            changeCardType(false);//make card half-hidden dimensions
            drawCardAndOutline(g,startingPoint.x, startingPoint.y, currentDimension.width, currentDimension.height);
            if (card.isVisible()){
                g.setColor(Color.BLACK);
                g.drawString(card.cardInfo(), startingPoint.x + width/2 - 5, startingPoint.y + smallerHeight /2);
            }
        } else {
            changeCardType(true);//make card half-hidden dimensions
            drawCardAndOutline(g,startingPoint.x, startingPoint.y, currentDimension.width, currentDimension.height);
            if (card.isVisible()){
                g.setColor(Color.BLACK);
                g.drawString(card.cardInfo(), startingPoint.x + width/2 - 5, startingPoint.y + height /2);
            }
        }
    }

    public void draw(Graphics g, String cardVal) throws IOException {
        if (cardVal.equals("empty")) {
            g.setColor(Color.GRAY);
            drawCardAndOutline(g,startingPoint.x, startingPoint.y, currentDimension.width, currentDimension.height);
            /*
            //IMPORTANT for setting an image in a rectangle
            //https://stackoverflow.com/questions/9864267/loading-resources-like-images-while-running-project-distributed-as-jar-archive/9866659#9866659
            //https://www.baeldung.com/java-resize-image
           //
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/images/ace_of_diamonds.png")));

            g.drawImage(resizeImage(image, 100, 140), startingPoint.x, startingPoint.y, null);
            */
        } else if (cardVal.equals("full")){
            g.setColor(Color.PINK);
            drawCardAndOutline(g,startingPoint.x, startingPoint.y, currentDimension.width, currentDimension.height);
        } else {
            // set the color
            g.setColor(Color.WHITE);

            drawCardAndOutline(g,startingPoint.x, startingPoint.y, currentDimension.width, currentDimension.height);

            if (cardVal.equals("d") || cardVal.equals("h")){
                g.setColor(Color.RED);
            } else if (cardVal.equals("C") || cardVal.equals("S")) {
                g.setColor(Color.BLACK);
            }

                //g.drawImage()
            g.drawString(cardVal, startingPoint.x + width/2 - 5, startingPoint.y + height /2);
        }
    }

    private void drawCardAndOutline(Graphics g, int x, int y, int w, int h){
        g.fillRect(x, y, w, h);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, w, h);
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }
}