package eu.forcom.android.publiccore.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class ObservableArrayAdapter<T> extends ArrayAdapter<T>{

    public static interface Observer {
        void onAdapterDataChanged();
    }

    private Observer mObserver;

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
            mObserver.onAdapterDataChanged();
    }

    public void setListObserver(Observer observer) {
        this.mObserver = observer;
    }
}
