package eu.forcom.android.publiccore.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import eu.forcom.android.publiccore.R;

public class TypefacedTextView extends TextView {

    public TypefacedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context, attrs);
    }

    public TypefacedTextView(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        setTypeface(context, attrs);
    }

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
