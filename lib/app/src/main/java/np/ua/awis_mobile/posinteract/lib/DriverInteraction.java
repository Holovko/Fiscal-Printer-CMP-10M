package np.ua.awis_mobile.posinteract.lib;


import np.ua.awis_mobile.posinteract.lib.command.Command;

/**
 * Interaction with driver
 * Created by Andrey Holovko on 1/25/17.
 */
public interface DriverInteraction {
    /**
     * Interface to simplify use driver
     * @param printerCommand command to execute
     */
    void doRequest(Command printerCommand);

    /**
     * Callback to notify UI
     * @param callback to notify device
     */
    void addResponseCallback(ResponseCallback callback);
}
