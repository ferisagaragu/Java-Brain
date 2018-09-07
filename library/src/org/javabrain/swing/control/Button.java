package org.javabrain.swing.control;

//<editor-fold defaultstate="collapsed" desc="import">
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import java.awt.Font;
import javax.swing.border.Border;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.awt.FontFormatException;
import javax.swing.BorderFactory;
import javax.swing.AbstractButton;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.*;
import javax.swing.plaf.metal.*;
import javax.swing.Timer;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
// </editor-fold>

public class Button extends JButton {
  
    private MaterialUIMovement animate;
    private Color hover = new Color(240, 240, 240);
    private Color color = Color.BLUE;
    private Color textColor = Color.white;
    private String lowerText;
    private int textSize = 13;
    
    public Button() {
        this("Button".toUpperCase(), UIManager.getColor("Button.background"), UIManager.getColor("Button.foreground"), UIManager.getColor("Button.highlight"));
        configureSettings(color,textColor,hover);
    }
    
    public Button(String text) {
        this(text.toUpperCase(), UIManager.getColor("Button.background"), UIManager.getColor("Button.foreground"), UIManager.getColor("Button.highlight"));
    }

    public Button(Icon icon) {
        this(icon, UIManager.getColor("Button.background"), UIManager.getColor("Button.foreground"), UIManager.getColor("Button.highlight"));
    }

    public Button(Icon icon, Color background, Color foreground, Color hover) {
        super(icon);
        configureSettings(background, foreground, hover);
    }

    public Button(String text, Color background, Color foreground, Color hover) {
        super(text.toUpperCase());
        configureSettings(background, foreground, hover);
    }

    public void setLowerText(String text) {
        super.setText(text); //To change body of generated methods, choose Tools | Templates.
    }

    public String getLowerText() {
        return lowerText;
    }
    
    private void configureSettings(Color background, Color foreground, Color hover) {
        this.setBackground(background);
        this.setForeground(foreground);

        animate = new MaterialUIMovement(hover, 5, 1000 / 30);
        animate.add(this);

        this.setFocusPainted(false);
        this.setUI(new MaterialButtonUI());
    }

    public Color getHover() {
        return hover;
    }

    public void setHover(Color hover) {
        this.hover = hover;
        animate = new MaterialUIMovement(hover, 5, 1000 / 30);
        animate.add(this);

        this.setFocusPainted(false);
        this.setUI(new MaterialButtonUI());
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);

        animate = new MaterialUIMovement(hover, 5, 1000 / 30);
        animate.add(this);
        this.setFocusPainted(false);
        this.setUI(new MaterialButtonUI());
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        setForeground(textColor);

        animate = new MaterialUIMovement(hover, 5, 1000 / 30);
        animate.add(this);
        this.setFocusPainted(false);
        this.setUI(new MaterialButtonUI());
    }

    @Override
    public void setText(String text) {
        super.setText(text.toUpperCase()); //To change body of generated methods, choose Tools | Templates.
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

}

//<editor-fold defaultstate="collapsed" desc="private class">
class DropShadowBorder extends AbstractBorder implements Border {
  private static enum Position {TOP, TOP_LEFT, LEFT, BOTTOM_LEFT,
    BOTTOM, BOTTOM_RIGHT, RIGHT, TOP_RIGHT};
  
  private static final Map<Integer,Map<Position,BufferedImage>> CACHE 
    = new HashMap<Integer,Map<Position,BufferedImage>>();
  
  private Color lineColor;
  private int lineWidth;
  private int shadowSize;
  private float shadowOpacity;
  private int cornerSize;
  private boolean showTopShadow;
  private boolean showLeftShadow;
  private boolean showBottomShadow;
  private boolean showRightShadow;
  
  public DropShadowBorder() {
    this(UIManager.getColor("Control"), 1, 5);
  }
  
  public DropShadowBorder(Color lineColor, int lineWidth, int shadowSize) {
    this(lineColor, lineWidth, shadowSize, .5f, 12, false, false, true, true);
  }
  
  public DropShadowBorder(Color lineColor, int lineWidth, boolean showLeftShadow) {
    this(lineColor, lineWidth, 5, .5f, 12, false, showLeftShadow, true, true);
  }
  
