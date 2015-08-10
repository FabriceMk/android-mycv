package net.fabricemk.android.mycv.ui.activities;

import android.support.v7.widget.Toolbar;

/**
 * All activities wanting a toolbar should implement this interface
 *
 * As choice has been made to delegate most of toolbar work to fragments.
 */
public interface IToolbarable {

    public void setupToolbar(Toolbar toolbar);
}
