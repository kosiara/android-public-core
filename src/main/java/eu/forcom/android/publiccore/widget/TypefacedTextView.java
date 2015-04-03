package eu.forcom.android.publiccore.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import eu.forcom.android.publiccore.R;

/**
 * <p>Extension of Android's TextView with the ability to set custom typeface.
 * Use the attribute
 *
 * <br/><br/> custom:typeface="Roboto-Regular.ttf" <br/><br/>
 *
 * to set custom typeface from xml view.</p>
 *
 * @see android.widget.TextView
 */
public class TypefacedTextView extends TextView {

    public TypefacedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context, attrs);
    }

    public TypefacedTextView(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        setTypeface(context, attrs);
    }

    /**
     * Custom typeface is automatically set in the constructor.
     * @param context
     * @param attrs
     */
    protected void setTypeface(Context context, AttributeSet attrs) {
        //If in the layout editor. Skip.
        if (isInEditMode())
            return;

        TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.TypefacedTextView);
        String fontName = styledAttrs.getString(R.styleable.TypefacedTextView_typeface);
        styledAttrs.recycle();

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontName);
        setTypeface(typeface);
    }
}
