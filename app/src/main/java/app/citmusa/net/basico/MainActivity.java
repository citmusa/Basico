package app.citmusa.net.basico;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private static final ArrayList<String> names = new ArrayList<String>();
    public static final String NAME_TAG = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText input_name = (EditText)findViewById(R.id.edit_name);
        ListView list = (ListView)findViewById(R.id.list_names);
        Button btn_submit = (Button)findViewById(R.id.button_submit);

        //Adaptador para la lista
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                                                android.R.layout.simple_list_item_1,
                                                                names);
        list.setAdapter(adapter);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = input_name.getText().toString();
                if (!names.contains(name)){
                    names.add(name);
                    adapter.notifyDataSetChanged();
                    input_name.setText("");
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
