package com.taylorcfrey.workouttracker.record;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.taylorcfrey.workouttracker.R;
import com.taylorcfrey.workouttracker.record.result.ResultFragment;

import java.util.ArrayList;
import java.util.List;


public class RecordFragment extends Fragment implements AdapterView.OnItemClickListener {

    private RelativeLayout mLayout;
    private ListView mRecordList;
    private RecordService mRecordService;
    private List<Record> mRecords;
    private RecordAdapter mRecordAdapter;
    private Button mCreateRecordButton;
    private EditText mNewRecordNameEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecordService = new RecordService(getActivity());
        mRecords = mRecordService.getRecords();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_record, container, false);

        mRecordList = (ListView) mLayout.findViewById(R.id.list_view_records);
        mRecordList.setOnItemClickListener(this);

        mCreateRecordButton = (Button) mLayout.findViewById(R.id.button_create_record);
        mCreateRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCreateRecordDialog();
            }
        });

        return mLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
        mRecordAdapter = new RecordAdapter(getActivity(), R.layout.list_item_record, mRecords);
        mRecordList.setAdapter(mRecordAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        loadResultFragment(mRecords.get(position));
    }

    public void loadCreateRecordDialog()  {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_create_record, null);
        mNewRecordNameEditText = (EditText) view.findViewById(R.id.edit_text_dialog_record_name);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Create Record");
        builder.setView(view);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Record record = new Record();
                record.setName(mNewRecordNameEditText.getText().toString());
                mRecordService.create(record);
                mRecords = mRecordService.getRecords();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
//        builder.create(); // Might not be necessary to create and then show... perhaps just show()
        builder.show();
    }

    private void loadResultFragment(Record record) {
        ResultFragment fragment = ResultFragment.newInstance(record);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_record_container, fragment, "ResultFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}