  public DropShadowBorder(Color lineColor, int lineWidth, int shadowSize,
                          float shadowOpacity, int cornerSize, boolean showTopShadow,
                          boolean showLeftShadow, boolean showBottomShadow, boolean showRightShadow) {
    this.lineColor = lineColor;
    this.lineWidth = lineWidth;
    this.shadowSize = shadowSize;
    this.shadowOpacity = shadowOpacity;
    this.cornerSize = cornerSize;
    this.showTopShadow = showTopShadow;
    this.showLeftShadow = showLeftShadow;
    this.showBottomShadow = showBottomShadow;
    this.showRightShadow = showRightShadow;
  }
  
  /**
   * @inheritDoc
   */
  public void paintBorder(Component c, Graphics graphics, int x, int y, int width, int height) {
    /*
     * 1) Get images for this border
     * 2) Paint the images for each side of the border that should be painted
     */
    Map<Position,BufferedImage> images = getImages(null);
    
    //compute the edges of the component -- not including the border
    //Insets borderInsets = getBorderInsets(c);
    // int leftEdge = x + borderInsets.left - lineWidth;
    // int rightEdge = x + width - borderInsets.right;
    // int topEdge = y + borderInsets.top - lineWidth;
    // int bottomEdge = y + height - borderInsets.bottom;
    Graphics2D g2 = (Graphics2D)graphics;
    g2.setColor(lineColor);
    
    //The location and size of the shadows depends on which shadows are being
    //drawn. For instance, if the left & bottom shadows are being drawn, then
    //the left shadow extends all the way down to the corner, a corner is drawn,
    //and then the bottom shadow begins at the corner. If, however, only the
    //bottom shadow is drawn, then the bottom-left corner is drawn to the
    //right of the corner, and the bottom shadow is somewhat shorter than before.
    
    Point topLeftShadowPoint = null;
    if (showLeftShadow || showTopShadow) {
      topLeftShadowPoint = new Point();
      if (showLeftShadow && !showTopShadow) {
        topLeftShadowPoint.setLocation(x, y + shadowSize);
      } else if (showLeftShadow && showTopShadow) {
        topLeftShadowPoint.setLocation(x, y);
      } else if (!showLeftShadow && showTopShadow) {
        topLeftShadowPoint.setLocation(x + shadowSize, y);
      }
    }
    
    Point bottomLeftShadowPoint = null;
    if (showLeftShadow || showBottomShadow) {
      bottomLeftShadowPoint = new Point();
      if (showLeftShadow && !showBottomShadow) {
        bottomLeftShadowPoint.setLocation(x, y + height - shadowSize - shadowSize);
      } else if (showLeftShadow && showBottomShadow) {
        bottomLeftShadowPoint.setLocation(x, y + height - shadowSize);
      } else if (!showLeftShadow && showBottomShadow) {
        bottomLeftShadowPoint.setLocation(x + shadowSize, y + height - shadowSize);
      }
    }
    
    Point bottomRightShadowPoint = null;
    if (showRightShadow || showBottomShadow) {
      bottomRightShadowPoint = new Point();
      if (showRightShadow && !showBottomShadow) {
        bottomRightShadowPoint.setLocation(x + width - shadowSize, y + height - shadowSize - shadowSize);
      } else if (showRightShadow && showBottomShadow) {
        bottomRightShadowPoint.setLocation(x + width - shadowSize, y + height - shadowSize);
      } else if (!showRightShadow && showBottomShadow) {
        bottomRightShadowPoint.setLocation(x + width - shadowSize - shadowSize, y + height - shadowSize);
      }
    }
    
    Point topRightShadowPoint = null;
    if (showRightShadow || showTopShadow) {
      topRightShadowPoint = new Point();
      if (showRightShadow && !showTopShadow) {
        topRightShadowPoint.setLocation(x + width - shadowSize, y + shadowSize);
      } else if (showRightShadow && showTopShadow) {
        topRightShadowPoint.setLocation(x + width - shadowSize, y);
      } else if (!showRightShadow && showTopShadow) {
        topRightShadowPoint.setLocation(x + width - shadowSize - shadowSize, y);
      }
    }
    
    if (showLeftShadow) {
      Rectangle leftShadowRect = new Rectangle(x, (int)(topLeftShadowPoint.getY() + shadowSize), shadowSize, (int)(bottomLeftShadowPoint.getY() - topLeftShadowPoint.getY() - shadowSize));
      g2.drawImage(images.get(Position.LEFT).getScaledInstance(leftShadowRect.width, leftShadowRect.height, Image.SCALE_FAST), leftShadowRect.x, leftShadowRect.y, null);
    }
    
    if (showBottomShadow) {
      Rectangle bottomShadowRect = new Rectangle((int)(bottomLeftShadowPoint.getX() + shadowSize), y + height - shadowSize, (int)(bottomRightShadowPoint.getX() - bottomLeftShadowPoint.getX() - shadowSize), shadowSize);
      g2.drawImage(images.get(Position.BOTTOM).getScaledInstance(bottomShadowRect.width, bottomShadowRect.height, Image.SCALE_FAST), bottomShadowRect.x, bottomShadowRect.y, null);
    }
    
    if (showRightShadow) {
      Rectangle rightShadowRect = new Rectangle(x + width - shadowSize, (int)(topRightShadowPoint.getY() + shadowSize), shadowSize, (int)(bottomRightShadowPoint.getY() - topRightShadowPoint.getY() - shadowSize));
      g2.drawImage(images.get(Position.RIGHT).getScaledInstance(rightShadowRect.width, rightShadowRect.height, Image.SCALE_FAST), rightShadowRect.x, rightShadowRect.y, null);
    }
    
    if (showTopShadow) {
      Rectangle topShadowRect = new Rectangle((int)topLeftShadowPoint.getX() + shadowSize, y, (int)(topRightShadowPoint.getX() - topLeftShadowPoint.getX() - shadowSize), shadowSize);
      g2.drawImage(images.get(Position.TOP).getScaledInstance(topShadowRect.width, topShadowRect.height, Image.SCALE_FAST), topShadowRect.x, topShadowRect.y, null);
    }
    
    if (showLeftShadow || showTopShadow) {
      g2.drawImage(images.get(Position.TOP_LEFT), null, (int)topLeftShadowPoint.getX(), (int)topLeftShadowPoint.getY());
    }
    if (showLeftShadow || showBottomShadow) {
      g2.drawImage(images.get(Position.BOTTOM_LEFT), null, (int)bottomLeftShadowPoint.getX(), (int)bottomLeftShadowPoint.getY());
    }
    if (showRightShadow || showBottomShadow) {
      g2.drawImage(images.get(Position.BOTTOM_RIGHT), null, (int)bottomRightShadowPoint.getX(), (int)bottomRightShadowPoint.getY());
    }
    if (showRightShadow || showTopShadow) {
      g2.drawImage(images.get(Position.TOP_RIGHT), null, (int)topRightShadowPoint.getX(), (int)topRightShadowPoint.getY());
    }
  }
  
