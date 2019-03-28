package rubronegra;

/** Simula referencia de um inteiro
 * @author Esther Barbara
 */
public class RefInt {
    private int internal;

    /** Constructor.
     * @param obj
     */
    public RefInt(int obj) {
        internal = obj;
    }

    /** Access method.
     * @return type
     */
    public int get() {
        return internal;
    }

    /** Set method. 
     * @param obj type
     */
    public void set(int obj) {
        internal = obj;
    }
}
