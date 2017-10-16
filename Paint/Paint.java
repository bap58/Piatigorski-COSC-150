package Paint;

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class Paint extends JFrame
    implements MouseListener, MouseMotionListener, ActionListener
{
    //attributes to keep track of the mouse
    int downX, downY;       // where the mouse is when button is pressed
    int mouseX, mouseY;     // where the mouse was last used
    int upX, upY;           // where mouse is when button is released

    //attributes to keep track of previous and current shapes
    ShapeList theList;      //the list of shapes already drawn
    Color chosenColor;      //the color currently chosen by the user
    String chosenShape;     //the shape currently chosen by the user
    String saveFile;        //file name to save to
    String loadFile;        //file name to load from
    String textString;      //text from the text input to write on the screen
    boolean kalEff;         //whether or not the kaleidescope effect is on
    boolean movingStuff;    //whether or not you're currently moving something

    //special attributes just to handle triangles, since they need 3 clicks
    int tx1, tx2, tx3;     //keeps the 3 x values for a triangle being made
    int ty1, ty2, ty3;     //keeps the 3 y values for a triangle being made
    int triCount;   //counts how many clicks the user has done so far when making a triangle

    //panels
    JPanel centerPanel;     //the center panel where drawing will occur
    JPanel colorPicker;     //the west panel where users pick colors
    JPanel shapePicker;     //the east panel where users pick shapes
    JPanel choiceDisplay;   //the south panel where the users' picks are shown

    //color buttons, goes in the color picker menu
    JButton redButton;
    JButton orangeButton;
    JButton yellowButton;
    JButton greenButton;
    JButton blueButton;
    JButton purpleButton;
    JButton blackButton;

    //shape buttons, to go in the shape picker menu
    JButton lineButton;             //selects a line
    JButton ovalButton;             //selects an oval
    JButton rectButton;             //selects a rectangle
    JButton triButton;              //selects a triangle
    JButton textButton;             //selects a text box
    JButton kaleidescopeButton;     //turns on/off the kaleidescope effect
    JButton clearButton;            //clears all shapes

    //save and load buttons, will go in the shape picker menu as well
    JButton saveButton;
    JButton loadButton;

    //text fields, accompanies their corresponding buttons
    JTextField saveText;
    JTextField loadText;
    JTextField textInput;

    public static void main(String[] args)
    {
        new Paint();
    }

    //Paint constructor
    public Paint()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Paint");

        addMouseListener(this);
        addMouseMotionListener(this);


        setLayout(new BorderLayout());

        //Add a blank center panel to the frame
        centerPanel = new JPanel();
        centerPanel.setSize(800, 800);
        add(centerPanel, BorderLayout.CENTER);

        //add the color picker to the west frame
        colorPicker = new JPanel();
        colorPicker.setLayout(new GridLayout(7,1));
        add(colorPicker, BorderLayout.WEST);
        setColorButtons();

        //add shape picker to the east frame
        shapePicker = new JPanel();
        shapePicker.setLayout(new GridLayout(6, 2));
        add(shapePicker, BorderLayout.EAST);
        setShapeButtons();

        //add the choice display to the south frame
        choiceDisplay = new JPanel();
        choiceDisplay.setLayout(new GridLayout());

        setSize(1500,1000);
        setVisible(true);

        //instantiate a new list to keep all the shapes
        theList = new ShapeList();

        //set the defaults for each attribute tracking the shapes
        kalEff = false;
        movingStuff = false;
        triCount = 0;
        textString = "";
        saveFile = "saveFilePaint.txt";
        chosenColor = Color.black;

    }

    //Mouse event functions for listener and motion listener
    //Mouse listener:
    @Override public void mouseClicked(MouseEvent m) {
        mouseX = m.getX();
        mouseY = m.getY();
    }
    @Override public void mousePressed(MouseEvent m) {
        mouseX = downX = m.getX();
        mouseY = downY = m.getY();

        //figure out if you pressed on a shape by asking each shape in the list
        movingStuff = theList.clickedAShape(downX, downY);
        if(movingStuff) { System.out.println("Clicked on a shape...You are moving it"); }

        repaint();
    }
    @Override public void mouseReleased(MouseEvent m) {
        mouseX = upX = m.getX();
        mouseY = upY = m.getY();

        //if we aren't movingStuff
        //when the mouse is released, something was made. so we have
        //to figure out what shape it was and add it to the list of shapes
        if(!movingStuff) {
            if (chosenShape == "line") {
                theList.add(new Line(chosenColor, downX, downY, upX, upY));

            } else if (chosenShape == "rectangle") {
                //must check if downX and Y are less than mouseX and Y
                //and use the lesser ones for the top left corner
                theList.add(new Rectangle(chosenColor, Math.min(downX, mouseX),
                        Math.min(downY, mouseY), Math.abs(mouseX - downX),
                        Math.abs(mouseY - downY)));

                //like square, make sure we know which corner to use as the
                //upper left corner
            } else if (chosenShape == "oval") {
                theList.add(new Oval(chosenColor, Math.min(downX, mouseX),
                        Math.min(downY, mouseY), Math.abs(mouseX - downX),
                        Math.abs(mouseY - downY)));

            } else if (chosenShape == "triangle") {
                //add the coordinates of this click to the array and update triCount
                if (triCount == 0) {
                    tx1 = upX;
                    ty1 = upY;
                } else if (triCount == 1) {
                    tx2 = upX;
                    ty2 = upY;
                } else if (triCount == 2) {
                    tx3 = upX;
                    ty3 = upY;
                }
                triCount++;

                //if triCount is 3, draw the triangle. else, do nothing yet
                if (triCount == 3) {
                    theList.add(new Triangle(chosenColor, tx1, ty1, tx2, ty2, tx3, ty3));

                    //reset tri count for the next triangle
                    triCount = 0;
                }


            } else if (chosenShape == "text") {
                theList.add(new TextBox(textString, chosenColor, upX, upY));
            }
        }

        else //movingStuff is true, move a shape
        {
            theList.moveShape(downX, downY, upX, upY);
        }


        repaint();
    }

    @Override public void mouseEntered(MouseEvent e) { }
    @Override public void mouseExited(MouseEvent e) { }

    //Mouse motion listener:
    @Override public void mouseDragged(MouseEvent m) {
        mouseX = m.getX();
        mouseY = m.getY();

        repaint();
    }
    @Override public void mouseMoved(MouseEvent e) { }

    //action event listener for buttons,
    @Override public void actionPerformed(ActionEvent e)
    {
        //Color buttons:
        //if a color button is pressed, the chosen color will be set
        //to the corresponding color
        if(e.getSource() == redButton)
        {
            chosenColor = Color.red;
        }

        if(e.getSource() == orangeButton)
        {
            chosenColor = Color.orange;
        }

        if(e.getSource() == yellowButton)
        {
            chosenColor = Color.yellow;
        }

        if(e.getSource() == greenButton)
        {
            chosenColor = Color.green;
        }

        if(e.getSource() == blueButton)
        {
            chosenColor = Color.blue;
        }

        if(e.getSource() == purpleButton)
        {
            chosenColor = Color.MAGENTA;
        }

        if(e.getSource() == blackButton)
        {
            chosenColor = Color.black;
        }

        //Shape buttons:
        //if a shape button is pressed, the type of shape to be drawn will
        //be set to that shape
        if(e.getSource() == lineButton)
        {
            chosenShape = "line";
        }
        if(e.getSource() == ovalButton)
        {
            chosenShape = "oval";
        }
        if(e.getSource() == rectButton)
        {
            chosenShape = "rectangle";
        }
        if(e.getSource() == triButton)
        {
            chosenShape = "triangle";
        }
        if(e.getSource() == textButton)
        {
            chosenShape = "text";
        }

        //Other buttons: save, load, clear, and kaleidescope
        if(e.getSource() == saveButton)
        {
            theList.save(saveFile);
            System.out.println("File saved to "+saveFile);
        }
        if(e.getSource() == loadButton)
        {
            theList.load(loadFile);
            System.out.println("File loaded from "+loadFile);
            repaint();
        }
        if(e.getSource() == clearButton)
        {
            theList.clear();
            repaint();
        }


        //if the action came from any of the text fields,
        //set the appropriate variable and echo the input
        if(e.getSource() == textInput)
        {
            textString = textInput.getText();
            System.out.println("Text entered: "+textString);
        }
        if(e.getSource() == saveText)
        {
            saveFile = saveText.getText();
            System.out.println("Save to file: "+saveFile);
        }
        if(e.getSource() == loadText)
        {
            loadFile = loadText.getText();
            System.out.println("Load from file: "+loadFile);
        }

    }


    //Paint to draw everything
    @Override public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(chosenColor);

        //if we're not movingStuff is false,
        //decide which shape has been selected by the user
        //draw the appropriate shape
        if(!movingStuff) {
            if (chosenShape == "line") {
                g.drawLine(downX, downY, mouseX, mouseY);

            } else if (chosenShape == "rectangle") {
                //must check if downX and Y are less than mouseX and Y
                //and use the lesser ones for the top left corner
                g.fillRect(Math.min(downX, mouseX), Math.min(downY, mouseY),
                        Math.abs(mouseX - downX), Math.abs(mouseY - downY));

            } else if (chosenShape == "oval") {
                //check for the top left corner, just like rectangle
                g.fillOval(Math.min(downX, mouseX), Math.min(downY, mouseY),
                        Math.abs(mouseX - downX), Math.abs(mouseY - downY));

            } else if (chosenShape == "triangle") {
                //won't draw the shape live, only after all 3 clicks have been made.
                //the triangle will be drawn when the list is repainted

            } else if (chosenShape == "text") {
                g.drawString(textString, mouseX, mouseY);

            }
        }

        //draw the whole list so all the shapes show up
        theList.drawList(g);
    }

    //formats each of the color buttons, giving them text and a background color
    //before adding them to the color picker grid in the west panel
    public void setColorButtons()
    {
        redButton = new JButton("Red");
        redButton.setBackground(Color.red);
        redButton.addActionListener(this);
        colorPicker.add(redButton);

        orangeButton = new JButton("Orange");
        orangeButton.setBackground(Color.orange);
        orangeButton.addActionListener(this);
        colorPicker.add(orangeButton);

        yellowButton = new JButton("Yellow");
        yellowButton.setBackground(Color.yellow);
        yellowButton.addActionListener(this);
        colorPicker.add(yellowButton);

        greenButton = new JButton("Green");
        greenButton.setBackground(Color.green);
        greenButton.addActionListener(this);
        colorPicker.add(greenButton);

        blueButton = new JButton("Blue");
        blueButton.setBackground(Color.blue);
        blueButton.addActionListener(this);
        colorPicker.add(blueButton);

        purpleButton = new JButton("Purple");
        purpleButton.setBackground(Color.MAGENTA);
        purpleButton.addActionListener(this);
        colorPicker.add(purpleButton);

        blackButton = new JButton("Black");
        blackButton.setBackground(Color.BLACK);
        blackButton.addActionListener(this);
        colorPicker.add(blackButton);
    }

    //adds each button and text field for the different types of shapes
    //also adds buttons to load and save files and their corresponding text
    //fields to the menu in the east panel
    public void setShapeButtons()
    {
        saveButton = new JButton("save");
        saveButton.addActionListener(this);
        shapePicker.add(saveButton);

        saveText = new JTextField("Name of the file to save goes here", 20);
        saveText.addActionListener(this);
        shapePicker.add(saveText);

        loadButton = new JButton("load");
        loadButton.addActionListener(this);
        shapePicker.add(loadButton);

        loadText = new JTextField("Name of the file to load goes here", 20);
        loadText.addActionListener(this);
        shapePicker.add(loadText);

        lineButton = new JButton("line");
        lineButton.addActionListener(this);
        shapePicker.add(lineButton);

        ovalButton = new JButton("oval");
        ovalButton.addActionListener(this);
        shapePicker.add(ovalButton);

        rectButton = new JButton("rectangle");
        rectButton.addActionListener(this);
        shapePicker.add(rectButton);

        triButton = new JButton("triangle");
        triButton.addActionListener(this);
        shapePicker.add(triButton);

        textButton = new JButton("text box");
        textButton.addActionListener(this);
        shapePicker.add(textButton);

        textInput = new JTextField("Text to draw goes here", 20);
        textInput.addActionListener(this);
        shapePicker.add(textInput);

        kaleidescopeButton = new JButton("Kaleidescope");
        kaleidescopeButton.addActionListener(this);
        shapePicker.add(kaleidescopeButton);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        shapePicker.add(clearButton);
    }

}
