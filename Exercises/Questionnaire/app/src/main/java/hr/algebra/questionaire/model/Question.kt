package hr.algebra.questionaire.model

data class Question(val question: String, val answer: String) {
    override fun toString() = question
    fun isCorrect(answer: String) = this.answer.equals(answer, ignoreCase = true)
}