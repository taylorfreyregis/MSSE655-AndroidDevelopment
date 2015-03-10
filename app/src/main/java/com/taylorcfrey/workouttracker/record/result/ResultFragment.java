package com.taylorcfrey.workouttracker.record.result;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.taylorcfrey.workouttracker.R;
import com.taylorcfrey.workouttracker.record.Record;
import com.taylorcfrey.workouttracker.record.RecordAdapter;
import com.taylorcfrey.workouttracker.record.RecordService;

import java.util.GregorianCalendar;
import java.util.List;

public class ResultFragment extends Fragment {

    private static final String LOGTAG = "ResultFragment";

    private static final String EXTRA_RECORD = "EXTRA_RECORD";

    private RelativeLayout mLayout;
    private List<Result> mResults;
    private Record mRecord;
    private Button mAddResultButton;
    private TextView mRecordName;
    private EditText mValue;
    private ResultService mResultService;
    private Button mDeleteRecordButton;
    private ListView mResultList;
    private ResultAdapter mResultAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecord = (Record)getArguments().getSerializable(EXTRA_RECORD);
        mResults = mRecord.getResults();
        mResultService = new ResultService(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_result, container, false);

        mResultList = (ListView) mLayout.findViewById(R.id.list_view_results);

        mAddResultButton = (Button) mLayout.findViewById(R.id.button_add_result);
        mAddResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAddResultDialog();
            }
        });
        mDeleteRecordButton = (Button) mLayout.findViewById(R.id.button_delete_record);
        mDeleteRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAddDeleteRecordDialog();
            }
        });

        return mLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
        mResultAdapter = new ResultAdapter(getActivity(), R.layout.list_item_result, mResults);
        mResultList.setAdapter(mResultAdapter);
    }

    public static ResultFragment newInstance(Record record) {
        ResultFragment fragment = new ResultFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable(EXTRA_RECORD, record);
        fragment.setArguments(arguments);
        return fragment;
    }

    public void loadAddResultDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_new_result, null);
        mRecordName = (TextView) view.findViewById(R.id.text_view_record_name);
        mRecordName.setText(mRecord.getName());
        mValue = (EditText) view.findViewById(R.id.edit_text_dialog_result_value);

        builder.setView(view);
        builder.setTitle("Add Result");
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Result result = new Result();
                result.setRecordId(mRecord.getId());
                result.setValue(Integer.parseInt(mValue.getText().toString()));
                result.setDate((GregorianCalendar) GregorianCalendar.getInstance());
                int id = mResultService.create(result);
                mResults = mResultService.getResults(mRecord);
//                record.setName(mNewRecordNameEditText.getText().toString());
//                mRecordService.create(record);
//                mRecords = mRecordService.getRecords();
                Toast.makeText(getActivity(), "Saving new result: " + id, Toast.LENGTH_SHORT).show();
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


    public void loadAddDeleteRecordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Add Result");
        builder.setMessage("Are you sure you wish to delete " + mRecord.getName() + "?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RecordService service = new RecordService(getActivity());
                service.delete(mRecord);
                getFragmentManager().popBackStack();
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
}
