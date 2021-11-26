package sk.itsovy.android.opendatagame

import android.os.Bundle
import android.telephony.ims.ImsMmTelManager
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sk.itsovy.android.opendatagame.databinding.ActivityMainBinding
import kotlin.math.min

class MainActivity : AppCompatActivity() {
    private val model: NamesViewModel by viewModels() {
        NamesViewModel.NamesViewModelFactory((application as NamesApplication).repository)
    }
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    // na zaciatku je prazdny zoznam aby sa nepracovalo s null
    var currentData: List<Record> = emptyList()

    // oznacuje ci hra bezi
    private var isPlaying = false

    private lateinit var adapter : NamesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        adapter = NamesAdapter()
        binding.includedContentMain.recyclerViewNames.adapter = adapter
        binding.includedContentMain.recyclerViewNames.layoutManager = LinearLayoutManager(this)
        itemTouchHelper.attachToRecyclerView(binding.includedContentMain.recyclerViewNames)

        binding.fab.setOnClickListener {
            onFabClicked()
        }
        model.allRecords.observe(this, Observer {
            currentData = it
            // records -> currentData = records
        })
    }

    private fun onFabClicked() {
        if (isPlaying) {
            // vyhodnotenie
            val text = if (adapter.win) "Vyhra, gratulujeme" else "Prehral si"
            Toast.makeText(this, text, Toast.LENGTH_LONG).show()
        } else {
            // nova hra
            adapter.submitList(getRandomList(4))
        }
        adapter.visibleCounts = isPlaying
        isPlaying = !isPlaying
    }

    private fun getRandomList(count: Int) : List<Record> {
        val shuffledList = currentData.shuffled()
        return shuffledList.subList(0, min(count, shuffledList.size))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_load -> {
                model.loadRecords()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    private val itemTouchHelper by lazy {
        var simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT, 0
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val adapter = recyclerView.adapter as NamesAdapter
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                adapter.exchangeItems(from, to)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // do nothing
            }
        }

        // itemTouchHelper = new ItemTouchHelper(callback)
        ItemTouchHelper(simpleItemTouchCallback)
    }

}