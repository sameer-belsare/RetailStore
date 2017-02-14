package com.retailstore;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.retailstore.productdetails.ProductDetailsActivity;
import com.retailstore.productlist.ProductListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Unit test class for UI test of application
 * Created by sameer.belsare on 14/2/17.
 */
@RunWith(AndroidJUnit4.class)
public class RetailStoreTest {
    @Rule
    public ActivityTestRule<ProductListActivity> productListActivityActivityTestRule =
            new ActivityTestRule<>(ProductListActivity.class);

    @Rule
    public ActivityTestRule<ProductDetailsActivity> productDetailsActivityActivityTestRule =
            new ActivityTestRule<>(ProductDetailsActivity.class, true, false);

    @Test
    public void viewProductDetailsAddProductToCartAndViewCart(){
        for(int i=0; i<9; i+=2) {
            Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            Intent intent = new Intent(targetContext, ProductDetailsActivity.class);
            intent.putExtra("id", i+1);
            productDetailsActivityActivityTestRule.launchActivity(intent);
            //Added sleep to stay on details screen
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            onView(withId(R.id.btnCart)).perform(click());
            productDetailsActivityActivityTestRule.getActivity().finish();
        }
        onView(withId(R.id.action_settings)).perform(click());
        //Added sleep to stay on cart
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
