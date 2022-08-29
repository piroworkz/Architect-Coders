package com.piroworkz.architectcoders.app.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.google.firebase.auth.FirebaseAuth
import com.piroworkz.architectcoders.R
import com.piroworkz.architectcoders.app.data.remote.datasource.GeoNamesServerDataSource
import com.piroworkz.architectcoders.app.utils.CustomViewAction.clickChildViewWithId
import com.piroworkz.architectcoders.app.utils.JsonReader.read
import com.piroworkz.architectcoders.app.utils.MockWebServerRule
import com.piroworkz.architectcoders.app.utils.OkHttp3IdlingResource
import com.piroworkz.architectcoders.testshared.productListOf
import com.piroworkz.architectcoders.testshared.shippingAddress
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

/**
 * Before running test make sure firebase emulator cli is started...
 * firebase emulators:start
 * hold down CTRL and double tap C , in the terminal running the emulator when you want to shut down the emulator and clear all the ports and processes.
 * */
@ExperimentalCoroutinesApi
@HiltAndroidTest
class MainUITest {
    @get:Rule(order = 0)
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @get:Rule(order = 2)
    val activityScenarioRule = ActivityScenarioRule(FragmentContainer::class.java)

    @Inject
    lateinit var geoNamesServerDataSource: GeoNamesServerDataSource

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var okHttpClient: okhttp3.OkHttpClient

    @Inject
    lateinit var dataSource: GeoNamesServerDataSource


    @Before
    fun setUp() = runTest {
        hiltAndroidRule.inject()
        firebaseAuth.useEmulator("10.0.2.2", 9099)
        mockWebServerRule.server.enqueue(MockResponse().read())
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okHttp", okHttpClient))
    }

    @After
    fun tearDown() {
        firebaseAuth.signOut()
        IdlingRegistry.getInstance()
            .unregister(OkHttp3IdlingResource.create("okHttp", okHttpClient))
    }

    @Test
    fun click_item_go_to_detail(): Unit = runTest {
        onView(withId(R.id.google_login_button))
            .perform(click())

        onView(withId(R.id.productsRecyclerView))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

        onView(withId(R.id.toolbar))
            .check(matches(hasDescendant(withText(productListOf[2].title))))
    }

    @Test
    fun add_item_to_cart() {
        onView(withId(R.id.google_login_button))
            .perform(click())

        onView(withId(R.id.productsRecyclerView))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

        onView(withId(R.id.openDetailButton))
            .perform(click())

        onView(withId(R.id.largeSizeChip))
            .perform(click())

        onView(withId(R.id.plusButton))
            .perform(click())

        onView(withId(R.id.addToCartButton))
            .perform(click())

        onView(withId(R.id.shoppingCartFragment))
            .perform(click())

        onView(withId(R.id.toolbar))
            .check(matches(hasDescendant(withText("Carrito de compras"))))
    }

    @Test
    fun add_to_favorites() {
        onView(withId(R.id.google_login_button))
            .perform(click())

        onView(withId(R.id.productsRecyclerView))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    clickChildViewWithId(R.id.favoriteSwitch)
                )
            )

        onView(withId(R.id.productsRecyclerView))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    2,
                    clickChildViewWithId(R.id.favoriteSwitch)
                )
            )

        onView(withId(R.id.productsRecyclerView))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    4,
                    clickChildViewWithId(R.id.favoriteSwitch)
                )
            )

        onView(withId(R.id.openMenuButton))
            .perform(click())

        onView(withId(R.id.userFavoritesFragment))
            .perform(click())

        onView(withId(R.id.favoritesRecyclerView))
            .check(matches(isDisplayed()))
    }


    @Test
    fun save_shipping_address() = runTest {
        onView(withId(R.id.google_login_button))
            .perform(click())

        onView(withId(R.id.openMenuButton))
            .perform(click())

        onView(withId(R.id.userProfileFragment))
            .perform(click())

        onView(withId(R.id.editShippingAddress))
            .perform(click())

        onView(allOf(withId(R.id.zipCodeET), isDisplayed()))
            .perform(typeText("52004"), closeSoftKeyboard())

        onView(allOf(withId(R.id.streetET), isDisplayed()))
            .perform(typeText(shippingAddress.street), closeSoftKeyboard())

        onView(allOf(withId(R.id.townET), isDisplayed()))
            .perform(typeText(shippingAddress.town), closeSoftKeyboard())

        onView(
            allOf(
                withId(R.id.useSameAddress),
                withText("Usar como dirección de facturación"),
                isDisplayed()
            )
        )
            .perform(click())

        onView(allOf(withId(R.id.saveAddressButton), withText("Guardar"), isDisplayed()))
            .perform(click())

        onView(allOf(withId(R.id.shippingAddressText), isDisplayed()))
            .check(matches(withText(containsString(shippingAddress.street))))

    }

}