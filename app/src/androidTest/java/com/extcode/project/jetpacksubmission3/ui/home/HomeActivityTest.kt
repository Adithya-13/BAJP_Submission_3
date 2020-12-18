package com.extcode.project.jetpacksubmission3.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.extcode.project.jetpacksubmission3.R
import com.extcode.project.jetpacksubmission3.utils.DataDummy
import com.extcode.project.jetpacksubmission3.utils.EspressoIdlingResource
import com.google.android.material.tabs.TabLayout
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
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
        onView(withId(R.id.nestedScrollView)).perform(swipeUp())
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
        onView(withId(R.id.nestedScrollView)).perform(swipeUp())
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.popularity)).check(matches(isDisplayed()))
    }

    @Test
    fun loadBookmarkMovies() {
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.favoriteButton)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.btFavorite)).perform(click())
        onView(withId(R.id.rvBookmarkMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvBookmarkMovies)).perform(
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
        onView(withId(R.id.nestedScrollView)).perform(swipeUp())
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.nestedScrollView)).perform(swipeDown())
        onView(withId(R.id.favoriteButton)).perform(click())
        onView(isRoot()).perform(pressBack())
    }

    @Test
    fun loadBookmarkTvShows() {
        onView(withId(R.id.btTvShows)).perform(click())
        onView(withId(R.id.rvTvShows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.favoriteButton)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.btFavorite)).perform(click())
        onView(withId(R.id.tab)).perform(selectTabAtPosition(1))
        onView(withId(R.id.rvBookmarkTvShows)).check(matches(isDisplayed()))
        onView(withId(R.id.rvBookmarkTvShows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                clickChildViewWithId(R.id.itemCard)
            )
        )
        onView(withId(R.id.posterTopBar)).check(matches(isDisplayed()))
        onView(withId(R.id.subPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.userScore)).check(matches(isDisplayed()))
        onView(withId(R.id.titleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.nestedScrollView)).perform(swipeUp())
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.nestedScrollView)).perform(swipeDown())
        onView(withId(R.id.favoriteButton)).perform(click())
        onView(isRoot()).perform(pressBack())
    }

    private fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $tabIndex"

            override fun getConstraints() =
                allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                    ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index $tabIndex"))
                        .build()

                tabAtIndex.select()
            }
        }
    }

    private fun clickChildViewWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<View>(id)
                v.performClick()
            }
        }
    }
}