package hr.algebra.questionaire.repository

import hr.algebra.questionaire.model.Question

object QuestionRepository {

    private val questions = listOf(
        Question("Capital of Slovenia?", "Ljubljana"),
        Question("Capital of Croatia?", "Zagreb"),
        Question("Capital of Brbija?", "Beograd"),
        Question("Capital of Italy?", "Rome"),
    )

    operator fun get(index: Int) = if (index in questions.indices) questions[index] else null
}