  private Map<Position,BufferedImage> getImages(Graphics2D g2) {
    //first, check to see if an image for this size has already been rendered
    //if so, use the cache. Else, draw and save
    Map<Position,BufferedImage> images = CACHE.get(shadowSize);
    if (images == null) {
      images = new HashMap<Position,BufferedImage>();
      
      /*
       * Do draw a drop shadow, I have to:
       *  1) Create a rounded rectangle
       *  2) Create a BufferedImage to draw the rounded rect in
       *  3) Translate the graphics for the image, so that the rectangle
       *     is centered in the drawn space. The border around the rectangle
       *     needs to be shadowWidth wide, so that there is space for the
       *     shadow to be drawn.
       *  4) Draw the rounded rect as black, with an opacity of 50%
       *  5) Create the BLUR_KERNEL
       *  6) Blur the image
       *  7) copy off the corners, sides, etc into images to be used for
       *     drawing the Border
       */
      int rectWidth = cornerSize + 1;
      RoundRectangle2D rect = new RoundRectangle2D.Double(0, 0, rectWidth, rectWidth, cornerSize, cornerSize);
      int imageWidth = rectWidth + shadowSize * 2;
      BufferedImage image = new BufferedImage(imageWidth, imageWidth, BufferedImage.TYPE_INT_ARGB);
      Graphics2D buffer = (Graphics2D)image.getGraphics();
      buffer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
      buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      buffer.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      buffer.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
      buffer.setColor(new Color(0.0f, 0.0f, 0.0f, shadowOpacity));
      buffer.translate(shadowSize, shadowSize);
      buffer.fill(rect);
      float blurry = 1.0f / (float)(shadowSize * shadowSize);//1.0f / (float)(shadowSize * shadowSize);
      float[] blurKernel = new float[shadowSize * shadowSize];
      for (int i=0; i<blurKernel.length; i++) {
        blurKernel[i] = blurry;
      }
      ConvolveOp blur = new ConvolveOp(new Kernel(shadowSize, shadowSize, blurKernel));
      BufferedImage targetImage = new BufferedImage(imageWidth, imageWidth, BufferedImage.TYPE_INT_ARGB);
      ((Graphics2D)targetImage.getGraphics()).drawImage(image, blur, -(shadowSize/2), -(shadowSize/2));
      
      int x = 1;
      int y = 1;
      int w = shadowSize;
      int h = shadowSize;
      images.put(Position.TOP_LEFT, targetImage.getSubimage(x, y, w, h));
      x = 1;
      y = h;
      w = shadowSize;
      h = 1;
      images.put(Position.LEFT, targetImage.getSubimage(x, y, w, h));
      x = 1;
      y = rectWidth;
      w = shadowSize;
      h = shadowSize;
      images.put(Position.BOTTOM_LEFT, targetImage.getSubimage(x, y, w, h));
      x = cornerSize + 1;
      y = rectWidth;
      w = 1;
      h = shadowSize;
      images.put(Position.BOTTOM, targetImage.getSubimage(x, y, w, h));
      x = rectWidth;
      y = x;
      w = shadowSize;
      h = shadowSize;
      images.put(Position.BOTTOM_RIGHT, targetImage.getSubimage(x, y, w, h));
      x = rectWidth;
      y = cornerSize + 1;
      w = shadowSize;
      h = 1;
      images.put(Position.RIGHT, targetImage.getSubimage(x, y, w, h));
      x = rectWidth;
      y = 1;
      w = shadowSize;
      h = shadowSize;
      images.put(Position.TOP_RIGHT, targetImage.getSubimage(x, y, w, h));
      x = shadowSize;
      y = 1;
      w = 1;
      h = shadowSize;
      images.put(Position.TOP, targetImage.getSubimage(x, y, w, h));
      
      buffer.dispose();
      image.flush();
    }
    return images;
  }
  
