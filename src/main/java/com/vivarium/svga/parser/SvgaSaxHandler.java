package com.vivarium.svga.parser;

import com.vivarium.svga.model.Renderer;
import com.vivarium.svga.model.Unit;
import com.vivarium.svga.model.UnitType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by neurons on 8/16/14.
 */
public class SvgaSaxHandler extends DefaultHandler {
    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts1) {
        if("rect".equalsIgnoreCase(qName)){
            Unit x = getUnit( getAttribute("x", atts1, "0"));
            Unit y = getUnit( getAttribute("y", atts1, "0"));
            Unit width = getUnit( getAttribute("width", atts1, "0"));
            Unit height = getUnit( getAttribute("height", atts1, "0"));
            renderer.drawRect(x, y, width, height, null, null, null, null);
        }
    }

    private String getAttribute(String attributeName, Attributes atts, String defaultValue){
        String result = defaultValue;
        for(int i = 0 ; i < atts.getLength() ; i++){
            if(atts.getQName(i).equalsIgnoreCase(attributeName)){
                result = atts.getValue(i);
                break;
            }
        }
        return result;
    }

    private Unit getUnit(String s){
        float value = 0;
        UnitType type = UnitType.SCALAR;
        if(s.endsWith("px")){
            value = Float.parseFloat(s.substring(0, s.length() - 2));
        }else{
            value = Float.parseFloat(s);
        }


        return new Unit(value, type);
    }
        private Renderer renderer;
}
