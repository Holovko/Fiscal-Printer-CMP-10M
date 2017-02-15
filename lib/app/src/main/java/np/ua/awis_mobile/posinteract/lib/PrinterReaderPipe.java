package np.ua.awis_mobile.posinteract.lib;

/**
 * Interface to read response
 * Created by Andrey Holovko on 1/25/17.
 */
public interface PrinterReaderPipe {
    /**
     * device response
     * @param buffer resopnce byte
     */
    void response(byte[] buffer);
}
