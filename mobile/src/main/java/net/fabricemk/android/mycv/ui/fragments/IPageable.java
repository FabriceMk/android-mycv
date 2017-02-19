package net.fabricemk.android.mycv.ui.fragments;

/**
 * Interface for fragments which are embedded in a ViewPager to provide a Title which can be used
 * for tabs or in a toolbar
 */
interface IPageable {

    /**
     * Returns the title
     * @return the title as a string
     */
    String getTitle();
}
