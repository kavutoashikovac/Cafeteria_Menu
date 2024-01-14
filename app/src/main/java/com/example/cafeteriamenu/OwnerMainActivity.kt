package com.example.cafeteriamenu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.cafeteriamenu.backgroundservice.CustomWorker
import com.example.cafeteriamenu.databinding.ActivityOwnerMainBinding
import com.example.cafeteriamenu.db.DbViewModel
import com.example.cafeteriamenu.util.Utils
import com.google.android.material.snackbar.Snackbar

class OwnerMainActivity : AppCompatActivity() {
    lateinit var binding: ActivityOwnerMainBinding

    lateinit var workManager: WorkManager
    lateinit var workRequest: OneTimeWorkRequest
    lateinit var customWorker: CustomWorker
    lateinit var dbViewModel: DbViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOwnerMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        workRequest = OneTimeWorkRequest.Builder(CustomWorker::class.java)
            .setInputData(
                Data.Builder().putString("ownerName", Utils.parent.owner.name)
                    .putString("ownerDate",Utils.parent.owner.date).build()
            )
            .build()

        workManager = WorkManager.getInstance(applicationContext)

        workManager.getWorkInfoByIdLiveData(workRequest.id).observe(this@OwnerMainActivity,
            Observer{ workInfo ->
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    val resultData: Data = workInfo.outputData//get output of worker
                    Snackbar.make(binding.btnSaveToDatabase, "SUCCEEDED " + resultData.getString("result"), Snackbar.LENGTH_LONG ).show()
                }
            })

        binding.tvOwnerDetail.text= Utils.parent.owner.toString()
        binding.btnSaveToDatabase.setOnClickListener {
            workManager.enqueue(workRequest)
        }
    }
}