package np.ua.awis_mobile.posinteract.lib.command;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.util.ArrayList;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Print daily report
 * Created by Andrey Holovko on 1/24/17.
 */

public class CommandPrintDailyReport extends Command {
    /**
     * X report
     */
    public static final String X_REPORT = "1";
    /**
     * Annulate control tape
     */
    public static final String ANULATE_CONTROL_TAPE = "2";
    /**
     * Copy last fiscal check
     */
    public static final String COPY_LAST_FISCAL_CHECK = "4";
    /**
     * Z report without anulate
     */
    public static final String Z_REPORT_WITHOUT_ANULATE_CONTROL_TAPE = "6";
    /**
     * Z report with anulate
     */
    public static final String Z_REPORT_WITH_ANULATE_CONTROL_TAPE = "0";

    private static final int CODE = 0xA1;

    /**
     * @param reportType type report to print
     */
    public CommandPrintDailyReport(@ReportType String reportType) {
        super(CODE);
        super.addTextParameter(reportType);
        super.mCommandList = new ArrayList<>();
        super.mCommandList.add(this);
    }

    @Retention(SOURCE)
    @StringDef({
            Z_REPORT_WITH_ANULATE_CONTROL_TAPE, X_REPORT, ANULATE_CONTROL_TAPE,
            COPY_LAST_FISCAL_CHECK, Z_REPORT_WITHOUT_ANULATE_CONTROL_TAPE
    })
    private @interface ReportType {
    }

}
