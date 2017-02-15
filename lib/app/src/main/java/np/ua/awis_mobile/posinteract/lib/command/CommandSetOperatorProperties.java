package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

/**
 * Set operator info
 * Created by Andrey Holovko on 1/24/17.
 */
public class CommandSetOperatorProperties extends Command {
    private static final int CODE = 0x2F;

    /**
     * @param operatorName operator name add only 20 symbols
     */
    public CommandSetOperatorProperties(String operatorName) {
        super(CODE);
        super.mCommandList = new ArrayList<>();
        addTextParameter("1");
        addTextParameter("000000");
        if (operatorName.length() > 20) {
            operatorName = operatorName.substring(0, 20);
        }
        addTextParameter(operatorName);
        super.mCommandList.add(this);
    }
}
