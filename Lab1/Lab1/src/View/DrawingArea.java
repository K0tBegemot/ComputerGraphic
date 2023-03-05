package View;

import Control.PaintAreaMouseListener;
import Model.MainFrameModel;
import Model.Shape.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.colorchooser.ColorSelectionModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DrawingArea extends JComponent
{
    BufferedImage drawingArea;
    Graphics2D drawingAreaGraphics;
    MainFrameModel model;
    BufferedImage backgroundImage;
    Graphics2D backgroundImageGraphics;

    DrawingArea(MainFrameModel model)
    {
        this.model = model;
        Border blackLineBorder = BorderFactory.createLineBorder(Color.BLACK);
        drawingArea = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_ARGB);/////////////ARGB
        drawingAreaGraphics = (Graphics2D) drawingArea.createGraphics();
        backgroundImage = null;
        backgroundImageGraphics = null;
        setBorder(blackLineBorder);
        addMouseListener(new PaintAreaMouseListener(model));
        repaint();
    }

    public void setImage(BufferedImage drawingArea) {
        backgroundImage = drawingArea;
        backgroundImageGraphics = drawingArea.createGraphics();
        this.drawingArea = new BufferedImage(backgroundImage.getWidth(), backgroundImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.drawingAreaGraphics = this.drawingArea.createGraphics();
        repaint();
    }

    public BufferedImage getImage() {
        return drawingArea;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        paintBackground();
        for(GraphicShape i : model.getImage())
        {
            paintGraphicShape(i);
        }
        GraphicShape tmp = model.getTmpShape();
        if(model.isTmpShapeVisible()) {
            tmp.setLineColor(model.getCurrentDrawingColor());
            tmp.setCurrentLineThickness(model.getCurrentLineThickness());
            tmp.setClosedCurve(model.isClosedDottedCurve());
            tmp.setRadius(model.getCurrentRadius());
            tmp.setNumberOfVertices(model.getCurrentNumberOfVertices());
            tmp.setRotationAngleInDegree(model.getRotationAngle());
            tmp.setType(model.getType());
            paintGraphicShape(tmp);
        }
        ((Graphics2D)g).drawImage(drawingArea, 0, 0, null);
    }

    void paintBackground()
    {
        if(backgroundImage == null) {
            whiteBackground();
        }else {
            drawingAreaGraphics.drawImage(backgroundImage, 0, 0, null);
        }
    }

    public void eraseBackground()
    {
        backgroundImage = null;
        backgroundImageGraphics = null;
        repaint();
    }

    public void whiteBackground()
    {
        drawingAreaGraphics.setColor(Color.WHITE);
        drawingAreaGraphics.fillRect(0, 0, drawingArea.getWidth(), drawingArea.getHeight());
    }

    void paintGraphicShape(GraphicShape shape)
    {
        if(shape instanceof DottedCurve)
        {
            List<Dot> arrayOfDots = ((DottedCurve)shape).getArrayOfDots();
            int indexLength = arrayOfDots.size() - 1;
            if(shape.isClosedCurve() && arrayOfDots.size() > 2)
            {
                indexLength = arrayOfDots.size();
            }
            for(int i = 0; i < indexLength; i++)
            {
                Dot dot1 = arrayOfDots.get(i % arrayOfDots.size());
                Dot dot2 = arrayOfDots.get((i + 1) % arrayOfDots.size());
                paintLine(dot1.xCoord, dot1.yCoord, dot2.xCoord, dot2.yCoord, shape.getLineColor(), shape.getCurrentLineThickness());
            }
        }
        if(shape instanceof SpanFill)
        {
            SpanFill fill = (SpanFill) shape;
            spanFill(fill.getStartDot(), fill.getLineColor());
        }
        if(shape instanceof RegularPolygon)
        {
            RegularPolygon polygon = (RegularPolygon) shape;
            if(shape.getType() == GShape.TYPE.POLY)
            {
                paintRegularPolygon(polygon.getCenter(), polygon.getRadius(),
                        polygon.getNumberOfVertices(), polygon.getRotationAngleInDegree(), polygon.getLineColor(), polygon.getCurrentLineThickness());
            } else if (shape.getType() == GShape.TYPE.STAR) {
                paintStar(polygon.getCenter(), polygon.getRadius(), polygon.getNumberOfVertices(),
                        polygon.getRotationAngleInDegree(), polygon.getLineColor(), polygon.getCurrentLineThickness());
            }
        }
    }

    private class Line
    {
        Line(int dot1x, int dot1y, int dot2x, int dot2y)
        {
            this.firstDot = new Dot(dot1x, dot1y);
            int deltaX = dot2x - dot1x;
            int deltaY = dot2y - dot1y;
            this.secondRelativeDot = new Dot(deltaX, deltaY);
            this.originalOctet = getNumberOfOctet(deltaX, deltaY);
            this.currentOctet = originalOctet;
        }
        Line(int dot2x, int dot2y, int dot1x, int dot1y, int originalOctet, int currentOctet)
        {
            this.secondRelativeDot = new Dot(dot2x, dot2y);
            this.firstDot = new Dot(dot1x, dot1y);
            this.originalOctet = originalOctet;
            this.currentOctet = currentOctet;
        }
        public Dot secondRelativeDot;
        public Dot firstDot; //shift of actual coord
        private int originalOctet;
        public int currentOctet;
        public int getOriginalOctet() {
            return originalOctet;
        }
        public static int getNumberOfOctet(int deltaX, int deltaY)
        {
            double tan = Math.abs(((double) deltaY) / ((double) deltaX));
            if(deltaY >= 0 && deltaX >= 0)
            {
                if(tan < 1)
                {
                    return 0;
                }else {
                    return 1;
                }
            } else if (deltaY >= 0 && deltaX < 0) { //=////////////////////////////////////////////////////////////////////////////////////
                if(tan < 1)
                {
                    return 3;
                }else {
                    return 2;
                }
            } else if (deltaY < 0 && deltaX <= 0) {
                if(tan < 1)
                {
                    return 4;
                }else {
                    return 5;
                }
            } else if (deltaY < 0 && deltaX > 0) {
                if(tan < 1)
                {
                    return 7;
                }else {
                    return 6;
                }
            }
            return -1;
        }

        public Line getCorrespondingDotInZeroOctet()
        {
            int originalOctet = this.originalOctet;
            Line retLine = null;
            switch(originalOctet)
            {
                case 0:
                {
                    retLine = this;
                    break;
                }
                case 1:
                {
                    retLine = new Line(this.secondRelativeDot.yCoord, this.secondRelativeDot.xCoord,
                            this.firstDot.xCoord, this.firstDot.yCoord, this.originalOctet, 0);
                    break;
                }
                case 2:
                {
                    retLine = new Line(this.secondRelativeDot.yCoord, this.secondRelativeDot.xCoord * (-1),
                            this.firstDot.xCoord, this.firstDot.yCoord, this.originalOctet, 0);
                    break;
                }
                case 3:
                {
                    retLine = new Line(this.secondRelativeDot.xCoord * (-1), this.secondRelativeDot.yCoord,
                            this.firstDot.xCoord, this.firstDot.yCoord, this.originalOctet, 0);
                    break;
                }
                case 4:
                {
                    retLine = new Line(this.secondRelativeDot.xCoord * (-1), this.secondRelativeDot.yCoord * (-1),
                            this.firstDot.xCoord, this.firstDot.yCoord, this.originalOctet, 0);
                    break;
                }
                case 5:
                {
                    retLine = new Line(this.secondRelativeDot.yCoord * (-1),this.secondRelativeDot.xCoord * (-1),
                            this.firstDot.xCoord, this.firstDot.yCoord, this.originalOctet, 0);
                    break;
                }
                case 6:
                {
                    retLine = new Line(this.secondRelativeDot.yCoord * (-1), this.secondRelativeDot.xCoord,
                            this.firstDot.xCoord, this.firstDot.yCoord, this.originalOctet, 0);
                    break;
                }
                case 7:
                {
                    retLine = new Line(this.secondRelativeDot.xCoord, this.secondRelativeDot.yCoord * (-1),
                            this.firstDot.xCoord, this.firstDot.yCoord, this.originalOctet, 0);
                    break;
                }
            }
            return retLine;
        }

        public static Dot getCorrespondingDotFromZeroOctet(int dot2x, int dot2y, int dot1x, int dot1y, int originalOctet)
        {
            Dot retDot = new Dot(0, 0);
            switch(originalOctet)
            {
                case 0:
                {
                    retDot.xCoord = dot2x + dot1x;
                    retDot.yCoord = dot2y + dot1y;
                    break;
                }
                case 1:
                {
                    retDot.xCoord = dot2y + dot1x;
                    retDot.yCoord = dot2x + dot1y;
                    break;
                }
                case 2:
                {
                    retDot.xCoord = dot2y * (-1) + dot1x;
                    retDot.yCoord = dot2x + dot1y;
                    break;
                }
                case 3:
                {
                    retDot.xCoord = dot2x * (-1) + dot1x;
                    retDot.yCoord = dot2y + dot1y;
                    break;
                }
                case 4:
                {
                    retDot.xCoord = dot2x * (-1) + dot1x;
                    retDot.yCoord = dot2y * (-1) + dot1y;
                    break;
                }
                case 5:
                {
                    retDot.xCoord = dot2y * (-1) + dot1x;
                    retDot.yCoord = dot2x * (-1) + dot1y;
                    break;
                }
                case 6:
                {
                    retDot.xCoord = dot2y + dot1x;
                    retDot.yCoord = dot2x * (-1) + dot1y;
                    break;
                }
                case 7:
                {
                    retDot.xCoord = dot2x + dot1x;
                    retDot.yCoord = dot2y * (-1) + dot1y;
                    break;
                }
            }
            return retDot;
        }
    }

    void gracefullyDrawLine(int dot1x, int dot1y, int dot2x, int dot2y, Color lineColor)
    {
        Line line = new Line(dot1x, dot1y, dot2x, dot2y);
        int error = 0;
        line = line.getCorrespondingDotInZeroOctet();
        int deltaX = Math.abs(line.secondRelativeDot.xCoord);
        int deltaY = Math.abs(line.secondRelativeDot.yCoord);
        int errorStep = deltaY;
        int j = 0;
        int biasX = line.firstDot.xCoord;
        int biasY = line.firstDot.yCoord;
        for (int i = 0; i <= line.secondRelativeDot.xCoord; i++) {
            Dot absDot = Line.getCorrespondingDotFromZeroOctet(i, j, biasX, biasY, line.getOriginalOctet());
            drawingArea.setRGB(absDot.xCoord, absDot.yCoord, lineColor.getRGB());
            error += errorStep;
            if (error > deltaX) {
                j += 1;
                error -= deltaX;
            }
        }
    }

    boolean isDotLiesOnPanel(int dotx, int doty)
    {
        if(dotx < 0 || dotx >= drawingArea.getWidth())
        {
            return false;
        }
        if(doty < 0 || doty >= drawingArea.getHeight())
        {
            return false;
        }
        return true;
    }

    Dot getEdgePoint(int panelDotX, int panelDotY, int outDotX, int outDotY)
    {
        int deltaX = -1;
        boolean isDeltaX = false;
        int deltaY = -1;
        boolean isDeltaY = false;
        if(outDotX >=  drawingArea.getWidth())
        {
            deltaX = drawingArea.getWidth() - 1 - panelDotX;
            isDeltaX = true;
        } else if (outDotX < 0) {
            deltaX = (-1) * panelDotX;
            isDeltaX = true;
        }
        if(outDotY >= drawingArea.getHeight())
        {
            deltaY = drawingArea.getHeight() - 1 - panelDotY;
            isDeltaY = true;
        } else if (outDotY < 0) {
            deltaY =  (-1) * panelDotY;
            isDeltaY = true;
        }
        if(!isDeltaX && !isDeltaY)
        {
            System.err.println("Strange things happening\n");
            return new Dot(outDotX, outDotY);
        } else if (!isDeltaX && isDeltaY) {
            deltaX = (deltaY * (outDotX - panelDotX)) / (outDotY - panelDotY);
        } else if (isDeltaX && !isDeltaY) {
            deltaY = ((outDotY - panelDotY) * deltaX ) / (outDotX - panelDotX);
        } else{
            if(Math.abs(deltaX) <= Math.abs(deltaY))
            {
                deltaY = ((outDotY - panelDotY) * deltaX) / (outDotX - panelDotX);
            }else{
                deltaX = (deltaY * (outDotX - panelDotX)) / (outDotY - panelDotY);
            }
        }
        return new Dot(panelDotX + deltaX, panelDotY + deltaY);
    }

    void paintLine(int dot1x, int dot1y, int dot2x, int dot2y, Color lineColor, int lineThickness)
    {
        int tmpDot1x = -1;
        int tmpDot1y = -1;
        int tmpDot2x = -1;
        int tmpDot2y = -1;
        boolean dot1lies = isDotLiesOnPanel(dot1x, dot1y);
        boolean dot2lies = isDotLiesOnPanel(dot2x, dot2y);
        if(dot1lies && dot2lies)
        {
            tmpDot1x = dot1x;
            tmpDot1y = dot1y;
            tmpDot2x = dot2x;
            tmpDot2y = dot2y;
            System.err.printf("1) %d %d %d %d\n", tmpDot1x, tmpDot1y, tmpDot2x, tmpDot2y);
        } else if (dot1lies && !dot2lies) {
            Dot edgeDot = getEdgePoint(dot1x, dot1y, dot2x, dot2y);
            tmpDot1x = dot1x;
            tmpDot1y = dot1y;
            tmpDot2x = edgeDot.xCoord;
            tmpDot2y = edgeDot.yCoord;
            System.err.printf("2) %d %d %d %d\n", tmpDot1x, tmpDot1y, tmpDot2x, tmpDot2y);
        } else if (!dot1lies && dot2lies) {
            Dot edgeDot = getEdgePoint(dot2x, dot2y, dot1x, dot1y);
            tmpDot1x = edgeDot.xCoord;
            tmpDot1y = edgeDot.yCoord;
            tmpDot2x = dot2x;
            tmpDot2y = dot2y;
            System.err.printf("3) %d %d %d %d\n", tmpDot1x, tmpDot1y, tmpDot2x, tmpDot2y);
        }else {
            return;
        }
        if(lineThickness == 1) {
            //gracefullyDrawLine(tmpDot1x, tmpDot1y, tmpDot2x, tmpDot2y, lineColor);
            dumblyDrawLine(tmpDot1x, tmpDot1y, tmpDot2x, tmpDot2y, lineColor);
        }else {
            drawingAreaGraphics.setColor(lineColor);
            drawingAreaGraphics.setStroke(new BasicStroke(lineThickness));
            drawingAreaGraphics.drawLine(dot1x, dot1y, dot2x, dot2y);
        }
    }

    void dumblyDrawLine(int dot1x, int dot1y, int dot2x, int dot2y, Color lineColor)
    {
        int deltaX = dot2x - dot1x;
        int deltaY = dot2y - dot1y;
        int octetNumber = Line.getNumberOfOctet(deltaX, deltaY);
        switch(octetNumber)
        {
            case 0 ->{
                dumblyDrawLine0Octet(dot1x, dot1y, dot2x, dot2y, lineColor);
            }
            case 1 -> {
                dumblyDrawLine1Octet(dot1x, dot1y, dot2x, dot2y, lineColor);
            }
            case 2 -> {
                dumblyDrawLine2Octet(dot1x, dot1y, dot2x, dot2y, lineColor);
            }
            case 3 -> {
                dumblyDrawLine3Octet(dot1x, dot1y, dot2x, dot2y, lineColor);
            }
            case 4 -> {
                dumblyDrawLine4Octet(dot1x, dot1y, dot2x, dot2y, lineColor);
            }
            case 5 -> {
                dumblyDrawLine5Octet(dot1x, dot1y, dot2x, dot2y, lineColor);
            }
            case 6 -> {
                dumblyDrawLine6Octet(dot1x, dot1y, dot2x, dot2y, lineColor);
            }
            case 7 -> {
                dumblyDrawLine7Octet(dot1x, dot1y, dot2x, dot2y, lineColor);
            }
        }
    }

    void dumblyDrawLine0Octet(int dot1x, int dot1y, int dot2x, int dot2y, Color lineColor)
    {
        int deltaY = dot2y - dot1y;
        int deltaX = dot2x - dot1x;
        int errorStep = deltaY;
        int j = dot1y;
        int error = 0;
        for (int i = dot1x; i <= dot2x; i++) {
            drawingArea.setRGB(i, j, lineColor.getRGB());
            error += errorStep;
            if (error > deltaX) {
                j += 1;
                error -= deltaX;
            }
        }
    }

    void dumblyDrawLine1Octet(int dot1x, int dot1y, int dot2x, int dot2y, Color lineColor)
    {
        int deltaY = dot2y - dot1y;
        int deltaX = dot2x - dot1x;
        int errorStep = deltaX;
        int j = dot1x;
        int error = 0;
        for (int i = dot1y; i <= dot2y; i++) {
            drawingArea.setRGB(j, i, lineColor.getRGB());
            error += errorStep;
            if (error > deltaY) {
                j += 1;
                error -= deltaY;
            }
        }
    }

    void dumblyDrawLine2Octet(int dot1x, int dot1y, int dot2x, int dot2y, Color lineColor)
    {
        int deltaY = dot2y - dot1y;
        int deltaX = dot1x - dot2x;
        int errorStep = deltaX;
        int j = dot1x;
        int error = 0;
        for (int i = dot1y; i <= dot2y; i++) {
            drawingArea.setRGB(j, i, lineColor.getRGB());
            error += errorStep;
            if (error > deltaY) {
                j -= 1;
                error -= deltaY;
            }
        }
    }

    void dumblyDrawLine3Octet(int dot1x, int dot1y, int dot2x, int dot2y, Color lineColor)
    {
        int deltaY = dot2y - dot1y;
        int deltaX = dot1x - dot2x;
        int errorStep = deltaY;
        int j = dot1y;
        int error = 0;
        for (int i = dot1x; i >= dot2x; i--) {
            drawingArea.setRGB(i, j, lineColor.getRGB());
            error += errorStep;
            if (error > deltaX) {
                j += 1;
                error -= deltaX;
            }
        }
    }

    void dumblyDrawLine4Octet(int dot1x, int dot1y, int dot2x, int dot2y, Color lineColor)
    {
        int deltaY = dot1y - dot2y;
        int deltaX = dot1x - dot2x;
        int errorStep = deltaY;
        int j = dot1y;
        int error = 0;
        for (int i = dot1x; i >= dot2x; i--) {
            drawingArea.setRGB(i, j, lineColor.getRGB());
            error += errorStep;
            if (error > deltaX) {
                j -= 1;
                error -= deltaX;
            }
        }
    }

    void dumblyDrawLine5Octet(int dot1x, int dot1y, int dot2x, int dot2y, Color lineColor)
    {
        int deltaY = dot1y - dot2y;
        int deltaX = dot1x - dot2x;
        int errorStep = deltaX;
        int j = dot1x;
        int error = 0;
        for (int i = dot1y; i >= dot2y; i--) {
            drawingArea.setRGB(j, i, lineColor.getRGB());
            error += errorStep;
            if (error > deltaY) {
                j -= 1;
                error -= deltaY;
            }
        }
    }

    void dumblyDrawLine6Octet(int dot1x, int dot1y, int dot2x, int dot2y, Color lineColor)
    {
        int deltaY = dot1y - dot2y;
        int deltaX = dot2x - dot1x;
        int errorStep = deltaX;
        int j = dot1x;
        int error = 0;
        for (int i = dot1y; i >= dot2y; i--) {
            drawingArea.setRGB(j, i, lineColor.getRGB());
            error += errorStep;
            if (error > deltaY) {
                j += 1;
                error -= deltaY;
            }
        }
    }

    void dumblyDrawLine7Octet(int dot1x, int dot1y, int dot2x, int dot2y, Color lineColor)
    {
        int deltaY = dot1y - dot2y;
        int deltaX = dot2x - dot1x;
        int errorStep = deltaY;
        int j = dot1y;
        int error = 0;
        for (int i = dot1x; i <= dot2x; i++) {
            drawingArea.setRGB(i, j, lineColor.getRGB());
            error += errorStep;
            if (error > deltaX) {
                j -= 1;
                error -= deltaX;
            }
        }
    }
    void paintRegularPolygon(Dot centerDot, int radius, int numberOfVertices, int rotationAngleInDegree, Color color, int lineThickness)
    {
        double angleStep = (double)(360) / numberOfVertices;
        double rotationAngleInRadian = ((rotationAngleInDegree * Math.PI) / 180);
        int firstXCoord = (int)(Math.cos(rotationAngleInRadian) * radius) + centerDot.xCoord;
        int firstYCoord = (int)(Math.sin(rotationAngleInRadian) * radius) + centerDot.yCoord;
        int tmp1XCoord = firstXCoord;
        int tmp1YCoord = firstYCoord;
        int startDegree = rotationAngleInDegree;
        int tmp2XCoord = -1;
        int tmp2YCoord = -1;
        for(int i = 1; i < numberOfVertices; i++)
        {
            rotationAngleInRadian = (((rotationAngleInDegree + i * angleStep) * Math.PI) / 180);
            tmp2XCoord = (int)(Math.cos(rotationAngleInRadian) * radius) + centerDot.xCoord;
            tmp2YCoord = (int)(Math.sin(rotationAngleInRadian) * radius) + centerDot.yCoord;
            paintLine(tmp1XCoord, tmp1YCoord, tmp2XCoord, tmp2YCoord, color, lineThickness);
            tmp1XCoord = tmp2XCoord;
            tmp1YCoord = tmp2YCoord;
        }
        tmp2XCoord = firstXCoord;
        tmp2YCoord = firstYCoord;
        paintLine(tmp1XCoord, tmp1YCoord, tmp2XCoord, tmp2YCoord, color, lineThickness);
    }

    void paintStar(Dot centerDot, int radius, int numberOfVertices, int rotationAngleInDegree, Color color, int lineThickness)
    {
        double angleStep = (double)(360) / numberOfVertices;
        double angleHalfStep = angleStep / 2;
        int halfRadius = radius / 2;
        double rotationAngleInRadian = (rotationAngleInDegree * Math.PI) / 180;
        int firstXCoord = (int)(Math.cos(rotationAngleInRadian) * radius) + centerDot.xCoord;
        int firstYCoord = (int)(Math.sin(rotationAngleInRadian) * radius) + centerDot.yCoord;
        int secondXCoord = -1;
        int secondYCoord = -1;
        int startXCoord = firstXCoord;
        int startYCoord = firstYCoord;
        for(int i = 1; i < numberOfVertices * 2; i+=2) //+=2
        {
            rotationAngleInRadian = ((rotationAngleInDegree + i * angleHalfStep) * Math.PI) / 180;
            secondXCoord = (int)(Math.cos(rotationAngleInRadian) * halfRadius) + centerDot.xCoord;
            secondYCoord = (int)(Math.sin(rotationAngleInRadian) * halfRadius) + centerDot.yCoord;
            paintLine(firstXCoord, firstYCoord, secondXCoord, secondYCoord, color, lineThickness);
            firstXCoord = secondXCoord;
            firstYCoord = secondYCoord;
                rotationAngleInRadian = ((rotationAngleInDegree + (i + 1) * angleHalfStep) * Math.PI) / 180;
                secondXCoord = (int) (Math.cos(rotationAngleInRadian) * radius) + centerDot.xCoord;
                secondYCoord = (int) (Math.sin(rotationAngleInRadian) * radius) + centerDot.yCoord;
                paintLine(firstXCoord, firstYCoord, secondXCoord, secondYCoord, color, lineThickness);
                firstXCoord = secondXCoord;
                firstYCoord = secondYCoord;
        }
    }

    private class SpanElement
    {
        public int startDotX;
        public int startDotY;
        public int endDotX;
        public int endDotY;
        SpanElement(int startDotX, int startDotY, int endDotX, int endDotY)
        {
            this.startDotX = startDotX;
            this.startDotY = startDotY;
            this.endDotX = endDotX;
            this.endDotY = endDotY;
        }
    }

    SpanElement getSpanFromDot(int startX, int startY, int startDotColor)
    {
        if(startX >= 0 && startX < drawingArea.getWidth() && startY >= 0 && startY < drawingArea.getHeight()) {
            int startSpanX = -1;
            int endSpanX = -1;
            for (int i = startX - 1; i > -1; i--) {
                int tpDotColor = drawingArea.getRGB(i, startY);
                if (tpDotColor != startDotColor) {
                    startSpanX = i + 1;
                    break;
                }
            }
            if (startSpanX == -1) {
                startSpanX = 0;
            }
            for (int i = startX + 1; i < drawingArea.getWidth(); i++) {
                int tpDotColor = drawingArea.getRGB(i, startY);
                if (tpDotColor != startDotColor) {
                    endSpanX = i - 1;
                    break;
                }
            }
            if (endSpanX == -1) {
                endSpanX = drawingArea.getWidth() - 1;
            }
            return new SpanElement(startSpanX, startY, endSpanX, startY);
        }
        return null;
    }

    boolean isDotInTheArray(List<SpanElement> arrayOfSpans, int currentDotX, int currentDotY)
    {
        boolean retValue = false;
        for(int i = 0; i < arrayOfSpans.size(); i++)
        {
            SpanElement element = arrayOfSpans.get(i);
            if((element.startDotY == currentDotY) &&
                    (element.startDotX <= currentDotX) && (element.endDotX >= currentDotX))
            {
                retValue = true;
                break;
            }
        }
        return retValue;
    }

    void spanFill(Dot startDot, Color color)
    {
        List<SpanElement> arrayOfSpans = new Vector<>();
        int startX = startDot.xCoord;
        int startY = startDot.yCoord;
        if(startX >= 0 && startX < drawingArea.getWidth() && startY >= 0 && startY < drawingArea.getHeight())
        {
            int startDotColor = drawingArea.getRGB(startX, startY);
            if (!isDotInTheArray(arrayOfSpans, startX, startY)) {
                SpanElement currentSpan = getSpanFromDot(startX, startY, startDotColor);
//                System.err.printf("%d %d %d %d\n", currentSpan.startDotX, currentSpan.startDotY,
//                        currentSpan.endDotX, currentSpan.endDotY);
                arrayOfSpans.add(currentSpan);
                while (arrayOfSpans.size() > 0) {
                    currentSpan = arrayOfSpans.remove(0);
//                    System.err.printf("%d %d %d %d\n", currentSpan.startDotX, currentSpan.startDotY,
//                            currentSpan.endDotX, currentSpan.endDotY);
                    int tmpY = currentSpan.startDotY;
                    for (int tmpX = currentSpan.startDotX; tmpX <= currentSpan.endDotX; tmpX++)
                    {
                        drawingArea.setRGB(tmpX, tmpY, color.getRGB());
                    }
                    for (int tmpX = currentSpan.startDotX; tmpX <= currentSpan.endDotX; tmpX++)
                    {
                        if (tmpY - 1 >= 0) {
                            if (drawingArea.getRGB(tmpX, tmpY - 1) == startDotColor && !isDotInTheArray(arrayOfSpans, tmpX, tmpY - 1)) {
                                arrayOfSpans.add(getSpanFromDot(tmpX, tmpY - 1, startDotColor));
                            }
                        }
                        if (tmpY + 1 < drawingArea.getHeight()) {
                            if (drawingArea.getRGB(tmpX, tmpY + 1) == startDotColor && !isDotInTheArray(arrayOfSpans, tmpX, tmpY + 1)) {
                                arrayOfSpans.add(getSpanFromDot(tmpX, tmpY + 1, startDotColor));
                            }
                        }
                    }
                }
            }
        }
        //System.err.println("AAAA");
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(drawingArea.getWidth(), drawingArea.getHeight());
    }
}
