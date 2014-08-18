package com.vivarium.svga.parser;

import com.vivarium.svga.model.*;
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
    private Transform transform = null;

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts1) {
        if("svg".equalsIgnoreCase(qName)){
            Unit width  = getUnit( getAttribute("width", atts1, "0") ).toPixels(renderer.getDpi());
            Unit height = getUnit( getAttribute("height", atts1, "0") ).toPixels(renderer.getDpi());
            PreserveAspectRatio preserveAspectRatio = new PreserveAspectRatio();
            preserveAspectRatio.setMeetOrSlice(PreserveAspectRatio.MeetOrSlice.SLICE);
            preserveAspectRatio.setxAlign(PreserveAspectRatio.Align.MID);
            preserveAspectRatio.setyAlign(PreserveAspectRatio.Align.MID);
            transform = TransformUtils.getViewportTransform(
                    new Unit(renderer.getWidthInPixels()),
                    new Unit(renderer.getHeightInPixels()),
                    width,
                    height,
                    preserveAspectRatio
            );
        }
        if("rect".equalsIgnoreCase(qName)){
            Unit x = getUnit( getAttribute("x", atts1, "0"));
            Unit y = getUnit( getAttribute("y", atts1, "0"));
            Unit width = getUnit( getAttribute("width", atts1, "0"));
            Unit height = getUnit( getAttribute("height", atts1, "0"));
            renderer.drawRect(x, y, width, height, null, null, transform, null);
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

    private Unit getUnit(String s) {
        float value = 0;
        UnitType type = UnitType.SCALAR;
        if (s.endsWith("px")) {
            value = Float.parseFloat(s.substring(0, s.length() - 2));
        } else if (s.endsWith("cm")) {
            type = UnitType.CM;
            value = Float.parseFloat(s.substring(0, s.length() - 2));
        }else{
            value = Float.parseFloat(s);
        }


        return new Unit(value, type);
    }
        private Renderer renderer;
}
