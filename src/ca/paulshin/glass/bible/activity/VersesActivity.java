package ca.paulshin.glass.bible.activity;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import ca.paulshin.glass.bible.App;
import ca.paulshin.glass.bible.R;
import ca.paulshin.glass.bible.adapter.VerseSearchAdapter;
import ca.paulshin.glass.bible.api.Api;
import ca.paulshin.glass.bible.api.SearchVerses;
import ca.paulshin.glass.bible.api.SearchVerses.Verse;
import ca.paulshin.glass.bible.utils.DebugLog;

import com.google.android.glass.app.Card;

public class VersesActivity extends BaseCardScrollActivity {
	public static String EXTRA_COMMAND = "command";
	private SearchVerses.Verse mSelectedVerse;
	private TextToSpeech mSpeech;

    public static void call(Activity activity, String command) {
        Intent intent = new Intent(activity, VersesActivity.class);
        intent.putExtra(EXTRA_COMMAND, command);
        activity.startActivity(intent);
    }
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                // Do nothing.
            }
        });
	}
    
    @Override
    public void onDestroy() {
		mSpeech.shutdown();
        mSpeech = null;
        
        super.onDestroy();
	}

    @Override
    protected void loadData() {
        showProgress(R.string.loading);
        final String command = getIntent().getStringExtra(EXTRA_COMMAND);
        
        Api.get().create(SearchVerses.class).searchForVerse(command, new Callback<List<Verse>>() {
            @Override
            public void success(List<Verse> verses, Response response) {
            	StringBuilder verseTexts = new StringBuilder();
            	for (Verse verse : verses) {
            		verseTexts.append(verse.text.replace("<b>", "").replace("</b>", "").replace("&#8211;", "")).append(" "); // Do some cleaning
            	}
            	mSpeech.speak(verseTexts.toString(), TextToSpeech.QUEUE_FLUSH, null);
            	
            	// Timeline deprecated on 4.4.2
//            	Card card = new Card(App.get());
//            	card.setText(verseTexts.toString());
//            	card.setFootnote(command.replace("%20", " "));                
//            	TimelineManager.from(App.get()).insert(card);
            	
                showContent(new VerseSearchAdapter(verses), new CardSelectedListener() {
					@Override
					public void onCardSelected(Object item) {
						mSelectedVerse = (SearchVerses.Verse) item;
						openOptionsMenu();
					}
				});
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                DebugLog.e(retrofitError);
                showError(R.string.error_please_try_again);
            }
        });
    }
    
    // Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mSelectedVerse != null) {
//            menu.findItem(R.id.menu_tips).setEnabled(mSelectedVenue.hasTips);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mSelectedVerse != null) {
            switch (item.getItemId()) {
                case R.id.menu_read_aloud:
                	mSpeech.speak(mSelectedVerse.text, TextToSpeech.QUEUE_FLUSH, null);
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
