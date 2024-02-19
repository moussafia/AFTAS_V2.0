import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Datastate, State } from '../../state/state';
import { CompetitionList } from '../../model/competition/competition-list';
import { MemberServiceService } from '../../services/memberService/member-service.service';
import { IdentityDocumentType, MemberList } from '../../model/member/member-list';

@Component({
  selector: 'app-create-member',
  templateUrl: './create-member.component.html',
  styleUrl: './create-member.component.css'
})
export class CreateMemberComponent {
  memberFormGroupe?:FormGroup
  memberResult?:State<MemberList>;
 constructor(private  formBuilder: FormBuilder,
  private memberService: MemberServiceService){}
  readonly dataState = Datastate;
  readonly identityType = IdentityDocumentType;
 ngOnInit(): void {
     this.memberFormGroupe = this.formBuilder.group({
      firstName: ["",Validators.required],
      lastName: ["",Validators.required],
      identityDocumentType: ["CIN",Validators.required],
      identityNumber: ["",Validators.required],
      nationality: ["",Validators.required]
     })
 }

createMember(){
  if(this.memberFormGroupe?.invalid) return;
this.memberService.post(this.memberFormGroupe?.value).subscribe({
  next: data => {
    if(data.body)
    this.memberResult = ({dataState: Datastate.LOADED, data: data.body})  },
  error:err => {
    this.memberResult=({dataState: Datastate.ERROR, error: err.error.message})

}})
}

}

