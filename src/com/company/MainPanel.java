package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is main panel class
 * it contains the The similar panel between AdminGUI and StudentGUI and TeacherGUI
 * also it contains the similar buttons
 *
 * @author Mahdi Rahmani
 * @version 1.0
 */
public class MainPanel extends JPanel {
    // the top panel of frame
    private JPanel firstPanel;
    // the right panel of frame
    private JPanel secondPanel;
    // the biggest panel in the middle of frame
    private JPanel thirdPanel;
    // The list of Right panel buttons
    private ArrayList<JButton> rightPanel_buttons;
    // The profile button
    private JButton profile;
    // The change pass & user button
    private JButton changePss_User;
    // The image
    private BufferedImage image;

    /**
     * This is the constructor of this class
     * creat new main panel
     */
    public MainPanel() {
        super();
        rightPanel_buttons = new ArrayList<>();
        setLayout(new BorderLayout());
        AbstractBorder brdr1 = new TextBubbleBorder(Color.BLACK, 2, 16, 16);
        AbstractBorder brdr2= new TextBubbleBorder(Color.RED, 2, 16, 0);
        firstPanel = new JPanel(new BorderLayout());
        firstPanel.setBorder(brdr1);
        secondPanel = new JPanel(new BorderLayout());
        secondPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
        thirdPanel = new JPanel(new GridBagLayout());
        thirdPanel.setBorder(brdr2);
        add(firstPanel, BorderLayout.NORTH);
        add(secondPanel, BorderLayout.EAST);
        add(thirdPanel, BorderLayout.CENTER);

        int width1 = firstPanel.getPreferredSize().width;
        int height1 = firstPanel.getPreferredSize().height + 90;

        int width2 = secondPanel.getPreferredSize().width + 180;
        int height2 = secondPanel.getPreferredSize().height;

        firstPanel.setPreferredSize(new Dimension(width1, height1));
        secondPanel.setPreferredSize(new Dimension(width2, height2));
    }

    /**
     * This is the right panel
     * We add the buttons inside it
     */
    public void rightPanel() {
        JPanel butCol = new JPanel();
        butCol.setLayout(new BoxLayout(butCol, BoxLayout.Y_AXIS));
        butCol.setBorder(new TitledBorder(new EtchedBorder(), "Management"));
        int width = butCol.getPreferredSize().width + 30;
        int height = butCol.getPreferredSize().height;
        butCol.setPreferredSize(new Dimension(width, height));


        profile = new JButton("profile");
        changePss_User = new JButton("change Pas & User");
        rightPanel_buttons.add(0, profile);
        rightPanel_buttons.add(changePss_User);

        for (JButton button : rightPanel_buttons) {
            changeBoth(button);
            butCol.add(button);
            butCol.add(Box.createRigidArea(new Dimension(0, 30)));
        }
        secondPanel.add(butCol, BorderLayout.CENTER);
    }

    /**
     * this method change dimension of a component and set max Value
     * @param comp the component
     */
    private static void changeBoth(JComponent comp) {
        comp.setAlignmentX(Component.CENTER_ALIGNMENT);
        comp.setAlignmentY(Component.CENTER_ALIGNMENT);
        Dimension dim = new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
        comp.setMaximumSize(dim);
    }

    /**
     * The top panel that contains the image of user
     * @param picPath the path that image is saved inside it
     */
    public void topPanel(String picPath) {
        try {
            image = ImageIO.read(new File(picPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(image));
        picLabel.setSize(100, 80);

        Image imageJLFit = image.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH);
        picLabel.setIcon(new ImageIcon(imageJLFit));
        firstPanel.add(picLabel, BorderLayout.EAST);
    }

    /**
     * get the right panel
     * @return right panel
     */
    public JPanel getSecondPanel() {
        return secondPanel;
    }

    /**
     * get the top panel
     * @return top panel
     */
    public JPanel getFirstPanel() {
        return firstPanel;
    }

    /**
     * get the middle panel
     * @return third panel
     */
    public JPanel getThirdPanel() {
        return thirdPanel;
    }

    /**
     * get the changePss_User button
     * @return button
     */
    public JButton getChangePss_User() {
        return changePss_User;
    }

    /**
     * get the profile button
     * @return button
     */
    public JButton getProfile() {
        return profile;
    }

    /**
     * add button to right panel
     * @param button the entry button
     */
    public void addButton(JButton button) {
        rightPanel_buttons.add(button);
    }

    /**
     * create BubbleBorder
     */
    class TextBubbleBorder extends AbstractBorder {

        private Color color;
        private int thickness = 4;
        private int radii = 8;
        private int pointerSize = 7;
        private Insets insets = null;
        private BasicStroke stroke = null;
        private int strokePad;
        private int pointerPad = 4;
        private boolean left = true;
        RenderingHints hints;

        TextBubbleBorder(Color color) {
            this(color, 4, 8, 7);
        }

        TextBubbleBorder(
                Color color, int thickness, int radii, int pointerSize) {
            this.thickness = thickness;
            this.radii = radii;
            this.pointerSize = pointerSize;
            this.color = color;

            stroke = new BasicStroke(thickness);
            strokePad = thickness / 2;

            hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int pad = radii + strokePad;
            int bottomPad = pad + pointerSize + strokePad;
            insets = new Insets(pad, pad, bottomPad, pad);
        }

        TextBubbleBorder(
                Color color, int thickness, int radii, int pointerSize, boolean left) {
            this(color, thickness, radii, pointerSize);
            this.left = left;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return insets;
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            return getBorderInsets(c);
        }

        @Override
        public void paintBorder(
                Component c,
                Graphics g,
                int x, int y,
                int width, int height) {

            Graphics2D g2 = (Graphics2D) g;

            int bottomLineY = height - thickness - pointerSize;

            RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(
                    0 + strokePad,
                    0 + strokePad,
                    width - thickness,
                    bottomLineY,
                    radii,
                    radii);

            Polygon pointer = new Polygon();

            if (left) {
                // left point
                pointer.addPoint(
                        strokePad + radii + pointerPad,
                        bottomLineY);
                // right point
                pointer.addPoint(
                        strokePad + radii + pointerPad + pointerSize,
                        bottomLineY);
                // bottom point
                pointer.addPoint(
                        strokePad + radii + pointerPad + (pointerSize / 2),
                        height - strokePad);
            } else {
                // left point
                pointer.addPoint(
                        width - (strokePad + radii + pointerPad),
                        bottomLineY);
                // right point
                pointer.addPoint(
                        width - (strokePad + radii + pointerPad + pointerSize),
                        bottomLineY);
                // bottom point
                pointer.addPoint(
                        width - (strokePad + radii + pointerPad + (pointerSize / 2)),
                        height - strokePad);
            }

            Area area = new Area(bubble);
            area.add(new Area(pointer));

            g2.setRenderingHints(hints);

            // Paint the BG color of the parent, everywhere outside the clip
            // of the text bubble.
            Component parent = c.getParent();
            if (parent != null) {
                Color bg = parent.getBackground();
                Rectangle rect = new Rectangle(0, 0, width, height);
                Area borderRegion = new Area(rect);
                borderRegion.subtract(area);
                g2.setClip(borderRegion);
                g2.setColor(bg);
                g2.fillRect(0, 0, width, height);
                g2.setClip(null);
            }

            g2.setColor(color);
            g2.setStroke(stroke);
            g2.draw(area);
        }
    }
}

