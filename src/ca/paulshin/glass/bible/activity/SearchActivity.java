package ca.paulshin.glass.bible.activity;

import java.util.ArrayList;

import android.speech.RecognizerIntent;
import android.text.TextUtils;
import ca.paulshin.glass.bible.R;
import ca.paulshin.glass.bible.utils.Utils;

/**
 * Base activity which performs search.
 */
public class SearchActivity extends BaseActivity {
	@Override
    protected void onResume() {
        super.onResume();
        ArrayList<String> voiceResults = getIntent().getExtras()
                .getStringArrayList(RecognizerIntent.EXTRA_RESULTS);
        String command = null;
        if (voiceResults != null && voiceResults.size() > 0) {
            command = voiceResults.get(0);
        }
        
        if (!TextUtils.isEmpty(command)) {
	        if ("today".equalsIgnoreCase(command)) {
	        	VersesActivity.call(this, "votd");
	            finish();
	        }
	        else if ("random".equalsIgnoreCase(command)) {
	        	VersesActivity.call(this, command);
	            finish();
	        }
	        else {
		        String[] cmdArray = command.split(" ");
		        
		        //TODO: handle 1 John
		        // John 3:16 - John Chapter 3 Verse 16
		        if (cmdArray.length == 5) {
		        	if ("chapter".equalsIgnoreCase(cmdArray[1]) 
		        			&& "verse".equalsIgnoreCase(cmdArray[3]) 
		        			&& isInteger(cmdArray[2]) 
		        			&& isInteger(cmdArray[4])) {
		        		command = cmdArray[0] + "%20" + cmdArray[2] + ":" + cmdArray[4];
		        		VersesActivity.call(this, command);
				        finish();
		        	}
		        }
		        // John 3:16-17 - John Chapter 3 Verse 16 To 17
		        else if(cmdArray.length == 7) {
		        	if ("chapter".equalsIgnoreCase(cmdArray[1]) 
		        			&& "verse".equalsIgnoreCase(cmdArray[3]) 
		        			&& ("to".equalsIgnoreCase(cmdArray[5]) || "through".equalsIgnoreCase(cmdArray[5]) || "thru".equalsIgnoreCase(cmdArray[5])) 
		        			&& isInteger(cmdArray[2]) 
		        			&& isInteger(cmdArray[4]) 
		        			&& isInteger(cmdArray[6])) {
		        		command = cmdArray[0] + "%20" + cmdArray[2] + ":" + cmdArray[4] + "-" + cmdArray[6];
		        		VersesActivity.call(this, command);
				        finish();
		        	}
		        }
		        else {
		        	Utils.showToast(this, R.string.error_please_try_again, true);
		        	finish();
		        }
	        }
        }
    }
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	protected void initViews() {
	}
}
