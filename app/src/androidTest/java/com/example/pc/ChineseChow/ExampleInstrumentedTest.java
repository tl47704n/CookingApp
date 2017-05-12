package com.example.pc.ChineseChow;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * Instrumentation view_recipe, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleInstrumentedTest {

    public void useAppContext() throws Exception {
        // Context of the app under view_recipe.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.pc.homechefparty", appContext.getPackageName());
    }
}