  /**
   * @inheritDoc
   */
  public Insets getBorderInsets(Component c) {
    int top = 4 + (showTopShadow ? lineWidth + shadowSize : lineWidth);
    int left = 4 + (showLeftShadow ? lineWidth + shadowSize : lineWidth);
    int bottom = 4 + (showBottomShadow ? lineWidth + shadowSize : lineWidth);
    int right = 4 + (showRightShadow ? lineWidth + shadowSize : lineWidth);
    
    return new Insets(top, left, bottom, right);
  }
  
  /**
   * @inheritDoc
   */
  public boolean isBorderOpaque() {
    return true;
  }
  
  public boolean isShowTopShadow() {
    return showTopShadow;
  }
  
  public boolean isShowLeftShadow() {
    return showLeftShadow;
  }
  
  public boolean isShowRightShadow() {
    return showRightShadow;
  }
  
  public boolean isShowBottomShadow() {
    return showBottomShadow;
  }
  
  public int getLineWidth() {
    return lineWidth;
  }
  
  public Color getLineColor() {
    return lineColor;
  }
  
  public int getShadowSize() {
    return shadowSize;
  }
  
  public float getShadowOpacity() {
    return shadowOpacity;
  }
  
  public int getCornerSize() {
    return cornerSize;
  }
}

class GUITheme {

  private Color inactiveTextbox, activeTextbox, textboxText;
  private Color card;
  private Color menuSelectionText, menuSelectionBackground;
  private Color menuDisabledText;
  private Color borderlessButtonText;
  private Color inactiveBorderlessButtonBackground, activeBorderlessButtonBackground;
  private Color borderedButtonText;
  private Color inactiveBorderedButtonBackground, activeBorderedButtonBackground;

  private Border menuBorder = new DropShadowBorder (Color.BLACK, 0, 5, 0.3f, 12, true, true, true, true);
  private Border defaultBorder = new DropShadowBorder (Color.BLACK, 5, 5, 0.3f, 12, true, true, true, true);

