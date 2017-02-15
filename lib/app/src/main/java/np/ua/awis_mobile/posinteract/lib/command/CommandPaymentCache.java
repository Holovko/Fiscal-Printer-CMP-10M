package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Payment cache
 * Created by Andrey Holovko on 1/24/17.
 */
public class CommandPaymentCache extends Command {
    private static final int CODE = 0x67;

    /**
     * @param paymentSum sum of payment
     */
    public CommandPaymentCache(float paymentSum) {
        super(CODE);
        super.mCommandList = new ArrayList<>();
        addTextParameter("0");
        addTextParameter(String.format(Locale.US, "%.2f", paymentSum));
        super.mCommandList.add(this);
    }
}
