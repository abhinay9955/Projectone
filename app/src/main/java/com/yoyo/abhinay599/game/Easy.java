package com.yoyo.abhinay599.game;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Easy extends Fragment implements View.OnClickListener {


    int player = 1;
    int number;
    CountDownTimer cnt;
    TextView txt;
    HashMap<Integer, Boolean> each;
    ArrayList<Integer> rejected;
    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;
    TextView player1, player2;
    int time = 10;
    Handler handler;
    Runnable runnable;

    public Easy() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_easy, container, false);
        player1 = view.findViewById(R.id.player1);
        player2 = view.findViewById(R.id.player2);
        txt = view.findViewById(R.id.textView3);
        img1 = view.findViewById(R.id.imageView1);
        img2 = view.findViewById(R.id.imageView2);
        img3 = view.findViewById(R.id.imageView3);
        img4 = view.findViewById(R.id.imageView4);
        img5 = view.findViewById(R.id.imageView5);
        img6 = view.findViewById(R.id.imageView6);
        img7 = view.findViewById(R.id.imageView7);
        img8 = view.findViewById(R.id.imageView8);
        img9 = view.findViewById(R.id.imageView9);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
        img6.setOnClickListener(this);
        img7.setOnClickListener(this);
        img8.setOnClickListener(this);
        img9.setOnClickListener(this);

        each = new HashMap<>();
        rejected = new ArrayList<>();
        generateRandom();
        check();
        player();
        clock();

        return view;
    }

    public void Initialize() {

        for (int i = 1; i <= 9; i++)
            each.put(i, false);
    }

    public void player() {
        if (player == 1) {
            player1.setBackgroundColor(Color.parseColor("#ffa040"));
            player2.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        }
        if (player == 2) {
            player2.setBackgroundColor(Color.parseColor("#ffa040"));
            player1.setBackgroundColor(Color.parseColor("#00FFFFFF"));

        }
    }

    public void onClick(View view) {
        for (int i : rejected) {
            if (Integer.parseInt(view.getTag().toString()) == i) {
                Toast.makeText(getActivity(), "BOX ALREADY SELECTED", Toast.LENGTH_SHORT).show();
                return;
            }
        }


        if (Integer.parseInt(view.getTag().toString()) == number) {
           // Toast.makeText(getActivity(), "PLAYER " + player + " won the game", Toast.LENGTH_SHORT).show();
            img1.setImageResource(R.drawable.win);
            img2.setImageResource(R.drawable.win);
            img3.setImageResource(R.drawable.win);
            img4.setImageResource(R.drawable.win);
            img5.setImageResource(R.drawable.win);
            img6.setImageResource(R.drawable.win);
            img7.setImageResource(R.drawable.win);
            img8.setImageResource(R.drawable.win);
            img9.setImageResource(R.drawable.win);
             cnt.cancel();
             txt.setText("Player "+player+" WON THE GAME");
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("START NEW GAME").setTitle("NEW GAME");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    newGame();
                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(getActivity(), GameActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }
            });


            builder.create();



        } else {
            ImageView img = (ImageView) view;
            if (player == 1)
                img.setImageResource(R.drawable.loose);
            if (player == 2)
                img.setImageResource(R.drawable.loose2);
            img.setScaleType(ImageView.ScaleType.FIT_XY);

            player = (player == 1 ? 2 : 1);
            rejected.add(Integer.parseInt(view.getTag().toString()));
            player();
            generateRandom();
            cnt.cancel();
            clock();

        }

    }

    public void generateRandom() {
        Random rand = new Random();
        number = rand.nextInt(9) + 1;
        while (rejected.contains(number)) {
            number = rand.nextInt(9) + 1;
        }


    }

    public void clock() {
       cnt= new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                txt.setText("" + l/1000);
            }

            @Override
            public void onFinish() {
                txt.setText("Player "+(player==1?2:1)+" WON THE GAME");
               cnt.cancel();
            }
        };
        cnt.start();

    }

    public void newGame() {
        img1.setImageResource(R.drawable.background);
        img2.setImageResource(R.drawable.background);
        img3.setImageResource(R.drawable.background);
        img4.setImageResource(R.drawable.background);
        img5.setImageResource(R.drawable.background);
        img6.setImageResource(R.drawable.background);
        img7.setImageResource(R.drawable.background);
        img8.setImageResource(R.drawable.background);
        img9.setImageResource(R.drawable.background);
        each = new HashMap<>();
        rejected = new ArrayList<>();
        generateRandom();
        player();
        clock();


    }

    public void check() {

        handler=new Handler();
        runnable= new Runnable() {
            @Override
            public void run() {
                if(rejected.size()==8)
                    newGame();
                check();
            }
        };
        handler.postDelayed(runnable,1000);
    }
}