package ca.paulshin.glass.bible.api;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Processor for bible.org api.
 */
public interface SearchVerses {
    @GET("/api/?type=json")
    void searchForVerse(@Query("passage") String passage, Callback<List<Verse>> callback);
    
    @GET("/api/?type=json")
    List<Verse> listVerses(@Query("passage") String passage);

//    public static class SearchResponse {
//        VerseContent response;
//
//        public List<Verse> getVerses() {
//            return response.verses;
//        }
//    }

    public static class Verse {
        public String bookname;
        public String chapter;
        public String verse;
        public String text;
        public String title;
    }

    class VerseContent {
        List<Verse> verses;
    }
}
