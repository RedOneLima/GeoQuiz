package khewitt.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button next;
    private TextView questionText;

    private TrueFalse[] questionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_ocean,true),
            new TrueFalse(R.string.question_mideast,false),
            new TrueFalse(R.string.question_africa,false),
            new TrueFalse(R.string.question_americas,true),
            new TrueFalse(R.string.question_asia, true)
    };

    private int currentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionText =(TextView)findViewById(R.id.question_text_view);
        next=(Button)findViewById(R.id.next_button);
        trueButton =(Button)findViewById(R.id.true_button);
        falseButton=(Button)findViewById(R.id.false_button);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkAnswer(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                currentIndex =(currentIndex+1)%questionBank.length;
                updateQuestion();
            }
        });
        updateQuestion();
    }

    private void updateQuestion(){
        int question = questionBank[currentIndex].getQuestion();
        questionText.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue=questionBank[currentIndex].isTrueQuestion();
        int messageResId = 0;

        if(userPressedTrue==answerIsTrue){
            messageResId = R.string.correct_Toast;
        }else{
            messageResId = R.string.incorrect_Toast;
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);


    }
}
