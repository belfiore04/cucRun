package base;

import auxiliary.ContentConstants;
import auxiliary.Direction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementBasicProperties {
    String NOTHING = "NOTHING";
    String value() default NOTHING;//无左右区分的对象的图片属性
    String leftImage() default NOTHING;
    String rightImage() default NOTHING;
    int elementWidth() default ContentConstants.ELEMENT_SIZE;
    int elementHeight()default ContentConstants.ELEMENT_SIZE;
    int characterWidth() default ContentConstants.CHARACTER_WIDTH;
    int characterHeight() default ContentConstants.CHARACTER_HEIGHT;
    Direction direction() default Direction.RIGHT;


}
