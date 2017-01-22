package net.fabricemk.android.mycv.ui.activities;

import android.support.v7.widget.Toolbar;

/**
 * All activities containing multiple fragments wanting a toolbar should implement this interface
 *
 * As choice has been made to delegate most of toolbar work to fragments, this interface will let
 * the fragments to modify some of the main toolbar properties
 *
 */
public interface IToolbarable {
    void setupToolbar(Toolbar toolbar);
}
