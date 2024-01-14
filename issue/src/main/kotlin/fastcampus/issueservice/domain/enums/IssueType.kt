package fastcampus.issueservice.domain.enums

enum class IssueType {

    BUG, TASK;
    companion object{

        // TODO : operator invoke를 사용하면 생성자를 사용하듯이 fun of(type : String) : IssueType = valueOf(type.uppercase())와 같은 기능!!
        operator fun invoke(type : String) : IssueType = valueOf(type.uppercase())

    }

}