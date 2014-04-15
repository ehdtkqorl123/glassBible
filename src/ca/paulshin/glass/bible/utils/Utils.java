package ca.paulshin.glass.bible.utils;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import ca.paulshin.glass.bible.R;

public class Utils {
	public static void showToast(final Activity activity, int message, boolean isShort) {
		if (activity == null) return;
		
		LayoutInflater inflater = activity.getLayoutInflater();
		View layout = inflater.inflate(R.layout.toast_message, (ViewGroup) activity.findViewById(R.id.message_layout_root));

		TextView text = (TextView) layout.findViewById(R.id.message);
		text.setText(message);

		Toast toast = new Toast(activity);
		toast.setGravity(Gravity.BOTTOM, 0, 100);
		if (isShort)
			toast.setDuration(Toast.LENGTH_SHORT);
		else
			toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		toast.show();
	}
}
