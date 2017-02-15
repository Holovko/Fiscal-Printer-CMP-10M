package np.ua.awis_mobile.posinteract.lib.utils;

/**
 * Jus aggregate result
 * Created by Andrey Holovko on 2/1/17.
 */
public class LogResult {
    private String mCommandName;
    private String mResponseResult;
    private String mResultMessage;

    /**
     * @param commandName class name
     */
    public LogResult(String commandName) {
        mCommandName = commandName;
    }

    public String getResultMessage() {
        return mResultMessage;
    }

    public void setResultMessage(String resultMessage) {
        mResultMessage = resultMessage;
    }

    public String getCommandName() {
        return mCommandName;
    }

    public String getResponseResult() {
        return mResponseResult;
    }

    public void setResponseResult(String responseResult) {
        mResponseResult = responseResult;
    }
}
