package eu.forcom.android.publiccore.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * SectionedBaseAdapter helps with populating ListView with data that can be divided into sections.
 * Header is being drawn above every row with unique HeaderId.
 * All items provided to Adapter must be sorted out.
 */
public abstract class SectionedBaseAdapter extends BaseAdapter {

    private Set<Integer> mHeaderVirtualPositions;
    private HashMap<Integer, Integer> mHeadersAboveVirtualPositions;

    public SectionedBaseAdapter() {
        mHeaderVirtualPositions = new HashSet<>();
        mHeadersAboveVirtualPositions = new HashMap<>();
    }

    @Override
    public int getCount() {
        return mHeaderVirtualPositions.size() + getItemsCount();
    }

    @Override
    public Object getItem(int virtualPosition) {
        int itemPosition = virtualPositionToItemPosition(virtualPosition);

        return getItemAtPosition(itemPosition);
    }

    @Override
    public long getItemId(int virtualPosition) {
        return virtualPosition;
    }

    @Override
    public View getView(int virtualPosition, View convertView, ViewGroup parent) {
        boolean isPositionHeader = mHeaderVirtualPositions.contains(virtualPosition);
        int itemPosition = virtualPositionToItemPosition(virtualPosition);

        return isPositionHeader
                ? getHeaderView(itemPosition, convertView, parent)
                : getPositionView(itemPosition, convertView, parent);
    }

    @Override
    public int getItemViewType(int virtualPosition) {
        boolean isPositionHeader = mHeaderVirtualPositions.contains(virtualPosition);

        return isPositionHeader ? 0 : 1;
    }

    @Override
    public int getViewTypeCount() {
        //row and header
        return 2;
    }

    @Override
    public void notifyDataSetChanged() {
        recalculateAdapterBackendData();

        super.notifyDataSetChanged();
    }

    private void recalculateAdapterBackendData() {
        int virtualIndex = 0;
        int itemIndex = 0;
        int itemsCount = getItemsCount();
        Set<Integer> headersIdConsumed = new HashSet<>();

        mHeaderVirtualPositions.clear();
        mHeadersAboveVirtualPositions.clear();

        while (itemIndex < itemsCount) {
            mHeadersAboveVirtualPositions.put(virtualIndex, mHeaderVirtualPositions.size());

            int headerId = getHeaderId(itemIndex);

            if (!headersIdConsumed.contains(headerId)) {
                headersIdConsumed.add(headerId);
                mHeaderVirtualPositions.add(virtualIndex);
            } else {
                itemIndex++;
            }

            virtualIndex++;
        }
    }

    private int virtualPositionToItemPosition(int virtualPosition) {
        int headersAbove = mHeadersAboveVirtualPositions.get(virtualPosition);

        return virtualPosition - headersAbove;
    }

    public abstract View getHeaderView(int itemPosition, View convertView, ViewGroup viewGroup);

    public abstract View getPositionView(int itemPosition, View convertView, ViewGroup viewGroup);

    public abstract Object getItemAtPosition(int itemPosition);

    public abstract int getItemsCount();

    public abstract int getHeaderId(int itemPosition);
}
