package com.adewijayanto.defilmsapp2.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.adewijayanto.defilmsapp2.R
import com.adewijayanto.defilmsapp2.utils.DataDummy
import com.adewijayanto.defilmsapp2.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {
    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTvShows = DataDummy.generateDummyTvShow()

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun buttonNav(){
        Espresso.onView(withId(R.id.menu_tv_show)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.menu_movie)).perform(ViewActions.click())
    }

    @Test
    fun loadMovies() {
        Espresso.onView(withId(R.id.menu_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadTvShows() {
        Espresso.onView(withId(R.id.menu_tv_show)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.menu_tv_show))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
        Espresso.onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.tv_detail_judul))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_judul))
            .check(ViewAssertions.matches(withText(dummyMovies[1].title)))
        Espresso.onView(withId(R.id.tv_detail_deskripsi))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_deskripsi))
            .check(ViewAssertions.matches(withText(dummyMovies[1].overview)))
        Espresso.onView(withId(R.id.tv_detail_bahasa))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_date))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_date))
            .check(ViewAssertions.matches(withText(dummyMovies[1].release_date)))
    }

    @Test
    fun loadDetailTvShow() {
        Espresso.onView(withId(R.id.menu_tv_show)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size
            )
        )
        Espresso.onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                4,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.tv_detail_judul))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_judul))
            .check(ViewAssertions.matches(withText(dummyTvShows[4].name)))
        Espresso.onView(withId(R.id.tv_detail_deskripsi))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_deskripsi))
            .check(ViewAssertions.matches(withText(dummyTvShows[4].overview)))
        Espresso.onView(withId(R.id.tv_detail_bahasa))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_date))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_date))
            .check(ViewAssertions.matches(withText(dummyTvShows[4].first_air_date)))
    }

    @Test
    fun buttonShareMovies() {
        Espresso.onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.fbShare)).perform(ViewActions.click())
    }

    @Test
    fun buttonShareTvShow() {
        Espresso.onView(withId(R.id.menu_tv_show)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tv_show))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.fbShare)).perform(ViewActions.click())
    }
}