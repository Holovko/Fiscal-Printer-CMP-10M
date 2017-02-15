package np.ua.awis_mobile.posinteract.lib.command;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.util.ArrayList;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Print daily report
 * Created by Andrey Holovko on 1/24/17.
 */

public class CommandPrintCoupon extends Command {
    /**
     * Init
     */
    public static final String INIT = "0";
    /**
     * Print and return
     */
    public static final String PRINT_AND_RETURN = "1";
    /**
     * Clear and return
     */
    public static final String CLEAR_AND_RETURN = "2";
    /**
     * Init without black line
     */
    public static final String INIT_WITHOUT_BLACK_LINE = "3";
    /**
     * Print return new
     */
    public static final String PRINT_AND_RETURN_TO_NEW = "4";

    private static final int CODE = 0xc8;

    /**
     * @param type coupon type
     */
    public CommandPrintCoupon(@Type String type) {
        super(CODE);
        super.addTextParameter(type);
        super.mCommandList = new ArrayList<>();
        super.mCommandList.add(this);
    }

    @Retention(SOURCE)
    @StringDef({
            INIT, PRINT_AND_RETURN, CLEAR_AND_RETURN,
            INIT_WITHOUT_BLACK_LINE, PRINT_AND_RETURN_TO_NEW
    })
    private @interface Type {
    }

}
