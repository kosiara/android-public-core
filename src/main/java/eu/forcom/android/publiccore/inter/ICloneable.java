package eu.forcom.android.publiccore.inter;

/**
 *  <p>ICloneable defines only one method clone(). <br/>
 *  It's a replacement of java's own interface java.lang.Cloneable.</p>
 *
 *  <p>clone() is tricky to implement correctly.
 *  It's better to use Defensive copying, copy constructors or static factory methods.</p>

 *  @see java.lang.Cloneable
 */
public interface ICloneable {

    /**
     * This method must be implemented by all classes that wish to
     * support deep cloning. The implementation of {@code clone()} in {@code Object}
     * checks if the object being cloned implements this interface and throws
     * {@code CloneNotSupportedException} if it does not.
     *
     * @see Object#clone
     * @see CloneNotSupportedException
     */
    public Object clone();
}
