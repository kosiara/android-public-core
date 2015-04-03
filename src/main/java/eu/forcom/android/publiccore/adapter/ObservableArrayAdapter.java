package eu.forcom.android.publiccore.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * An ArrayAdapter for Android's ListView that can be decorated with an observer.
 * Each dataset change notifies the observer via onAdapterDataSetChanged() method.
 *
 * @param <T>
 */
public class ObservableArrayAdapter<T> extends ArrayAdapter<T>{

    private Observer mObserver;

    /**
     * Set an observer to get dataset notifications.
     * @param observer
     */
    public void setObserver(Observer observer) {
        this.mObserver = observer;
    }

    public static interface Observer {
        void onAdapterDataSetChanged();
    }

    public ObservableArrayAdapter(Context context, int resource) {
        super(context, resource);
    }

    public ObservableArrayAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public ObservableArrayAdapter(Context context, int resource, T[] objects) {
        super(context, resource, objects);
    }

    public ObservableArrayAdapter(Context context, int resource, int textViewResourceId, T[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public ObservableArrayAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
    }

    public ObservableArrayAdapter(Context context, int resource, int textViewResourceId, List<T> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

        if (mObserver != null)
            mObserver.onAdapterDataSetChanged();
    }
}
