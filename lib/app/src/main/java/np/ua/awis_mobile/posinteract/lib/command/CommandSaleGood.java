package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Sale programmed item
 * Created by Andrey Holovko on 1/24/17.
 */
public class CommandSaleGood extends Command {
    private static final int CODE = 0x64;

    /**
     * @param goodCode code of good
     * @param count count to sale, by default use 1
     * @param price price of sale
     */
    public CommandSaleGood(int goodCode, float count, float price) {
        super(CODE);
        super.mCommandList = new ArrayList<>();
        addTextParameter(String.valueOf(goodCode));
        addTextParameter(String.format(Locale.US, "%.3f", count));
        addTextParameter(String.format(Locale.US, "%.2f", price));
        super.mCommandList.add(this);
    }
}
