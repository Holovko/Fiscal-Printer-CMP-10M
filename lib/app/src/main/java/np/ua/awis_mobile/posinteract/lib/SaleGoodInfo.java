package np.ua.awis_mobile.posinteract.lib;


import np.ua.awis_mobile.posinteract.lib.command.CommandPrintZeroCheck;

/**
 * Goo for sale
 */
public class SaleGoodInfo {
    private float mCount;
    private float mPrice;
    private String mComment;
    private int mGoodCode;

    /**
     * @param goodCode code of good that used in {@link CommandPrintZeroCheck}
     * @param count count items to sale
     * @param price sale price
     * @param comment comment for sale good
     */
    public SaleGoodInfo(int goodCode, float count, float price, String comment) {
        this.mGoodCode = goodCode;
        this.mCount = count;
        this.mPrice = price;
        this.mComment = comment;
    }

    public float getCount() {
        return mCount;
    }

    public float getPrice() {
        return mPrice;
    }

    public String getComment() {
        return mComment;
    }

    public int getGoodCode() {
        return mGoodCode;
    }

}
