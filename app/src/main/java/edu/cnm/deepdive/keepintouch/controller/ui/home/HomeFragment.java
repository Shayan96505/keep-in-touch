package edu.cnm.deepdive.keepintouch.controller.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.keepintouch.R;
import edu.cnm.deepdive.keepintouch.databinding.FragmentHomeBinding;

//implements AdapterView.OnItemSelectedListener

public class HomeFragment extends Fragment  {

  private HomeViewModel homeViewModel;

// the stock code that came  with the fragment
  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    homeViewModel =
        ViewModelProviders.of(this).get(HomeViewModel.class);
    View root = inflater.inflate(R.layout.fragment_home, container, false);
    final TextView textView = root.findViewById(R.id.text_home);
    homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        textView.setText(s);
      }
    });
    return root;
  }

  //TODO Fix Spinner
//  String[] users = { "Teen", "Millennial", "Parent", "Grandparent" };
//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.fragment_home);
//    Spinner spin = (Spinner) findViewById(R.id.select_user_type);
//    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
//    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//    spin.setAdapter(adapter);
//    spin.setOnItemSelectedListener(this);
//  }
//
//  @Override
//  public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
//    Toast.makeText(getApplicationContext(), "Selected User: "+ users[position] ,Toast.LENGTH_SHORT).show();
//  }
//  @Override
//  public void onNothingSelected(AdapterView<?> arg0) {
//    // TODO - Custom Code
//  }
}