package np.ua.awis_mobile.posinteract.lib;

/**
 * Interaction with device
 * Created by Andrey Holovko on 1/25/17.
 */
public interface DeviceInteraction {
    /**
     * Write to device
     * @param out array byte
     */
    void write(byte[] out);

    /**
     * Add listener for response
     * @param readerPipe service need initialize lister for response
     */
    void setResponseListener(PrinterReaderPipe readerPipe);
}
