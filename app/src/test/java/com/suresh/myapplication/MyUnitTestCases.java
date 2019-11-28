package com.suresh.myapplication;

import androidx.lifecycle.LifecycleOwner;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyUnitTestCases {

    @Test
    public void CheckForNullValues() {
        MainActivity mainActivity = new MainActivity();

        // check if action bar not null
        assertNotNull(mainActivity.getSupportActionBar());

        mainActivity.model.getData().observe((LifecycleOwner) this, list -> {

            // check if get title is not null
            assertNotNull(list.getTitle());

            // check if rows in json not null
            assertNotNull(list.getRows());

            // check if get title is not null
            assertNotNull(list.getTitle());

        });

        // check if fragment manager is not null
        assertNotNull("Fragment manager should not be null", mainActivity.fragmentManager);

        // check if fragment transaction is not null
        assertNotNull("Fragment transaction should not be null", mainActivity.fragmentTransaction);





    }
}
