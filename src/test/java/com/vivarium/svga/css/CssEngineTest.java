package com.vivarium.svga.css;

import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by neurons on 7/31/14.
 */
public class CssEngineTest {

    @Test
    public void testClassSelector(){
        CssEngine engine = new CssEngine();
        engine.init(".test{color:black}");
        Map<String,String> attributes = new HashMap<String, String>();
        attributes.put("class", "test");
        engine.getAttributes("rec", attributes);

        Assert.assertEquals("black", attributes.get("color"));
    }

    @Test
    public void testClassSelectorNegative(){
        CssEngine engine = new CssEngine();
        engine.init(".test{color:black}");
        Map<String,String> attributes = new HashMap<String, String>();
        attributes.put("class", "test1");
        engine.getAttributes("rec", attributes);

        Assert.assertEquals(null, attributes.get("color"));
    }

    @Test
    public void testClassSelectorNegative2(){
        CssEngine engine = new CssEngine();
        engine.init(".test{color:black}");
        Map<String,String> attributes = new HashMap<String, String>();
        engine.getAttributes("rec", attributes);

        Assert.assertEquals(null, attributes.get("color"));
    }


    @Test
    public void testIdSelector(){
        CssEngine engine = new CssEngine();
        engine.init("#test{color:black}");
        Map<String,String> attributes = new HashMap<String, String>();
        attributes.put("id", "test");
        engine.getAttributes("rec", attributes);

        Assert.assertEquals("black", attributes.get("color"));
    }
}
