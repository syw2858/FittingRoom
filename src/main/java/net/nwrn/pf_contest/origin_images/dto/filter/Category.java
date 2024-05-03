package net.nwrn.pf_contest.origin_images.dto.filter;

public enum Category {
    SHIRT("shirt"),
    SWEATSHIRT("sweatshirt"),
    COAT("coat"),
    JUMPER("jumper");

    private final String displayValue;
    private Category(String displayValue) {
        this.displayValue = displayValue;
    }
    public String getDisplayValue() {return displayValue;}

}

