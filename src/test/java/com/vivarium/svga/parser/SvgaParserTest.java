package com.vivarium.svga.parser;

import com.vivarium.svga.model.Renderer;
import com.vivarium.svga.model.Style;
import com.vivarium.svga.model.Transform;
import com.vivarium.svga.model.Unit;
import org.junit.Test;

import static org.easymock.EasyMock.*;

/**
 * Created by neurons on 8/16/14.
 */
public class SvgaParserTest {
    @Test
    public void helloWorldTest(){
        Renderer renderer = createNiceMock(Renderer.class);
        renderer.drawRect(eq(new Unit(1)),
                eq(new Unit(1)),
                eq(new Unit(1198)),
                eq(new Unit(398)),
                anyObject(Unit.class),
                anyObject(Unit.class),
                anyObject(Transform.class),
                anyObject(Style.class));
        replay(renderer);

        SvgaParser parser = new SvgaParser();
        parser.setRenderer(renderer);
        parser.parse(SvgaParserTest.class.getClassLoader().getResourceAsStream("hello.svg"));
        verify(renderer);
    }
}
