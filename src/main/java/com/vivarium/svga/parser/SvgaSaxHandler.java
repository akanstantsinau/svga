package com.vivarium.svga.parser;

import com.vivarium.svga.model.Renderer;
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
            renderer.drawRect(null, null, null, null, null, null, null, null);
        }
    }
        private Renderer renderer;
}
