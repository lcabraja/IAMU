package hr.algebra.questionaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import hr.algebra.questionaire.databinding.ActivityMainBinding
import hr.algebra.questionaire.model.Question
import hr.algebra.questionaire.repository.QuestionRepository
import hr.algebra.questionaire.utils.showToast

private const val INDEX = "hr.algebra.questionnaire.index"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var questionIndex = 0
    private var question: Question? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()


    }

    private fun setupListeners() {

        binding.etAnswer.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnNext.isEnabled = text?.isNotBlank() ?: false
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        // SAM == Single Abstract Method == Functional Interface == 'Lambdable'
        binding.btnNext.setOnClickListener {
            if (question!!.isCorrect(binding.etAnswer.text.toString().trim())) {
                showToast(getString(R.string.correct), Toast.LENGTH_LONG)
            } else {
                showToast(getString(R.string.notcorrect), Toast.LENGTH_LONG)
            }
            println(binding.tvQuestion.text.toString().trim())
            refresh()
        }
    }

    private fun refresh() {
        binding.etAnswer.text.clear()
        questionIndex++
        setCurrentQuestion()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        questionIndex = savedInstanceState.getInt(INDEX)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(INDEX, questionIndex)
    }

    override fun onResume() {
        super.onResume()
        setCurrentQuestion()

    }

    private fun setCurrentQuestion() {
        question = QuestionRepository[questionIndex]
        if (question != null) {
            binding.tvQuestion.text = question.toString()
        } else {
            binding.tvQuestion.text = getString(R.string.thanks)
            setWidgetsVisibility(View.INVISIBLE)
        }
    }

    private fun setWidgetsVisibility(visibility: Int) {
        binding.btnNext.visibility = visibility
        binding.etAnswer.visibility = visibility
    }
}

