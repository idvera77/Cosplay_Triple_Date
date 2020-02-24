package com.mystra77.visualnovel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.mystra77.visualnovel.fragments.ContinueFragment;
import com.mystra77.visualnovel.fragments.GalleryFragment;
import com.mystra77.visualnovel.fragments.SettingsFragment;


public class HomeActivity extends AppCompatActivity {
    private int unlockImageGallery;
    private Button btnContinue, btnGaleria, btnSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_home);


        btnContinue = this.findViewById(R.id.btnContinue);
        btnGaleria = this.findViewById(R.id.btnGallery);
        btnSettings = this.findViewById(R.id.btnSettings);

    }

    public void Start(final View view) {

        startActivity(new Intent(view.getContext(), GameStart.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    public void Continue(View view) {
        btnContinue.setEnabled(false);
        ContinueFragment fragment = new ContinueFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.frameZoneFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        btnSettings.setEnabled(true);
        btnGaleria.setEnabled(true);
    }

    public void Gallery(View view) {
        btnGaleria.setEnabled(false);
        GalleryFragment fragment = new GalleryFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.frameZoneFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        unlockImageGallery++;
        btnSettings.setEnabled(true);
        btnContinue.setEnabled(true);
    }

    public void Settings(View view) {
        btnSettings.setEnabled(false);
        SettingsFragment fragment = new SettingsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.frameZoneFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        btnGaleria.setEnabled(true);
        btnContinue.setEnabled(true);
    }

    public void Exit(View view) {
        new AlertDialog.Builder(this, R.style.AlertDialogCustom)
                .setMessage(R.string.exitQuestion)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public int unlockGallery() {
        return unlockImageGallery;
    }


    public void goToPatreon(View view) {
        new AlertDialog.Builder(this, R.style.AlertDialogCustom)
                .setMessage(R.string.moveToPatreon)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.patreon.com/mystra77")));
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void goToTwitter(View view) {
        new AlertDialog.Builder(this, R.style.AlertDialogCustom)
                .setMessage(R.string.moveToTwitter)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/mystra77")));
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onBackPressed() {

    }
}
