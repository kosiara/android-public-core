package eu.forcom.android.publiccore.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.webkit.WebView;
import eu.forcom.android.publiccore.R;

public class JustifiedTextView extends WebView {
    public JustifiedTextView(final Context context) {
        this(context, null, 0);
    }

    public JustifiedTextView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JustifiedTextView(final Context context, final AttributeSet attrs, final int defStyle, boolean isTransparent) {
        super(context, attrs, defStyle);

        if (attrs != null) {
            final TypedValue tv = new TypedValue();
            final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.JustifiedTextView, defStyle, 0);
            if (ta != null) {
                ta.getValue(R.styleable.JustifiedTextView_text, tv);

                if (tv.resourceId > 0) {
                    final String text = context.getString(tv.resourceId).replace("\n", "<br />");
                    loadDataWithBaseURL("file:///android_asset/",
                            "<html><head>" +
                                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"justified_textview.css\" />" +
                                    "</head><body>" + text + "</body></html>",

                            "text/html", "UTF8", null);
                    setTransparentBackground();
                }
            }
        }

        if (isTransparent)
            setTransparentBackground();
    }

    public JustifiedTextView(final Context context, final AttributeSet attrs, final int defStyle) {
        this(context, attrs, defStyle, true);
    }

    public void setTransparentBackground() {
        try {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        } catch (final NoSuchMethodError e) {}

        setBackgroundColor(Color.TRANSPARENT);
        setBackgroundDrawable(null);
        setBackgroundResource(0);
    }

    public void setText(String text) {
        text = text.replace("\n", "<br />");
        loadDataWithBaseURL("file:///android_asset/",
                "<html><head>" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"justified_textview.css\" />" +
                        "</head><body>" + text + "</body></html>",

                "text/html", "UTF8", null);
    }
}
