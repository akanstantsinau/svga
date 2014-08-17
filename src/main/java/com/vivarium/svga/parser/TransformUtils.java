package com.vivarium.svga.parser;

import com.vivarium.svga.model.PreserveAspectRatio;
import com.vivarium.svga.model.Transform;
import com.vivarium.svga.model.Unit;

/**
 * @author neurons
 * @since 8/16/14.
 */
public class TransformUtils {
    /**
     * Creates transformation matrix for given viewport settings.
     * @param targetWidth
     * @param targetHeight
     * @param viewportWidth
     * @param viewPortHeight
     * @param preserveAspectRatio
     * @return Transformation matrix
     */
    public Transform getViewportTransform(Unit targetWidth,
                                          Unit targetHeight,
                                          Unit viewportWidth,
                                          Unit viewPortHeight,
                                          PreserveAspectRatio preserveAspectRatio){
        Transform result = Transform.NO_TRANSFORM;

        float scaleX = targetWidth.getValue() / viewportWidth.getValue();
        float scaleY = targetHeight.getValue() / viewPortHeight.getValue();

        if(preserveAspectRatio == null || preserveAspectRatio.isDefer() ){
            result = Transform.scale(scaleX, scaleY);
        }else {

            float maxScale = scaleX > scaleY ? scaleX : scaleY;
            float minScale = scaleX < scaleY ? scaleX : scaleY;

            if (preserveAspectRatio.getMeetOrSlice() == PreserveAspectRatio.MeetOrSlice.MEET) {
                float deltaX = targetWidth.getValue() - viewportWidth.getValue() * minScale;
                if (deltaX < 0) {
                    deltaX = 0;
                }
                float deltaY = targetHeight.getValue() - viewPortHeight.getValue() * minScale;
                if (deltaY < 0) {
                    deltaY = 0;
                }
                deltaX = getDelta(deltaX, preserveAspectRatio.getxAlign());
                deltaY = getDelta(deltaY, preserveAspectRatio.getyAlign());
                result = Transform.addTransform(Transform.scale(minScale, minScale),
                        Transform.translate(new Unit(deltaX, targetWidth.getType()),
                                new Unit(deltaY, targetHeight.getType())));
            } else if (preserveAspectRatio.getMeetOrSlice() == PreserveAspectRatio.MeetOrSlice.SLICE) {
                float deltaX = targetWidth.getValue() - viewportWidth.getValue() * maxScale;
                if (deltaX > 0) {
                    deltaX = 0;
                }
                float deltaY = targetHeight.getValue() - viewPortHeight.getValue() * maxScale;
                if (deltaY > 0) {
                    deltaY = 0;
                }
                deltaX = getDelta(deltaX, preserveAspectRatio.getxAlign());
                deltaY = getDelta(deltaY, preserveAspectRatio.getyAlign());
                result = Transform.addTransform(Transform.scale(maxScale, maxScale),
                        Transform.translate(new Unit(deltaX, targetWidth.getType()),
                                new Unit(deltaY, targetHeight.getType())));
            }
        }
        return result;
    }
    private float getDelta(float value, PreserveAspectRatio.Align align){
        switch(align){
            case MAX:
                break;
            case MID:
                value = value / 2;
                break;
            case MIN:
            default:
                value = 0;
                break;
        }
        return value;
    }
}
