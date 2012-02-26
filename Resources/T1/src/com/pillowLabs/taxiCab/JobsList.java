package com.pillowLabs.taxiCab;

import com.pillowLabs.taxiCab.JobsDbAdapter;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class JobsList extends ListActivity {
    private JobsDbAdapter mDbHelper;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobs_list);
        mDbHelper = new JobsDbAdapter(this);
        mDbHelper.open();
        mDbHelper.createJob("title2", "body");
        fillData();
    }
    
    private void fillData() {
        Cursor jobsCursor = mDbHelper.fetchAllJobs();
        startManagingCursor(jobsCursor);

        // Create an array to specify the fields we want to display in the list (only TITLE)
        String[] from = new String[]{JobsDbAdapter.KEY_NAME};

        // and an array of the fields we want to bind those fields to (in this case just text1)
        int[] to = new int[]{R.id.text1};

        // Now create a simple cursor adapter and set it to display
        // TODO use LoaderManager with a CursorLoader. API level 11 needed
        SimpleCursorAdapter jobs = 
            new SimpleCursorAdapter(this, R.layout.jobs_row, jobsCursor, from, to);
        setListAdapter(jobs);
    }
    
    @Override
    public void onStart(){//for resuming being seen by the user after restart from stop
    	super.onStart();
    }
    @Override
    public void onRestart(){//for activating cursor from stop
    	super.onRestart();
    }
    @Override
    public void onStop(){//for removing the cursor when you are no longer visible after pause
    	super.onStop();
    }
    @Override
    public void onDestroy(){//for removing resources like threads after stop
    	super.onDestroy();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState();
        //outState.putSerializable(NotesDbAdapter.KEY_ROWID, mRowId);//used to save the row id with a key into the bundle to be loaded in oncreate.
    }

    @Override
    protected void onPause() {//for saving, moving the activity to the background and freeing exclusive hardware
        super.onPause();
        saveState();
    }

    @Override
    protected void onResume() {//for restarting interaction with the user from pause and after start from stop
        super.onResume();
        fillData();//populateFields();//used in notepad to reload the single edited record back into the fields.
    }

    private void saveState() {//currently used to save the note in progress to the db before closing.
        /*String title = mTitleText.getText().toString();
        String body = mBodyText.getText().toString();

        if (mRowId == null) {
            long id = mDbHelper.createjob(title, body);
            if (id > 0) {
                mRowId = id;
            }
        } else {
            mDbHelper.updatejob(mRowId, title, body);
        }*/
    }
}