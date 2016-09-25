package com.example.android.musicalstructure;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by android on 9/17/2016.
 */
public class lollywood extends AppCompatActivity{
    private MediaPlayer mediaPlayer;

    private AudioManager audioManager;

    private AudioManager.OnAudioFocusChangeListener monAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                release();
            }
        }
    };
    private MediaPlayer.OnCompletionListener monCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            release();
        }
    };
    @Override
    public void onStop() {
        super.onStop();
        release();
    }

    private void release(){
        if(mediaPlayer != null){
            mediaPlayer.release();

            mediaPlayer = null;

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview); {
            String songname = "LollyWood";
            final ArrayList<forview> arrayList = new ArrayList<forview>();
            arrayList.add(new forview(R.drawable.iconlolly,songname,R.color.lolly,R.raw.song));
            arrayList.add(new forview(R.drawable.iconlolly,songname,R.color.lolly,R.raw.song));
            arrayList.add(new forview(R.drawable.iconlolly,songname,R.color.lolly,R.raw.song));
            arrayList.add(new forview(R.drawable.iconlolly,songname,R.color.lolly,R.raw.song));
            arrayList.add(new forview(R.drawable.iconlolly,songname,R.color.lolly,R.raw.song));

            adapter adapterView = new adapter(this, arrayList,R.color.lolly,R.color.lolly);
            ListView listView = (ListView) findViewById(R.id.list);

            listView.setAdapter(adapterView);
            listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(android.widget.AdapterView<?> parent, View view, int position, long id) {
                    forview itemInListView = arrayList.get(position);
                    release();
                    mediaPlayer = MediaPlayer.create(lollywood.this, itemInListView.getAudioresourceid());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(monCompletionListener);
                }
            });
        }
    }
}
