package ca.paulshin.glass.bible.adapter;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import ca.paulshin.glass.bible.App;
import ca.paulshin.glass.bible.api.SearchVerses;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;

/**
 * Adapter for list of tips.
 */
public class VerseSearchAdapter extends CardScrollAdapter {

    private List<SearchVerses.Verse> mVerses;

    public VerseSearchAdapter(List<SearchVerses.Verse> verses) {
        mVerses = verses;
    }

    @Override
    public int getCount() {
        return mVerses.size();
    }

    @Override
    public Object getItem(int i) {
        return mVerses.get(i);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Card card = new Card(App.get());
        SearchVerses.Verse verse = mVerses.get(position);
        card.setText(verse.text.replace("<b>", "").replace("</b>", ""));
        card.setFootnote(verse.bookname + " " + verse.chapter + ":" + verse.verse);
        return card.getView();
    }

	@Override
	public int getPosition(Object item) {
		return mVerses.indexOf(item);
	}
}
