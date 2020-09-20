package com.example.room.trigger.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.room.R
import com.example.room.trigger.ui.entries.EntriesFragment
import com.example.room.trigger.ui.logs.LogsFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_trigger_demo.*
import java.lang.IllegalArgumentException
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class TriggerDemoActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, TriggerDemoActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trigger_demo)

        setupActionBar()
        setupViewPager()
    }

    private fun setupActionBar() {
        supportActionBar?.title = "Trigger Demo"
    }

    private fun setupViewPager() {
        view_pager.adapter = TriggerDemoViewPagerAdapter(
            activity = this,
            fragments = ViewPagerItem.values().map {
                it.fragmentClass.createInstance()
            }
        )
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = ViewPagerItem.findByPosition(position).title
        }.attach()
    }


    private enum class ViewPagerItem(
        val position: Int,
        val title: String,
        val fragmentClass: KClass<out Fragment>
    ) {
        ENTRIES(0, "Entries", EntriesFragment::class),
        LOGS(1, "Logs", LogsFragment::class);

        companion object {

            fun findByPosition(position: Int): ViewPagerItem {
                return values().find { it.position == position }
                    ?: throw IllegalArgumentException("Can't find ViewPagerItem for position - $position")
            }
        }
    }
}