  private static final Font ITALIC = getFont ("/org/external/MaterialDesing/fonts/Roboto-Italic.ttf");
  private static final Font LIGHT = getFont ("/org/external/MaterialDesing/fonts/Roboto-Light.ttf");
  private static final Font BOLD = getFont ("/org/external/MaterialDesing/fonts/Roboto-Medium.ttf");
  private static final Font REGULAR = getFont ("/org/external/MaterialDesing/fonts/Roboto-Regular.ttf");
  private static final Font THIN = getFont ("/org/external/MaterialDesing/fonts/Roboto-Thin.ttf");
  private static final Font THIN_ITALIC = getFont ("/org/external/MaterialDesing/fonts/RobotoCondensed-LightItalic.ttf");

  public static final GUITheme LIGHT_THEME = new GUITheme (new Color (230, 230, 230), new Color (220, 220, 220), Color.BLACK, Color.WHITE, Color.BLACK, new Color (230, 230, 230), new Color (0, 0, 0, 100),
                                                           new Color (33, 150, 243), Color.WHITE, new Color (240, 240, 240), Color.WHITE, new Color (245, 0, 87), new Color (255, 64, 129));

  public static final GUITheme DARK_THEME = new GUITheme (new Color (90, 90, 90), new Color (120, 120, 120), Color.WHITE, new Color (75, 75, 75), Color.WHITE, new Color (90, 90, 90), new Color (255, 255, 255, 100),
                                                          Color.WHITE, new Color (75, 75, 75), new Color (100, 100, 100), Color.WHITE, new Color (0, 150, 136), new Color (38, 166, 154));

  public Color getInactiveTextbox () {
    return inactiveTextbox;
  }

  public Color getActiveTextbox () {
    return activeTextbox;
  }

  public Color getTextboxText () {
    return textboxText;
  }

  public Color getCard () {
    return card;
  }

  public Color getMenuSelectionText () {
    return menuSelectionText;
  }

  public Color getMenuSelectionBackground () {
    return menuSelectionBackground;
  }

  public Color getMenuDisabledText () {
    return menuDisabledText;
  }

  public Color getBorderlessButtonText () {
    return borderlessButtonText;
  }

  public Color getInactiveBorderlessButtonBackground () {
    return inactiveBorderlessButtonBackground;
  }

  public Color getActiveBorderlessButtonBackground () {
    return activeBorderlessButtonBackground;
  }

  public Color getBorderedButtonText () {
    return borderedButtonText;
  }

  public Color getInactiveBorderedButtonBackground () {
    return inactiveBorderedButtonBackground;
  }

  public Color getActiveBorderedButtonBackground () {
    return activeBorderedButtonBackground;
  }

  public Border getMenuBorder () {
    return menuBorder;
  }

  public Border getDefaultBorder () {
    return defaultBorder;
  }

  public Font getItalic () {
    return ITALIC;
  }

  public Font getLight () {
    return LIGHT;
  }

  public Font getBold () {
    return BOLD;
  }

  public Font getRegular () {
    return REGULAR;
  }

  public Font getThin () {
    return THIN;
  }

  public Font getThinItalic () {
    return THIN_ITALIC;
  }

  private static Font getFont (String fileName) {
    try {
      return Font.createFont (Font.TRUETYPE_FONT, new FileInputStream (new File ("../fonts/" + fileName))).deriveFont (14f);
    }
    catch (IOException e) {
      System.out.println (fileName + " not found. set font directory in GUITheme class, getFont (), line 129");
      return null;
    }
    catch (FontFormatException e) {
      System.out.println (fileName + " FontFormatException occurred");
      return null;
    }
  }

  private GUITheme (Color inactiveTextbox, Color activeTextbox, Color textboxText, Color card, Color menuSelectionText, Color menuSelectionBackground,
                    Color menuDisabledText, Color borderlessButtonText, Color inactiveBorderlessButtonBackground,
                    Color activeBorderlessButtonBackground, Color borderedButtonText, Color inactiveBorderedButtonBackground, Color activeBorderedButtonBackground) {

    this.inactiveTextbox = inactiveTextbox;
    this.activeTextbox = activeTextbox;
    this.textboxText = textboxText;
    this.card = card;
    this.menuSelectionText = menuSelectionText;
    this.menuSelectionBackground = menuSelectionBackground;
    this.menuDisabledText = menuDisabledText;
    this.borderlessButtonText = borderlessButtonText;
    this.inactiveBorderlessButtonBackground = inactiveBorderlessButtonBackground;
    this.activeBorderlessButtonBackground = activeBorderlessButtonBackground;
    this.borderedButtonText = borderedButtonText;
    this.inactiveBorderedButtonBackground = inactiveBorderedButtonBackground;
    this.activeBorderedButtonBackground = activeBorderedButtonBackground;
  }
}

