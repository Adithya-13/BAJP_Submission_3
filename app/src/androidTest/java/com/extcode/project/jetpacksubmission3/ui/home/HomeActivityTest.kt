package com.extcode.project.jetpacksubmission3.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.extcode.project.jetpacksubmission3.R
import com.extcode.project.jetpacksubmission3.utils.DataDummy
import com.extcode.project.jetpacksubmission3.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTvShows = DataDummy.generateDummyTvShows()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.btTvShows)).perform(click())
        onView(withId(R.id.rvTvShows)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size
            )
        )
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.posterTopBar)).check(matches(isDisplayed()))
        onView(withId(R.id.subPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.userScore)).check(matches(isDisplayed()))
        onView(withId(R.id.titleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.nestedScrollView)).perform(ViewActions.swipeUp())
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.popularity)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailTvShows() {
        onView(withId(R.id.btTvShows)).perform(click())
        onView(withId(R.id.rvTvShows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.posterTopBar)).check(matches(isDisplayed()))
        onView(withId(R.id.subPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.userScore)).check(matches(isDisplayed()))
        onView(withId(R.id.titleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.nestedScrollView)).perform(ViewActions.swipeUp())
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.popularity)).check(matches(isDisplayed()))
    }
}