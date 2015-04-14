package eu.forcom.android.publiccore.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.webkit.WebView;
import eu.forcom.android.publiccore.R;

/**
 * <p>This Android control serves as a standard TextView with the ability to justify text inside of it. </p>
 *
 * <p>Tip: <br/> method setText() is used to set custom string value to control's text <br/>
 * android:text="" takes only 'R.string.name_of_string_res' string reference. It won't accept strings.</p>
 *
 * <p>Sample usage: <br/><br/>
 *
 * &#60;eu.forcom.android.publiccore.widget.JustifiedTextView <br/>
 * &#160;&#160;      android:layout_width="match_parent" <br/>
 * &#160;&#160;      android:layout_height="wrap_content" &#47;> <br/>
 *
 *  <br/>
 *  justifiedTextView.setText("Sample text body"); </p>
 *
 *
 * @see android.R
 */
public class JustifiedTextView extends WebView {

    private int mTextColor;

    public JustifiedTextView(final Context context, final AttributeSet attrs) { this(context, attrs, 0, true); }

    public JustifiedTextView(final Context context, final AttributeSet attrs, final int defStyle, boolean isTransparent) {
        super(context, attrs, defStyle);

        if (attrs != null) {
            final TypedValue tv = new TypedValue();
            final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.JustifiedTextView, defStyle, 0);
            if (ta != null) {
                ta.getValue(R.styleable.JustifiedTextView_text, tv);
                mTextColor = ta.getColor(R.styleable.JustifiedTextView_textColor, 0);

                if (tv.resourceId > 0) {
                    final String text = context.getString(tv.resourceId).replace("\n", "<br />");

                    reloadHtmlData(text);
                    setTransparentBackground();
                }
            }
        }

        if (isTransparent)
            setTransparentBackground();
    }

    /**
     * Sets background to transparent. Default is white.
     */
    public void setTransparentBackground() {
        try {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        } catch (final NoSuchMethodError e) {}

        setBackgroundColor(Color.TRANSPARENT);
        setBackgroundDrawable(null);
        setBackgroundResource(0);
    }

    /**
     * Sets/replaces text value of the control.
     * @param text string value to set
     */
    public void setText(String text) {
        text = text.replace("\n", "<br />");
        reloadHtmlData(text);
    }

    private void reloadHtmlData(String text) {
        loadDataWithBaseURL("file:///android_asset/",
                "<html><head>" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"justified_textview.css\" />" +
                        "</head><body style=\"color: #" + Integer.toHexString(mTextColor).substring(2) + "\">" + text + "</body></html>",

                "text/html", "UTF8", null);
    }
}
