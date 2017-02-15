package np.ua.awis_mobile.posinteract.lib.command;

import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import java.util.ArrayList;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Print amount for type
 * Created by Andrey Holovko on 1/24/17.
 */

public class CommandGetAmountsPerDay extends Command {

    /**
     * Payments cache
     */
    public static final String PAYMENTS_CACHE = "0";

    /**
     * Payments cacheless
     */
    public static final String PAYMENTS_CACHELESS = "1";

    /**
     * Amounts adding withdrawal
     */
    public static final String AMOUNTS_ADDING_WITHDRAWAL = "2";

    /**
     * Amounts in cache box
     */
    public static final String AMOUNTS_IN_CACHEBOX = "3";

    /**
     * Turnover A
     */
    public static final String TURNOVER_A = "4";

    /**
     * Turnover B
     */
    public static final String TURNOVER_B = "5";

    /**
     * Income tax A
     */
    public static final String INCOME_TAX_A = "6";

    /**
     * Income tax B
     */
    public static final String INCOME_TAX_B = "7";

    /**
     * Turnover C
     */
    public static final String TURNOVER_C = "8";

    /**
     * Income tax C
     */
    public static final String INCOME_TAX_C = "9";

    private static final int CODE = 0xE0;

    /**
     * @param type type report to print
     */
    public CommandGetAmountsPerDay(@AmountType String type) {
        super(CODE);
        int source = 0;
        int idx = 0;
        int modifier = 0;
        super.mCommandList = new ArrayList<>();
        if (PAYMENTS_CACHE.equals(type)) {
            source = 2;
            idx = 0;
            modifier = 0;
        } else if (PAYMENTS_CACHELESS.equals(type)) {
            source = 2;
            idx = 1;
            modifier = 0;
        } else if (AMOUNTS_IN_CACHEBOX.equals(type)) {
            source = 3;
            idx = 0;
            modifier = 0;
        } else if (AMOUNTS_ADDING_WITHDRAWAL.equals(type)) {
            source = 3;
            idx = 1;
            modifier = 0;
        } else if (TURNOVER_A.equals(type)) {
            source = 0;
            idx = 0;
            modifier = 0;
        } else if (TURNOVER_B.equals(type)) {
            source = 0;
            idx = 1;
            modifier = 0;
        } else if (INCOME_TAX_A.equals(type)) {
            source = 1;
            idx = 0;
            modifier = 0;
        } else if (INCOME_TAX_B.equals(type)) {
            source = 1;
            idx = 1;
            modifier = 0;
        } else if (TURNOVER_C.equals(type)) {
            source = 0;
            idx = 2;
            modifier = 0;
        } else if (INCOME_TAX_C.equals(type)) {
            source = 1;
            idx = 2;
            modifier = 0;
        }
        super.addTextParameter(String.valueOf(source));
        super.addTextParameter(String.valueOf(idx));
        super.addTextParameter(String.valueOf(modifier));
        super.mCommandList.add(this);
    }

    @Retention(SOURCE)
    @StringDef({
            PAYMENTS_CACHE, PAYMENTS_CACHELESS, AMOUNTS_ADDING_WITHDRAWAL, AMOUNTS_IN_CACHEBOX,
            TURNOVER_A, TURNOVER_B, INCOME_TAX_A, INCOME_TAX_B, TURNOVER_C, INCOME_TAX_C})
    private @interface AmountType {
    }

}
