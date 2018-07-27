/**
 * Created by Yining on 7/19/18.
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;

public class Editor extends Application {

//    private Button btnHello;  // Declare a "Button" control

    private final Rectangle cursor;

    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;
    private linkedList dLList;

    public Editor() {
        // Create a rectangle to surround the text that gets displayed.  Initialize it with a size
        // of 0, since there isn't any text yet.
        cursor = new Rectangle(1, 20);
        dLList = new linkedList();
        dLList.sentinel.setO(cursor);

    }

    /** An EventHandler to handle keys that get pressed. */
    private class KeyEventHandler implements EventHandler<KeyEvent> {
//
        private double positionX;
        private double positionY;

        private static final int STARTING_FONT_SIZE = 20;

        /** The Text to display on the screen. */
        private Text displayText = new Text(positionX, positionY, "");
        private int fontSize = STARTING_FONT_SIZE;
        private String characterTyped;
//        private ArrayList keyEvents = new ArrayList<KeyEvent>();

        private String fontName = "Verdana";
        private Group root;

        KeyEventHandler(final Group root, int windowWidth, int windowHeight) {
            this.root = root;

        }

        @Override
        public void handle(KeyEvent keyEvent) {

            if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                // Arrow keys should be processed using the KEY_PRESSED event, because KEY_PRESSED
                // events have a code that we can check (KEY_TYPED events don't have an associated
                // KeyCode).
                KeyCode code = keyEvent.getCode();
                if (code == KeyCode.UP || (keyEvent.isShortcutDown() && code == KeyCode.EQUALS || code == KeyCode.PLUS)) {
                    fontSize += 4;
                    positionX = 0;
                    positionY = 0;
                    for (Object i : root.getChildren()) {
                        if (i instanceof Text) {
                            ((Text) i).setFont(Font.font(fontSize));
                            ((Text) i).setX(positionX);
                            ((Text) i).setY(positionY);
                            positionX += ((Text) i).getLayoutBounds().getWidth();
                        }
                    }
                    displayText.setFont(Font.font(fontName, fontSize));
                    updateCursor();

                } else if (code == KeyCode.DOWN || (keyEvent.isShortcutDown() && code == KeyCode.MINUS)) {
                    fontSize = Math.max(1, fontSize - 4);
                    positionX = 0;
                    positionY = 0;
                    for (Object i : root.getChildren()) {
                        if (i instanceof Text) {
                            ((Text) i).setFont(Font.font(fontSize));
                            ((Text) i).setX(positionX);
                            ((Text) i).setY(positionY);
                            positionX += ((Text) i).getLayoutBounds().getWidth();
                        }

                    }
                    displayText.setFont(Font.font(fontName, fontSize));
                    updateCursor();

                }

            } else if (keyEvent.getEventType() == KeyEvent.KEY_TYPED) {
                // Use the KEY_TYPED event rather than KEY_PRESSED for letter keys, because with
                // the KEY_TYPED event, javafx handles the "Shift" key and associated
                // capitalization.
                characterTyped = keyEvent.getCharacter();
//                Text newInsert = new Text(0, 0, characterTyped);


                if (characterTyped.length() > 0 && characterTyped.charAt(0) != 8) {
                    if (characterTyped.equals("+") || characterTyped.equals("-") || characterTyped.equals("=")) {
                        return;
                    }
                    // Ignore control keys, which have zero length, as well as the backspace
                    // key, which is represented as a character of value = 8 on Windows.
                    showCharacter(characterTyped);

                    keyEvent.consume();
                }
                updateCursor();

            }
        }

//        @Override
//        public void handle(AWTEvent)

        private void showCharacter(String characterTyped) {
            Text newInsert = new Text(positionX, positionY, characterTyped);
            dLList.insert(newInsert);
            newInsert.setTextOrigin(VPos.TOP);
            newInsert.setFont(Font.font(fontName, fontSize));
            positionX += newInsert.getLayoutBounds().getWidth();
            root.getChildren().add(newInsert);
        }

        private void updateCursor() {

            // Figure out the size of the current text.
//            Text newInsert = new Text(0, 0, characterTyped);
//
//            double textHeight = newInsert.getLayoutBounds().getHeight();
//            double textWidth = displayText.getLayoutBounds().getWidth();

            // Calculate the position so that the text will be centered on the screen.
//            cursorX = textWidth;
//            cursorY = 0;

            cursor.setHeight(fontSize);

            cursor.setX(positionX);
            cursor.setY(positionY);

        }
    }

    /** An EventHandler to handle changing the color of the rectangle. */
    private class RectangleBlinkEventHandler implements EventHandler<ActionEvent> {
        private int currentColorIndex = 0;
        private Color[] boxColors =
                {Color.BLACK, Color.WHITE};

        RectangleBlinkEventHandler() {
            // Set the color to be the first color in the list.
            changeColor();
        }

        private void changeColor() {
            cursor.setFill(boxColors[currentColorIndex]);
            currentColorIndex = (currentColorIndex + 1) % boxColors.length;
        }

        @Override
        public void handle(ActionEvent event) {
            changeColor();
        }
    }

    /** Makes the text bounding box change color periodically. */
    public void makeRectangleColorChange() {
        // Create a Timeline that will call the "handle" function of RectangleBlinkEventHandler
        // every 1 second.
        final Timeline timeline = new Timeline();
        // The rectangle should continue blinking forever.
        timeline.setCycleCount(Timeline.INDEFINITE);
        RectangleBlinkEventHandler cursorChange = new RectangleBlinkEventHandler();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), cursorChange);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }


    @Override
    public void start(Stage primaryStage) {

        // Create a Node that will be the parent of all things displayed on the screen.
        Group root = new Group();
        // The Scene represents the window: its height and width will be the height and width
        // of the window displayed.
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);

        // To get information about what keys the user is pressing, create an EventHandler.
        // EventHandler subclasses must override the "handle" function, which will be called
        // by javafx.
        EventHandler<KeyEvent> keyEventHandler =
                new KeyEventHandler(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        // Register the event handler to be called for all KEY_PRESSED and KEY_TYPED events.
        scene.setOnKeyTyped(keyEventHandler);
        scene.setOnKeyPressed(keyEventHandler);
//        scene.setOnKeyReleased(keyEventHandler);

        // All new Nodes need to be added to the root in order to be displayed.
        root.getChildren().add(cursor);
        makeRectangleColorChange();

        // Construct a scene graph of nodes

        primaryStage.setScene(scene);    // The stage sets scene
        primaryStage.setTitle("Editor");  // Set window's title
        primaryStage.show();             // Set visible (show it)
    }

    public static void main(String[] args) {
        launch(args);
    }
}
