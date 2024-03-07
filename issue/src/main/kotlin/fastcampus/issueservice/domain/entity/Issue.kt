package fastcampus.issueservice.domain.entity

import fastcampus.issueservice.domain.enums.IssuePriority
import fastcampus.issueservice.domain.enums.IssueStatus
import fastcampus.issueservice.domain.enums.IssueType
import jakarta.persistence.*

@Entity
@Table
class Issue(
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   val id : Long,

   @Column
   var userId : Long,

   @Column
   @OneToMany
   var comments: MutableList<Comment> = mutableListOf(),

   @Column
   var summery : String,

   @Column
   var description : String,

   @Column
   @Enumerated(EnumType.STRING)
   var type : IssueType,

   @Column
   @Enumerated(EnumType.STRING)
   var priority : IssuePriority,

   @Column
   @Enumerated(EnumType.STRING)
   var status : IssueStatus,
) : BaseEntity()