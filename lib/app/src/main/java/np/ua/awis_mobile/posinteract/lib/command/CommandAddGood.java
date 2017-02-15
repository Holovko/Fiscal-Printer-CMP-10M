package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;


/**
 * Add good for zero check
 * Created by Andrey Holovko on 1/24/17.
 */
class CommandAddGood extends Command {
    private static final int CODE = 0x24;

    /**
     * @param code code of item
     * @param name item name
     * @param taxGroup tax group recorded on device
     */
    CommandAddGood(int code, String name, int taxGroup) {
        super(CODE);
        super.mCommandList = new ArrayList<>();
        addTextParameter(String.valueOf(code));
        if (name.length() > 75) {
            name = name.substring(0, 75);
        }
        addTextParameter(name);
        addTextParameter(String.valueOf(taxGroup));
        addTextParameter("1");
        addTextParameter("00");
        addTextParameter("");
        super.mCommandList.add(this);
    }
}
