package np.ua.awis_mobile.posinteract.lib;

/**
 * @author galaxen
 */
public class GoodInfo {
    private int mGoodCode;
    private String mGoodName;
    private int mTaxGroup;

    /**
     * Info about sale item
     *
     * @param goodCode code item
     * @param goodName name of item
     * @param taxGroup tax group
     */
    public GoodInfo(int goodCode, String goodName, int taxGroup) {
        this.mGoodCode = goodCode;
        this.mGoodName = goodName;
        this.mTaxGroup = taxGroup;
    }

    public int getGoodCode() {
        return mGoodCode;
    }

    public String getGoodName() {
        return mGoodName;
    }

    public int getTaxGroup() {
        return mTaxGroup;
    }

}
