package eu.forcom.android.publiccore.util;

import java.util.ArrayList;
import java.util.List;
import eu.forcom.android.publiccore.inter.ICloneable;

public class ListUtil {

    /**
     * <p>Used to make a deep clone of the entire list of objects of type T which are Cloneable </p>
     *
     * @param inputList list to clone
     * @param <T> Type of list's elements
     * @return Deep clone of a list
     */
    public static <T extends ICloneable> List<T> cloneList(List<T> inputList) {
        try {
            List<T> clone = new ArrayList<T>(inputList.size());
            for(T item: inputList)
                clone.add((T)item.clone());
            return clone;
        } catch (Exception exc) {}
        return null;
    }
}
