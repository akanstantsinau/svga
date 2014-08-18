package com.vivarium.svga.model;

/**
 * Created by neurons on 8/9/14.
 */
public interface Renderer {
    /**
     * Returns target device DPI.
     * @return DPI
     */
    public int getDpi();

    /**
     * Returns target device screen width in pixels.
     * @return
     */
    public int getWidthInPixels();

    /**
     * Returns target device screen height in pixels.
     * @return
     */
    public int getHeightInPixels();

    /**
     * Draws a rectangle which is axis-aligned with the current user coordinate system.
     * Rounded rectangles can be achieved by setting appropriate values for attributes ‘rx’ and ‘ry’.
     * @param x The x-axis coordinate of the side of the rectangle which has the smaller x-axis
     *          coordinate value in the current user coordinate system. If the attribute is not specified,
     *          the effect is as if a value of "0" were specified.
     * @param y The y-axis coordinate of the side of the rectangle which has the smaller y-axis
     *          coordinate value in the current user coordinate system. If the attribute is not specified,
     *          the effect is as if a value of "0" were specified.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param rx For rounded rectangles, the x-axis radius of the ellipse used to round off the corners of the rectangle.
     * @param ry For rounded rectangles, the y-axis radius of the ellipse used to round off the corners of the rectangle.
     * @param t Transform matrix
     * @param s Element style properties.
     */
    public void drawRect(Unit x, Unit y, Unit width,
                         Unit height, Unit rx, Unit ry, Transform t, Style s);

    /**
     * Draws a circle based on a center point and a radius.
     * @param cx The x-axis coordinate of the center of the circle.
     * @param cy The y-axis coordinate of the center of the circle.
     * @param r The radius of the circle.
     * @param t Transform matrix
     * @param s Element style properties.
     */
    public void drawCircle(Unit cx, Unit cy, Unit r, Transform t, Style s);

    /**
     * Draws  an ellipse which is axis-aligned with the current user coordinate system based on a center point and two radii.
     * @param cx The x-axis coordinate of the center of the ellipse.
     * @param cy The y-axis coordinate of the center of the ellipse.
     * @param rx The x-axis radius of the ellipse.
     * @param ry The y-axis radius of the ellipse.
     * @param t Transform matrix
     * @param s Element style properties.
     */
    public void drawEllipse(Unit cx, Unit cy, Unit rx, Unit ry, Transform t, Style s);

    /**
     * Draws a line segment that starts at one point and ends at another.
     * @param x1 The x-axis coordinate of the start of the line.
     * @param y1 The y-axis coordinate of the start of the line.
     * @param x2 The x-axis coordinate of the end of the line.
     * @param y2 The y-axis coordinate of the end of the line.
     * @param t Transform matrix
     * @param s Element style properties.
     */
    public void drawLine(Unit x1, Unit y1, Unit x2, Unit y2, Transform t, Style s);

    /**
     * Draws a set of connected straight line segments. Typically, ‘polyline’ elements define open shapes.
     * @param x Array of x-axis coordinates of polyline points.
     * @param y Array of x-axis coordinates of polyline points.
     * @param t Transform matrix
     * @param s Element style properties.
     */
    public void drawPolyline(Unit[] x, Unit[] y, Transform t, Style s);

    /**
     * Draws a closed shape consisting of a set of connected straight line segments.
     * @param x Array of x-axis coordinates of polygon points.
     * @param y Array of x-axis coordinates of polygon points.
     * @param t Transform matrix
     * @param s Element style properties.
     */
    public void drawPolygon(Unit[] x, Unit[] y, Transform t, Style s);
}
