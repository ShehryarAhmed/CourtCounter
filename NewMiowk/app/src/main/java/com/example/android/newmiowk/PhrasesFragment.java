package com.example.android.newmiowk;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {

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
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mediaPlayer.start();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
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

    public PhrasesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

    View rootview = inflater.inflate(R.layout.listview,container,false);

        //past <code>HERE</code>
    audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        //Creat ArrayList
        final ArrayList<ItemInListView> arrayList = new ArrayList<ItemInListView>();

        arrayList.add(new ItemInListView("minto wuksus", "Where are you going?", R.raw.phrase_are_you_coming));
        arrayList.add(new ItemInListView("tinnә oyaase'nә", "What is your name?", R.raw.phrase_what_is_your_name));
        arrayList.add(new ItemInListView("oyaaset", "My name is...", R.raw.phrase_my_name_is));
        arrayList.add(new ItemInListView("michәksәs?", "How are you feeling?", R.raw.phrase_how_are_you_feeling));
        arrayList.add(new ItemInListView("kuchi achit", "I’m feeling good.", R.raw.phrase_im_feeling_good));
        arrayList.add(new ItemInListView("әәnәs'aa?", "Are you coming?", R.raw.phrase_are_you_coming));
        arrayList.add(new ItemInListView("hәә’ әәnәm", "Yes, I’m coming.", R.raw.phrase_yes_im_coming));
        arrayList.add(new ItemInListView("әәnәm", " I’m coming.", R.raw.phrase_im_coming));
        arrayList.add(new ItemInListView("yoowutis", "Let’s go.", R.raw.phrase_lets_go));
        arrayList.add(new ItemInListView("әnni'nem", "Come here.", R.raw.phrase_come_here));

        AdapterView adapterView = new AdapterView(getActivity(),arrayList,R.color.color_pharse,R.color.color_pharse);

        ListView listView = (ListView) rootview.findViewById(R.id.list);

        listView.setAdapter(adapterView);

        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(android.widget.AdapterView<?> parent, View view, int position, long id) {

                ItemInListView itemInListView = arrayList.get(position);
                //release media player to play next sound

        release();

        mediaPlayer = MediaPlayer.create(getActivity(),itemInListView.getAudio_resource_id());

        // Request audio focus for playback

        int result = audioManager.requestAudioFocus(monAudioFocusChangeListener,
        // Use the music stream.
        AudioManager.STREAM_MUSIC,
        // Request permanent focus.
        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
        // Start playback.
        mediaPlayer.start();
        //setup a listner on the media players so that we can stop and release the
        //media player once the sound has  finished playing.
        mediaPlayer.setOnCompletionListener(monCompletionListener);
        }}
        });
        return rootview;
    }

    @Override
    public void onStop() {
        super.onStop();
        release();
    }

    private void release(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(monAudioFocusChangeListener );
        }
    }
}
