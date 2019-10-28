package com.example.apkersan2.adapter;

import android.content.Context;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.apkersan2.R;
import com.example.apkersan2.fragment.DataKorbanFragment;
import com.example.apkersan2.fragment.IsiLaporanFragment;
import com.example.apkersan2.fragment.KonfirmasiFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import java.util.ArrayList;

public class FormStepperAdapter  extends AbstractFragmentStepAdapter {

    ArrayList<FormStepperAdapter> formStepperAdapters = new ArrayList<>();

    public FormStepperAdapter(@Nullable FragmentManager fm, @Nullable Context context){
        super(fm, context);
    }


    @Override
    public Step createStep(int position) {


        Step step = null;

        switch (position){
            case 0:
                final DataKorbanFragment dataKorbanFragment = new DataKorbanFragment();
                step = dataKorbanFragment;
                break;
            case 1:
                final IsiLaporanFragment isiLaporanFragment = new IsiLaporanFragment();
                step = isiLaporanFragment;
                break;
            case 2:
                final KonfirmasiFragment konfirmasiFragment = new KonfirmasiFragment();
                step = konfirmasiFragment;

            default:
        }

        return step;
    }



    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        StepViewModel.Builder builder = new StepViewModel.Builder(context);

//        //Override this method to set Step title for the Tabs, not necessary for other stepper types
//        return new StepViewModel.Builder(context)
//                .setTitle(R.string.tab_title) //can be a CharSequence instead
//                .create();

        switch (position){
            case 0:
                builder.setTitle("Step 1");
                break;
            case 1:
                builder.setTitle("Step 2");
                break;
            case 2:
                builder.setTitle("Step 3");
                break;
            default:
        }
        return builder.create();
    }
}