class MaterialButtonUI extends BasicButtonUI {
  
  @Override
  public void installUI (JComponent c) {
    super.installUI(c);
    AbstractButton button = (AbstractButton) c;
    button.setOpaque (false);
    button.setBorder (BorderFactory.createEmptyBorder (7, 17, 7, 17));
  }
  
  @Override
  public void paint (Graphics g, JComponent c) {
    AbstractButton b = (AbstractButton) c;
    
    paintBackground (g, b);
    super.paint(g, c);
  }
  
  private void paintBackground (final Graphics g, final JComponent c) {
    Dimension size = c.getSize();
    Graphics2D g2 = (Graphics2D) g;
    
    g2.addRenderingHints (new RenderingHints (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
    
    g.setColor (c.getBackground ());
    g.fillRoundRect (0, 0, size.width, size.height, 7, 7);//button
  }
}

class MaterialLookAndFeel extends MetalLookAndFeel {
  
  @Override
  public String getName () {
    return "Material Design";
  }
  
  @Override
  public String getID () {
    return "Material Design";
  }
  
  @Override
  public String getDescription () {
    return "A Material Design inspired Look and Feel by atharva washimkar";
  }
  
  @Override 
  public boolean getSupportsWindowDecorations () {
    return true;
  }
  
  @Override
  public boolean isNativeLookAndFeel () {
    return false;
  }
  
  @Override
  public boolean isSupportedLookAndFeel () {
    return true;
  }
  
  protected void initComponentDefaults (UIDefaults table) {
    super.initComponentDefaults (table);
  }
  
  public MaterialLookAndFeel (GUITheme theme) {
    super ();
    
    UIManager.put ("Button.font", theme.getBold ());
    UIManager.put("RadioButton.font", theme.getLight ());
    UIManager.put("CheckBox.font", theme.getLight ());
    UIManager.put("ComboBox.font", theme.getLight ());
    UIManager.put("Label.font", theme.getLight ());
    UIManager.put("MenuBar.font", theme.getBold ());
    UIManager.put("MenuItem.font", theme.getRegular ());
    UIManager.put("Menu.font", theme.getBold ());
    UIManager.put("OptionPane.font", theme.getLight ());
    UIManager.put("Panel.font", theme.getLight ());
    UIManager.put("ScrollPane.font", theme.getLight ());
    UIManager.put("Table.font", theme.getLight ());
    UIManager.put("TableHeader.font", theme.getLight ());
    UIManager.put("TextField.font", theme.getLight ());
    UIManager.put("TextArea.font", theme.getRegular ());
    
    UIManager.put ("Panel.background", theme.getCard ());
    UIManager.put ("Panel.border", BorderFactory.createEmptyBorder ());
    
    UIManager.put ("MenuItem.background", theme.getCard ());
    UIManager.put ("MenuItem.border", BorderFactory.createEmptyBorder ());
    UIManager.put ("MenuItem.disabledForeground", theme.getMenuDisabledText ());
    UIManager.put ("MenuItem.selectionBackground", theme.getMenuSelectionBackground ());
    UIManager.put("MenuItem.selectionForeground", theme.getMenuSelectionText ());
    UIManager.put ("MenuItem.foreground", theme.getMenuSelectionText ());
    
    UIManager.put("PopupMenu.border", BorderFactory.createLineBorder (theme.getMenuSelectionBackground (), 1));
    UIManager.put("PopupMenu.background", theme.getCard ());
    UIManager.put("Menu.border", BorderFactory.createEmptyBorder ());
    UIManager.put("Menu.selectionBackground", theme.getMenuSelectionBackground ());
    UIManager.put("Menu.selectionForeground", theme.getMenuSelectionText ());
    UIManager.put("Menu.disabledForeground", theme.getMenuDisabledText ());
    UIManager.put ("Menu.background", theme.getCard ());
    UIManager.put("Menu.foreground", theme.getMenuSelectionText ());
    UIManager.put("Menu.opaque", true);
    UIManager.put("Menu.menuPopupOffsetY", 10);
    
    UIManager.put("MenuBar.background", theme.getCard ());
    UIManager.put ("MenuBar.border", theme.getMenuBorder ());
    
    UIManager.put ("SplitPane.border", BorderFactory.createEmptyBorder ());
    UIManager.put ("SplitPane.background", theme.getCard ());
    UIManager.put ("SplitPane.dividerSize", 5);
    UIManager.put ("SplitPaneDivider.border", BorderFactory.createEmptyBorder ());
    
    UIManager.put ("ScrollPane.background", theme.getCard ());
    UIManager.put ("ScrollPane.border", BorderFactory.createEmptyBorder ());
    
    UIManager.put ("TextArea.background", theme.getInactiveTextbox ());
    UIManager.put ("TextArea.border", BorderFactory.createEmptyBorder ());
    UIManager.put ("TextArea.foreground", theme.getTextboxText ());
    
    UIManager.put ("OptionPane.background", theme.getCard ());
    UIManager.put ("OptionPane.border", theme.getDefaultBorder ());
    
    UIManager.put ("Button.background", theme.getInactiveBorderlessButtonBackground ());
    UIManager.put ("Button.foreground", theme.getBorderlessButtonText ());
    UIManager.put ("Button.highlight", theme.getActiveBorderlessButtonBackground ());
    UIManager.put ("Button.border", theme.getDefaultBorder ());
  }
}

class MaterialUIMovement {
  
  private Color fadeColor; // color to fade to
  private int steps; // number of steps to fade into fadeColor
  private int interval; // timer interval
  private Map <Color, List <Color>> backgroundColors = new HashMap <Color, List <Color>> ();
  
  private List <Color> getColors (Color background)
  {
    List <Color> o = backgroundColors.get (background);
    
    if (o != null) {
      return o;
    }
    
    List <Color> colors = new ArrayList <Color> (steps + 1);
    colors.add (background);
    
    int rDelta = (background.getRed () - fadeColor.getRed ()) / steps;
    int gDelta = (background.getGreen () - fadeColor.getGreen ()) / steps;
    int bDelta = (background.getBlue () - fadeColor.getBlue ()) / steps;
    int aDelta = (background.getAlpha () - fadeColor.getAlpha ()) / steps;
    
    for (int i = 1; i < steps; i++) {
      int rValue = background.getRed () - (i * rDelta);
      int gValue = background.getGreen () - (i * gDelta);
      int bValue = background.getBlue () - (i * bDelta);
      int aValue = background.getAlpha () - (i * aDelta);
      
      colors.add (new Color (rValue, gValue, bValue, aValue));
    }
    
    colors.add (fadeColor);
    backgroundColors.put (background, colors);
    
    return colors;
  }
  
  public MaterialUIMovement (Color fadeColor, int steps, int interval)
  {
    this.fadeColor = fadeColor;
    this.steps = steps;
    this.interval = interval;
  }
  
  public MaterialUIMovement add (JComponent component)
  {
    List <Color> colors = getColors (component.getBackground ());
    new MaterialUITimer (colors, component, interval);
    
    return this;
  }
  
  private class MaterialUITimer implements MouseListener /*, FocusListener*/, ActionListener {
    
    private List <Color> colors;
    private JComponent component;
    private Timer timer;
    private int alpha;
    private int increment;
    
    public void mousePressed (MouseEvent me) {
      alpha = steps;
      increment = -1;
      timer.start ();
      
      alpha = 0;
      increment = 1;
      timer.start ();
    }
    
    public void mouseReleased (MouseEvent me) {
      
    }
    
    public void mouseClicked (MouseEvent me) {
      
    }
    
    public void mouseExited (MouseEvent me) {
      alpha = steps;
      increment = -1;
      
      timer.start ();
    }
    
    public void mouseEntered (MouseEvent me) {
      alpha = 0;
      increment = 1;
      
      timer.start ();
    }
    
    public void actionPerformed(ActionEvent e) {
      alpha += increment;
      
      component.setBackground (colors.get (alpha));
      
      if (alpha == steps || alpha == 0) {
        timer.stop ();
      }
    }
    
    MaterialUITimer (List <Color> colors, JComponent component, int interval) {
      this.colors = colors;
      this.component = component;
      
      component.addMouseListener (this);
      timer = new Timer (interval, this);
    }
  }
}
// </editor-fold>