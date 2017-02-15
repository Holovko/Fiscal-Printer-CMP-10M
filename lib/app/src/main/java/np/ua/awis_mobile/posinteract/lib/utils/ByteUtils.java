package np.ua.awis_mobile.posinteract.lib.utils;

/**
 * Just byte utils, thanks galaxen
 */
public final class ByteUtils {

    private ByteUtils() {
    }

    /**
     * Add one byte to end of array
     *
     * @param arr destination
     * @param b   byte to add
     * @return array with byte
     */
    public static byte[] addByteToEndArr(byte[] arr, byte b) {
        byte[] arrToAdd = {b};
        return addByteToEndArr(arr, arrToAdd);
    }

    /**
     * Add array to byte array
     *
     * @param arr     destination
     * @param bArrAdd arra to add
     * @return array with array
     */
    public static byte[] addByteToEndArr(byte[] arr, byte[] bArrAdd) {
        byte[] dest = new byte[arr.length + bArrAdd.length];
        System.arraycopy(arr, 0, dest, 0, arr.length);
        System.arraycopy(bArrAdd, 0, dest, arr.length, bArrAdd.length);
        return dest;
    }

    /**
     * Add byte to byte array
     *
     * @param arr array
     * @param i   byte to int
     * @return array with byte
     */
    public static byte[] addByteToEndArr(byte[] arr, int i) {
        byte[] arrToAdd = {(byte) i};
        return addByteToEndArr(arr, arrToAdd);
    }

    /**
     * Convert byte array to String hex
      * @param arr array to conver
     * @return hex String
     */
    public static String byteArrToStringHexArr(byte[] arr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : arr) {
            String hexStr = Integer.toHexString(unsignedToBytes(b));
            if (hexStr.length() == 1) {
                hexStr = "0" + hexStr;
            }
            sb.append(hexStr);
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * @param b for byte
     * @return get unassigned bytes
     */
    public static int unsignedToBytes(byte b) {
        return (b & 0xFF);
    }
}
