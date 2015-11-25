package nl.s5630213023.saving;

/**
 * Created by NxEnjoy on 11/7/2015.
 */
public class item {
    private String item;
    private String Category;
    private double cash;

    public item(String item, double cash, int indexCategory) {
        setItem(item);
        setCash(cash);
        setCategory(indexCategory);
    }
    @Override
    public String toString() {return " \t" + this.getItem() + " \t(" + this.getCash()+ ") \t" + this.getCategory();}

    // Set Mothod
    public void setItem(String item) { this.item = item;}

    public void getCategory(String category) { Category = category;}

    public void setCategory(int indexCategory) {
        switch (indexCategory) {
            case 1:  this.getCategory("Food"); break;
            case 2:  this.getCategory("Drink"); break;
            case 3:  this.getCategory("Entertain"); break;
            case 4:  this.getCategory("Energy"); break;
            case 6: this.getCategory("Monthly"); break;
            case 7: this.getCategory("PartTime"); break;
            case 8: this.getCategory("Lucky"); break;
            default: this.getCategory("Other"); break;
        }
    }

    public void setCash(double cash) { this.cash = cash; }
    // Get Mothod
    public String getItem() {return item;}

    public String getCategory() { return Category; }

    public double getCash() { return cash; }
    // Adapter Mothod
    public String stringCash(){  return " ("+getCash()+" บาท)"; }







}
