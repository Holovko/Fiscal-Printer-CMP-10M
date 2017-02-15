package np.ua.awis_mobile.posinteract.lib;

import java.util.HashMap;

import np.ua.awis_mobile.posinteract.lib.utils.Result;


/**
 * Command result
 * Created by Andrey Holovko on 1/26/17.
 */
public class CommandResult {
    private Result mCommandResult;
    private HashMap<String, String> mResponseData;

    /**
     * @param commandResult result from enum {@link Result}
     * @param responseData  parsed data
     */
    public CommandResult(Result commandResult, HashMap<String, String> responseData) {
        mCommandResult = commandResult;
        mResponseData = responseData;
    }

    public Result getCommandResult() {
        return mCommandResult;
    }

    public HashMap<String, String> getResponseData() {
        return mResponseData;
    }
}
