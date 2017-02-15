package np.ua.awis_mobile.posinteract.lib.command;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.util.ArrayList;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Print daily report
 * Created by Andrey Holovko on 1/24/17.
 */

public class CommandOpenFiscalCheck extends Command {
    /**
     * Sale
     */
    public static final String SALE = "0";
    /**
     * Return
     */
    public static final String RETURN = "1";
    /**
     * Deposite
     */
    public static final String DEPOSIT_WITHDRAWAL = "2";

    private static final int CODE = 0x63;

    /**
     * @param cashboxNumber number of cashbox that was added in initialization from zero report
     * @param fiscalCheckType type check to open
     */
    public CommandOpenFiscalCheck(String cashboxNumber, @FiscalCheckType String fiscalCheckType) {
        super(CODE);
        super.mCommandList = new ArrayList<>();
        addTextParameter("1");
        addTextParameter(cashboxNumber);
        addTextParameter(fiscalCheckType);
        super.mCommandList.add(this);
    }

    @Retention(SOURCE)
    @StringDef({SALE, RETURN, DEPOSIT_WITHDRAWAL})
    private @interface FiscalCheckType {
    }

}
