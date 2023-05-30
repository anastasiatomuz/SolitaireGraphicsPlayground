import javax.swing.*;

public class TextPanel extends JPanel {

    private GameComponent gameComponent;
    private JLabel messageLabel;
    private JTextArea messageArea;

    public TextPanel (GameComponent gameComponent){
        this.gameComponent = gameComponent;
        messageLabel = new JLabel("Message");
        add(messageLabel);


        messageArea = new JTextArea("No message yet", 4, 60);
        messageArea.setEditable(false);
        add(messageArea);



        // initialize the panel using the init() private helper method
    }

    public void updateText(String toDisplay){
        messageArea.setText(toDisplay);
    }
}
