package com.vivarium.svga.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by neurons on 8/18/14.
 */
public class TransformTest {
    @Test
    public void testNoTransform(){
        Unit x = new Unit(1);
        Unit y = new Unit(2);

        Unit[] result = Transform.transformPoint(x, y, Transform.NO_TRANSFORM);
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.length);

        Assert.assertEquals(1, result[0].getValue(), Transform.DELTA);
        Assert.assertEquals(2, result[1].getValue(), Transform.DELTA);
    }

    @Test
    public void testTranslateTransform(){
        Unit x = new Unit(1);
        Unit y = new Unit(2);

        Unit[] result = Transform.transformPoint(x, y, Transform.translate(new Unit(1), new Unit(2)));

        Assert.assertEquals(2, result[0].getValue(), Transform.DELTA);
        Assert.assertEquals(4, result[1].getValue(), Transform.DELTA);
    }

    @Test
    public void testScaleTransform(){
        Unit x = new Unit(1);
        Unit y = new Unit(2);

        Unit[] result = Transform.transformPoint(x, y, Transform.scale(3,9));

        Assert.assertEquals(3, result[0].getValue(), Transform.DELTA);
        Assert.assertEquals(18, result[1].getValue(), Transform.DELTA);
    }

    @Test
    public void testAddTransform(){
        Unit x = new Unit(1);
        Unit y = new Unit(2);

        Unit[] result = Transform.transformPoint(x, y,
                Transform.addTransform(
                        Transform.scale(3,9),
                        Transform.translate(new Unit(1), new Unit(2))));

        Assert.assertEquals(4, result[0].getValue(), Transform.DELTA);
        Assert.assertEquals(20, result[1].getValue(), Transform.DELTA);

        result = Transform.transformPoint(x, y,
                Transform.addTransform(
                        Transform.translate(new Unit(1), new Unit(2)),
                        Transform.scale(3,9)
                        ));

        Assert.assertEquals(6, result[0].getValue(), Transform.DELTA);
        Assert.assertEquals(36, result[1].getValue(), Transform.DELTA);
    }
}
